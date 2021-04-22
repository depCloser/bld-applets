package com.bld.applets.config;

import com.bld.applets.wxpay.IWXPayDomain;
import com.bld.applets.wxpay.WXPayConfig;

import java.io.InputStream;

/**
 * @author tyx
 * @title: BldWxConfig
 * @projectName applets
 * @description: TODO
 * @date 2021/3/19 15:23
 */
public class BldWxConfig extends WXPayConfig {

    @Override
    public String getAppID() {
        return null;
    }

    @Override
    public String getMchID() {
        return null;
    }

    @Override
    public String getKey() {
        return null;
    }

    @Override
    public InputStream getCertStream() {
        return null;
    }

    @Override
    public IWXPayDomain getWXPayDomain() {
        return null;
    }

}
