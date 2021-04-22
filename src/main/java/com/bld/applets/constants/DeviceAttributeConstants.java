package com.bld.applets.constants;

/**
 * @author tyx
 * @title: DeviceAttribute
 * @projectName applets
 * @description: TODO
 * @date 2021/3/13 18:02
 */
public class DeviceAttributeConstants {

    // 开关=充电状态
    public static final String ChargingPileSwitch = "ChargingPileSwitch";
    // 电价 key：时段 value：价格
    public static final String ChargingFee = "ChargingFee";
    // 位置
    public static final String Location = "Location";
    // 维护开关=boolean
    public static final String maintaining = "maintaining";
    // 制造商
    public static final String manufacturer = "manufacturer";
    // 操作人
    public static final String Operator = "Operator";
    // 有序充电？充电方式
    public static final String OrderlyCharging = "OrderlyCharging";
    // 版本
    public static final String WorkingVersion = "WorkingVersion";
    // 容量
    public static final String SPICAPACITY = "SPICAPACITY";
    // 功率
    public static final String power = "power";

    public enum Switch {
        OFF, ON;
    }

}
