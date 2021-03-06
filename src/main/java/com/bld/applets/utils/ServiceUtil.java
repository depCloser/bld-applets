package com.bld.applets.utils;

import com.bld.applets.domain.AppletsUser;
import org.apache.commons.lang3.RandomStringUtils;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author tyx
 * @title: ServiceUtil
 * @projectName applets
 * @description: TODO
 * @date 2021/3/6 11:32
 */
public class ServiceUtil {

    /*
     * @Author: tyx
     * @Description:计算两点坐标距离
     * @Param: [sourceJ, sourceW, targetJ, targetW, ellipsoid]:[坐标1经度，坐标1纬度，坐标2经度，坐标2纬度，坐标系]
     * @return: double
     * @Date: 2021/2/24
     */
    public static double getDistance (String sourceJ, String sourceW, String targetJ, String targetW, Ellipsoid ellipsoid) {
        GlobalCoordinates from = new GlobalCoordinates(Double.parseDouble(sourceW), Double.parseDouble(sourceJ));
        GlobalCoordinates to = new GlobalCoordinates(Double.parseDouble(targetW), Double.parseDouble(targetJ));
        GeodeticCurve geoCurve = new GeodeticCalculator().calculateGeodeticCurve(ellipsoid, from, to);
        return geoCurve.getEllipsoidalDistance();
    }

    /*
     * @Author: tyx
     * @Description: 生成订单号
     * 当前系统时间戳+位随机数
     * @Param: []
     * @return: java.lang.String
     * @Date: 2021/3/3
     */
    public static String generateOrderCode () {
        return String.valueOf(System.currentTimeMillis()) + RandomStringUtils.randomNumeric(8);
    }

    public static String getCost () {
        // 计算发电量
        // 计算价格
        return "10";
    }

    public static Long getIntegral () {
        // 计算积分
        return 5l;
    }

    public static Long getCharge () {
        // 计算积分
        return 5l;
    }

    public static Long getMemberDifference () {
        // 计算等级经验差
        return 300l;
    }

}
