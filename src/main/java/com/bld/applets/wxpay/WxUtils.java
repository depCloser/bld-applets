package com.bld.applets.wxpay;

import com.bld.applets.constants.WxConstants;
import com.bld.applets.domain.AppletsOrder;
import com.bld.applets.service.IAppletsConfigService;
import com.bld.applets.utils.CommonUtils;
import com.google.gson.Gson;
import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.AutoUpdateCertificatesVerifier;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tyx
 * @title: WxUtils
 * @projectName applets
 * @description: 微信公众平台操作密码：717837
 * getKey()方法生成apiv3密钥
 * @date 2021/3/18 13:53
 */
public class WxUtils {

    @Autowired
    private IAppletsConfigService configService;

    /*@PostConstruct
    public void init () {
        List<AppletsConfig> appletsConfigs = configService.selectConfigLikeKey("'wx.%'");
        if (CollectionUtils.isNotEmpty(appletsConfigs)) {
            Map<String, String> configMap = appletsConfigs.stream().collect(Collectors.toMap(AppletsConfig::getConfigKey, AppletsConfig::getConfigValue));
            privateKey = configMap.get(WxConstants.WX_APPID);
            API_CERT_SERIALNO = configMap.get(WxConstants.WX_SERIALNO);
            MCHID = configMap.get(WxConstants.WX_MCHID);
            APIV3KEY = configMap.get(WxConstants.WX_APIKEY);
        }
    }*/

    /**
     * @Author: tyx
     * @Description: 初始化client  
     * @Param: []
     * @return: org.apache.http.impl.client.CloseableHttpClient
     * @Date: 2021/4/22
     */
    public static CloseableHttpClient setupHttpClient() {
        // 加载商户私钥
        PrivateKey merchantPrivateKey = null;
        try {
            merchantPrivateKey = getPrivateKey(WxConstants.API_PRIVATE_KEY_PATH);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        // 本地签名
        PrivateKeySigner privateKeySigner = new PrivateKeySigner(WxConstants.API_CERT_SERIALNO, merchantPrivateKey);
        // 创建凭证
        WechatPay2Credentials wechatPay2Credentials = new WechatPay2Credentials(WxConstants.MCHID, privateKeySigner);
        // 远程认证
        /*WechatPay2Credentials wechatPay2Credentials = new WechatPay2Credentials(MCHID, new Signer() {
            @Override
            public SignatureResult sign(byte[] bytes) {
                // rpc调用
                String sign = "";
                String serialNum = "";
                return new SignatureResult(sign, serialNum);
            }
        });*/

        // 加载平台证书(证书过期自动更新)（MCHID：商户号,API_CERT_SERIALNO：商户证书序列号,APIV3KEY：V3秘钥）
        AutoUpdateCertificatesVerifier verifier = null;
        try {
            verifier = new AutoUpdateCertificatesVerifier(wechatPay2Credentials, WxConstants.APIV3KEY.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        // 初始化httpClient
        CloseableHttpClient httpClient = WechatPayHttpClientBuilder.create()
                .withMerchant(WxConstants.MCHID, WxConstants.API_CERT_SERIALNO, merchantPrivateKey)
                .withValidator(new WechatPay2Validator(verifier))
                .build();
        return httpClient;
    }

    /**
     * @Author: tyx
     * @Description: 微信统一下单
     * @Param: [order, desc, url]
     * @return: java.lang.String
     * @Date: 2021/4/22
     */
    public static String createOrder (AppletsOrder order, String desc, String url) {
        // 请求参数
        HashMap<Object, Object> amount = new HashMap<>();
        // 单位为分 故 * 100
        amount.put("total", new BigDecimal(order.getCost()).multiply(new BigDecimal(100)).intValue());
        amount.put("currency", "CNY");

        HashMap<Object, Object> payer = new HashMap<>();
        payer.put("openid", CommonUtils.getUser().getCode());

        HashMap<Object, Object> params = new HashMap<>();
        params.put("amount", amount);
        params.put("payer", payer);
        params.put("appid", WxConstants.APPID);
        params.put("mchid", WxConstants.MCHID);
        params.put("description", desc);
        params.put("notify_url", url);
        params.put("out_trade_no", order.getCode());

        StringEntity stringEntity = new StringEntity(new Gson().toJson(params), "utf-8");
        stringEntity.setContentType("application/json");

        HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi");
        httpPost.setHeader("Accept", "application/json");
        httpPost.setEntity(stringEntity);

        CloseableHttpClient client = setupHttpClient();

        HttpEntity entity = null;
        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            // 成功
            if (statusCode == 200 || statusCode == 204) {
                entity = response.getEntity();
                Gson gson = new Gson();
                HashMap<String, String> map = gson.fromJson(EntityUtils.toString(entity), HashMap.class);
                return "prepay_id=" + map.get("prepay_id");
            } else {
                System.out.println("failed,resp code = " + statusCode+ ",return body = " + EntityUtils.toString(response.getEntity()));
                throw new IOException("request failed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (client != null) {
                    client.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    /**
     * @Author: tyx
     * @Description: 小程序登录凭证校验，openid、sessionkey获取
     * @Param: [code]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Date: 2021/4/22
     */
    public static Map<String, Object> code2Session(String code) {
        CloseableHttpClient httpClient = setupHttpClient();
        HashMap<String, String> params = new HashMap<>();
        params.put("appid", WxConstants.APPID);
        params.put("secret", WxConstants.APP_SECRET);
        params.put("js_code", code);
        params.put("grant_type", "authorization_code"); // 固定值
        HashMap<String, Object> resultMap = new HashMap<>();
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(
                    new HttpGet(buildGetRequestUrl("https://api.weixin.qq.com/sns/jscode2session", params)));
            // 200 or 204 成功
//            int statusCode = response.getStatusLine().getStatusCode();
            // 转换响应体格式
            String result = EntityUtils.toString(response.getEntity());
            Gson gson = new Gson();
            resultMap = gson.fromJson(result, HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultMap;
    }

    /*********************************************************数据加解密相关*******************************************************/

    /**
     * @Author: tyx
     * @Description: 生成签名  
     * @Param: [message]
     * @return: java.lang.String
     * @Date: 2021/4/22
     */
    public static String sign(byte[] message) throws SignatureException, IOException, InvalidKeyException, NoSuchAlgorithmException {
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(getPrivateKey(WxConstants.API_PRIVATE_KEY_PATH));
        sign.update(message);
        return Base64.encodeBase64String(sign.sign());
    }

    /**
     * @Author: tyx
     * @Description: 微信回调验签
     * @Param: [source, wxSignature]
     * @return: boolean
     * @Date: 2021/4/22
     */
    public static boolean verification(String source, String wxSignature) {
        boolean isEquals = false;
        try {
            CertificateFactory instance = CertificateFactory.getInstance("X.509");
            X509Certificate certificate = (X509Certificate) instance.generateCertificate(
                    new FileInputStream(WxConstants.WXPAY_CERT_PATH));
            PublicKey publicKey = certificate.getPublicKey();
            Signature sign = Signature.getInstance("SHA256withRSA");
            sign.initVerify(publicKey);
            sign.update(source.getBytes());
            isEquals = sign.verify(Base64.decodeBase64(wxSignature));
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        return isEquals;
    }

    /**
     * @Author: tyx
     * @Description: 微信加密数据解密（如获取用户授权手机号）
     * @Param: [encryptData, sessionKey, iv]
     * @return: String
     * @Date: 2021/4/19
     */
    public static String decryptByAES (String encryptData, String sessionKey, String iv) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            Key sKeySpec = new SecretKeySpec(sessionKey.getBytes(), "AES");
            AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
            params.init(new IvParameterSpec(iv.getBytes()));
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, params);
            return new String(cipher.doFinal(encryptData.getBytes()), "utf-8");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @Author: tyx
     * @Description: 证书回调报文解密（如微信支付回调）
     * @Param: [associatedData, nonce, ciphertext]
     * @return: java.lang.String
     * @Date: 2021/4/22
     */
    public static String decryptToString(String associatedData, String nonce, String ciphertext) throws GeneralSecurityException, IOException {
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            SecretKeySpec key = new SecretKeySpec(WxConstants.APIV3KEY.getBytes(), "AES");
            GCMParameterSpec spec = new GCMParameterSpec(128, nonce.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, key, spec);
            cipher.updateAAD(associatedData.getBytes());
            return new String(cipher.doFinal(Base64.decodeBase64(ciphertext)), "utf-8");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new IllegalStateException(e);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 获取私钥。
     * @param filename 私钥文件路径
     * @return 私钥对象
     */
    public static PrivateKey getPrivateKey(String filename) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filename)), "utf-8");
        try {
            String privateKey = content.replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\r\n", "")
                    .replaceAll("\\s+", "");
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey)));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("当前Java环境不支持RSA", e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException("无效的密钥格式");
        }
    }

    /****************************************************辅助工具************************************************/

    /**
     * @Author: tyx
     * @Description: 字母数字随机串
     * @Param: [length]
     * @return: java.lang.String
     * @Date: 2021/4/22
     */
    public static String getKey (int length) {
        char[] num = getAsciiArray(48, 10);
        char[] uper = getAsciiArray(65, 26);
        char[] lower = getAsciiArray(97, 26);
        AtomicInteger atomicInteger = new AtomicInteger(length);
        StringBuffer key = new StringBuffer();
        while (atomicInteger.getAndAdd(-1) > 0) {
            int i = RandomUtils.nextInt(1, 4);
            if (i == 1) { // num
                key.append(num[RandomUtils.nextInt(0, 10)]);
            } else if (i == 2) { // uper
                key.append(uper[RandomUtils.nextInt(0, 26)]);
            } else { // lower
                key.append(lower[RandomUtils.nextInt(0, 26)]);
            }
        }
        return key.toString();
    }

    /**
     * @Author: tyx
     * @Description: ascii数组
     * @Param: [begin, length]
     * @return: char[]
     * @Date: 2021/4/22
     */
    public static char[] getAsciiArray (int begin, int length) {
        int end = begin + length;
        char[] charArray = new char[length];
        AtomicInteger index = new AtomicInteger(0);
        for (int i = begin; i < end; i++) {
            charArray[index.getAndAdd(1)] = (char) i;
        }
        return charArray;
    }

    // 构造签名串
    public static String getSignatureStr (Object... param) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(param).forEach(e -> {
            sb.append(e).append("\n");
        });
        return sb.toString();
    }

    public static String buildGetRequestUrl (String url, Map<String, String> params) {
        StringBuilder sb = new StringBuilder(url);
        if (params != null && params.size() > 0) {
            sb.append("?");
            params.forEach((k, v) -> {
                sb.append(k).append("=").append(v).append("&");
            });
            // 去末尾&
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

}
