package com.bld.applets.controller;

import com.bld.applets.constants.WxConstants;
import com.bld.applets.domain.AjaxResult;
import com.bld.applets.domain.AppletsOrder;
import com.bld.applets.domain.AppletsUser;
import com.bld.applets.service.IAppletsOrderService;
import com.bld.applets.service.IAppletsUserService;
import com.bld.applets.utils.CommonUtils;
import com.bld.applets.utils.ServiceUtil;
import com.bld.applets.wxpay.WxUtils;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.InputSource;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;

/**
 * @author tyx
 * @title: WxController
 * @projectName applets
 * @description: TODO
 * @date 2021/3/16 11:00
 */
@Controller
@RequestMapping("/applets/wx")
public class WxController extends BaseController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private IAppletsOrderService orderService;

    @Autowired
    private IAppletsUserService userService;

    @PostMapping("pay")
    @ResponseBody
    @Transactional
    public AjaxResult pay (Long orderId, Integer paymentType) {
        AppletsOrder appletsOrder = orderService.selectAppletsOrderById(orderId);
        if (paymentType == null) {
            return AjaxResult.parametersMissing();
        }
        if (appletsOrder != null) {
            appletsOrder.setPaymentType(paymentType);
        }
        // 微信支付
        if (paymentType == 1) {
            return createPreOrder(appletsOrder, "订单支付：" + appletsOrder.getId());
        }
        // 余额支付
        if (paymentType == 2) {
            Long userId = CommonUtils.getUserId();
            AppletsUser appletsUser = userService.selectAppletsUserById(userId);
            String balance = appletsUser.getBalance();
            String cost = appletsOrder.getCost();
            // 余额充足
            if (CommonUtils.compareStrNum(balance, cost) >= 0) {
                // 余额扣除
                int i = userService.updateUserBySql("balance = balance - " + cost + " where id = " + userId);
                if (i > 0) {
                    // 更新订单
                    orderService.updateAppletsOrder(appletsOrder.setStatus(2));
                    // 更新积分
                    userService.updateUserBySql("integral = integral + " + appletsOrder.getIntegral() + " where id = " + userId);
                }
                return toAjax(i);
            } else {
                return AjaxResult.error("余额不足");
            }
        }
        return AjaxResult.success();
    }

    // 充值
    @PostMapping("recharge")
    @ResponseBody
    public AjaxResult recharge (String amount) {
        // 创建订单
        AppletsOrder appletsOrder = new AppletsOrder()
                .setUserId(CommonUtils.getUserId())
                .setUserName(CommonUtils.getUser().getName())
                .setCode(ServiceUtil.generateOrderCode())
                .setStatus(1)
                .setPaymentType(1)
                .setCost(amount)
                .setType(2);
        appletsOrder.setCreateTime(new Date()).setUpdateTime(new Date());
        int i = orderService.insertAppletsOrder(appletsOrder);
        if (i > 0) {
            return createPreOrder(appletsOrder, "余额充值：" + appletsOrder.getId());
        }
        return AjaxResult.error("订单创建失败");
    }

    /**
     * @Author: tyx
     * @Description: 微信支付回调接口，异常处理需设置响应码为500或4xx，成功为200或204
     * 考虑小程序端回调验证订单
     * @Param: []
     * @return: com.bld.applets.domain.AjaxResult
     * @Date: 2021/4/22
     */
    @PostMapping("callBack")
    @ResponseBody
    public AjaxResult callBack () {
        // 证书序列号检查
        String serialNo = request.getHeader("Wechatpay-Serial");
        if (!WxConstants.WXPAY_CERT_SERIALNO.equals(serialNo)) {
            return AjaxResult.error();
        }
        // 请求体
        String postData = null;
        try {
            postData = CommonUtils.getPostData(request);
        } catch (IOException e) {
            e.printStackTrace();
            response.setStatus(500);
            return AjaxResult.error();
        }
        String wxTimestamp = request.getHeader("Wechatpay-Timestamp");
        String wxNonce = request.getHeader("Wechatpay-Nonce");
        // 构造签名串
        String signatureStr = WxUtils.getSignatureStr(wxTimestamp, wxNonce, postData);
        // 验签未通过
        if (!WxUtils.verification(signatureStr, request.getHeader("Wechatpay-Signature"))) {
            response.setStatus(500);
            return AjaxResult.error();
        }
        Gson gson = new Gson();
        HashMap<String, Object> postDataMap = gson.fromJson(postData, HashMap.class);
        LinkedTreeMap<String, String> resource = (LinkedTreeMap<String, String>) postDataMap.get("resource");
        String decryptStr = "";
        try {
            // 密文解密
            decryptStr = WxUtils.decryptToString(resource.get("associated_data"), resource.get("nonce"), resource.get("ciphertext"));
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
            response.setStatus(500);
            return AjaxResult.error();
        }
        // 回调数据
        Map<String, Object> decryptData = gson.fromJson(decryptStr, Map.class);
        synchronized (this.getClass()) {
            // 订单号
            String orderCode = decryptData.get("out_trade_no").toString();
            List<AppletsOrder> appletsOrders = orderService.selectAppletsOrderList(new AppletsOrder().setCode(orderCode));
            if (CollectionUtils.isEmpty(appletsOrders)) {
                response.setStatus(500);
                return AjaxResult.error();
            }
            AppletsOrder appletsOrder = appletsOrders.get(0);
            // 校验订单处理状态 1未处理
            if (appletsOrder.getStatus() == 1) {
                // 校验金额
                Map<String, Object> amount = (Map<String, Object>) decryptData.get("amount");
                String total = amount.get("total").toString();
                int divideValue = new BigDecimal(total).divide(new BigDecimal(appletsOrder.getCost())).intValue();
                // 微信金额以分为单位
                if (divideValue != 100) {
                    appletsOrder.setCallBack("total:" + total);
                }
                // 更新积分
                int updateIntegral = userService.updateUserBySql("integral = integral + " + appletsOrder.getIntegral() + " where id = " + CommonUtils.getUserId());
                if (updateIntegral <= 0) {
                    appletsOrder.setRemark("积分更新失败");
                }
                appletsOrder.setStatus(2);
                // 更新订单
                orderService.updateAppletsOrder(appletsOrder);
            }
        }
        return AjaxResult.success();
    }

    @PostMapping("getPhoneNumber")
    @ResponseBody
    public AjaxResult getPhoneNumber (String encryptedData, String iv) {
        if (StringUtils.isNotEmpty(encryptedData) && StringUtils.isNotEmpty(iv)) {
            AppletsUser user = CommonUtils.getUser();
            String result = WxUtils.decryptByAES(encryptedData, user.getSessionKey(), iv);
            if (StringUtils.isNotEmpty(result)) {
                Gson gson = new Gson();
                Map map = gson.fromJson(result, Map.class);
                Object phoneNumber = map.get("phoneNumber");
                if (phoneNumber != null) {
                    // 电话号码考虑加密存储
                    int i = userService.updateAppletsUser(user.setPhone(phoneNumber.toString()));
                    return toAjax(i).put("data", phoneNumber.toString());
                }
            }
            return AjaxResult.error();
        }
        return AjaxResult.parametersMissing();
    }

    private AjaxResult createPreOrder (AppletsOrder order, String desc) {
        if (order != null) {
            // 拉起微信支付api
            String result = WxUtils.createOrder(order, desc, WxConstants.NOTIFY_URL);
            if (StringUtils.isNotEmpty(result)) {
                // 取出预订单号
                String packageStr = result;
                Long timeStamp = System.currentTimeMillis() / 1000;
                String nonceStr = WxUtils.getKey(32);
                String paySign = "";
                try {
                    // 计算签名
                    paySign = WxUtils.sign(WxUtils.getSignatureStr(WxConstants.APPID, timeStamp, nonceStr, packageStr).getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                    return AjaxResult.error("签名计算失败");
                }
                // 返回参数
                HashMap<Object, Object> res = new HashMap<>();
                res.put("timeStamp", timeStamp);
                res.put("nonceStr", nonceStr);
                res.put("packageStr", packageStr);
                res.put("paySign", paySign);
                // 更新预付订单id
                orderService.updateAppletsOrder(order.setPrepayId(packageStr));
                return AjaxResult.success(res);
            }
        }
        return AjaxResult.error();
    }

}
