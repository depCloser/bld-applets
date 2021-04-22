package com.bld.applets.utils;

import com.bld.applets.domain.AppletsPiles;
import org.thingsboard.server.common.data.Device;

/**
 * @author tyx
 * @title: BeanUtils
 * @projectName applets
 * @description: TODO
 * @date 2021/3/16 11:06
 */
public class BeanUtils {
    
    /**
     * @Author: tyx
     * @Description: tb设备转换为充电桩
     * @Param: [device]
     * @return: void
     * @Date: 2021/3/16
     */
    public static AppletsPiles device2Piles (Device device) {
        return new AppletsPiles()
                .setName(device.getName())
                .setCode(device.getId().toString());

    }
    
    
    
    
    
    
    
}
