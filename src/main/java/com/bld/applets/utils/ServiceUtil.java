package com.bld.applets.utils;

import com.bld.applets.domain.AppletsOrder;
import com.bld.applets.domain.AppletsPower;
import com.bld.applets.domain.AppletsUser;
import com.bld.applets.service.IAppletsOrderService;
import com.bld.applets.service.IAppletsPowerService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author tyx
 * @title: ServiceUtil
 * @projectName applets
 * @description: TODO
 * @date 2021/3/6 11:32
 */
public class ServiceUtil {

    public static IAppletsPowerService powerService;

    public static IAppletsOrderService orderService;

    @Autowired
    private ServiceUtil (IAppletsPowerService powerService, IAppletsOrderService orderService) {
        ServiceUtil.powerService = powerService;
        ServiceUtil.orderService = orderService;
    }

    /*
     * @Author: tyx
     * @Description:计算两点坐标距离，单位为m
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
        return System.currentTimeMillis() + RandomStringUtils.randomNumeric(8);
    }

    // 计算价格
    public static String getCost (AppletsOrder order) {
        return "0.01";
    }

    // 计算积分，1度电=10分
    public static Long getIntegral (AppletsOrder order) {
        return order.getCharge() * 10;
    }

    // 计算充电量
    public static Long getCharge (AppletsOrder order) {
        return 5l;
//        Date startTime = order.getStartTime();
//        Date endTime = order.getEndTime();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        // 取开始和结束前后1小时区间的记录
//        List<AppletsPower> appletsPowers = powerService.selectPowerBySql("createTime BETWEEN '" + sdf.format(DateUtils.addHours(startTime, -1))
//                + "' and '" + sdf.format(DateUtils.addHours(endTime, 1)) + "' ORDER BY createTime");
//        // 开始时间所属功率 * 时间差 + 中间功率 * 时 + 结束时间所属功率 * 时间差
//        AppletsPower first = appletsPowers.get(0);
//        long startMinutes = 60 - DateUtils.getFragmentInMinutes(startTime, Calendar.HOUR_OF_DAY);
//        AppletsPower end = appletsPowers.get(appletsPowers.size() - 1);
//        long endMinutes = DateUtils.getFragmentInMinutes(endTime, Calendar.HOUR_OF_DAY);
//        long centerCharge = 0;
//        if (appletsPowers.size() >= 3) {
//            // 去掉首尾
//            List<AppletsPower> centerTimeFrame = appletsPowers.subList(1, appletsPowers.size() - 1);
//            centerCharge = centerTimeFrame.stream().mapToLong(e -> Long.parseLong(e.getPower())).sum();
//        }
//        return (long) (startMinutes / 60 * Double.parseDouble(first.getPower()) + endMinutes / 60 * Double.parseDouble(end.getPower()) + centerCharge);
    }

    // 计算等级经验差
    // 1度电 = 1经验
    public static Long getMemberDifference () {
        return 100l;
//        // 获取所有已完成订单充电量，求和，转换
//        List<AppletsOrder> appletsOrders = orderService.selectCompletOrder(CommonUtils.getUserId());
//        long totalCharge = appletsOrders.stream().mapToLong(e -> e.getCharge()).sum();
//        // 当前等级
//        Integer level = CommonUtils.getUser().getLevel();
//        // 取下一级所需经验值
//        String nextLevelExp = CacheConfigUtils.get("user_level_" + (level + 1));
//        return Long.valueOf(nextLevelExp) - totalCharge;
    }

}
