package com.bld.applets.constants;

import java.net.URL;

/**
 * @author tyx
 * @title: WxConstants
 * @projectName applets
 * @description: TODO
 * @date 2021/3/18 14:10
 */
public class WxConstants {

    /** api调用关键属性 */
    // 回调接口地址
    public static final String NOTIFY_URL = "https://depcloser.top/applets/wx/callBack";
    public static final String APIV3KEY = "9V5wsun0o1157fi2jhZ6Ey1gsgjnLdOh";
    public static final String APIKEY = "8tR11A87PL8cF16Qzl36HyrQG0vdjI29";
    /** 商户api证书 */
    public static final String API_CERT_SERIALNO = "4CAB516AD01630FE080CAECD603AB107E273F227";
//    public static final String API_CERT_PATH = "src/main/resources/wx/apiclient_cert.p12";
//    public static final String API_PRIVATE_KEY_PATH = "src/main/resources/wx/apiclient_key.pem";
//    public static final String API_PUBLIC_KEY_PATH = "src/main/resources/wx/apiclient_cert.pem";
    public static final String API_CERT_PATH = "/opt/tempapp/wx/apiclient_cert.p12";
    public static final String API_PRIVATE_KEY_PATH = "/opt/tempapp/wx/apiclient_key.pem";
    public static final String API_PUBLIC_KEY_PATH = "/opt/tempapp/wx/apiclient_cert.pem";

    // 微信支付平台证书
    public static final String WXPAY_CERT_SERIALNO = "5A1E344FF333060314C30B417B2753833DB4602D";
//    public static final String WXPAY_CERT_PATH = "src/main/resources/wx/wechatpay_5A1E344FF333060314C30B417B2753833DB4602D.pem";
    public static final String WXPAY_CERT_PATH = "/opt/tempapp/wx/wechatpay_5A1E344FF333060314C30B417B2753833DB4602D.pem";

    // 商户id
    public static final String MCHID = "1608161687";
    public static final String APPID = "wx2a4086fa20106854";
    public static final String APP_SECRET = "6f3e4402e15b28a407cdfe793f9b9b1d";

    /** 配置表字段key */
    public static final String WX_MCHID = "wx.mchid";
    public static final String WX_SERIALNO = "wx.serialNo";
    public static final String WX_APPID = "wx.appid";
    public static final String WX_APIKEY = "wx.apiKey";

}
