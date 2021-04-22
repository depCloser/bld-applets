package com.bld.applets.utils;

import com.bld.applets.constants.DeviceAttributeConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.thingsboard.rest.client.RestClient;
import org.thingsboard.server.common.data.Device;
import org.thingsboard.server.common.data.id.DeviceId;
import org.thingsboard.server.common.data.kv.AttributeKvEntry;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * @author tyx
 * @title: ThingsboardApiUtils
 * @projectName applets
 * @description: thingsboardAPI调用封装
 * 当前存在的问题：
 * client是否过期、过期如何检测
 * @date 2021/3/13 17:07
 */
public class ThingsboardApiUtils {

    // 测试
    private static final RestClient CLIENT = new RestClient("http://125.64.98.21:8088");
    private static final String UNAME = "1005750@qq.com";
    private static final String PWD = "Abc123654!";

    static {
        try {
            CLIENT.login(UNAME, PWD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 设备属性范围
    public enum Scop {
        SHARED_SCOPE, SERVER_SCOPE
    }

    /**
     * @Author: tyx
     * @Description: 获取设备
     * @Param: [deviceId, key]
     * @return: void
     * @Date: 2021/3/13
     */
    public static Optional<Device> getDeviceByName (String deviceName) {
        return CLIENT.getTenantDevice("test2");
    }

    /**
     * @Author: tyx
     * @Description: 获取设备
     * @Param: [deviceId, key]
     * @return: void
     * @Date: 2021/3/13
     */
    public static Optional<Device> getDeviceById (String deviceId) {
        return CLIENT.getDeviceById(DeviceId.fromString(deviceId));
    }

    /**
     * @Author: tyx
     * @Description: 获取设备属性值
     * @Param: [deviceId, key]
     * @return: void
     * @Date: 2021/3/13
     */
    public static List<AttributeKvEntry> getValueFromDevice (String deviceId, String... key) {
        return CLIENT.getAttributeKvEntries(DeviceId.fromString(deviceId), Arrays.asList(key));
    }

    /**
     * @Author: tyx
     * @Description: 设置属性值，重复键则更新  
     * @Param: [deviceId, scope, data]
     * @return: boolean
     * @Date: 2021/3/13
     */
    public static boolean setValueFromDevice (String deviceId, String scope, JsonNode data) {
        return CLIENT.saveDeviceAttributes(DeviceId.fromString(deviceId), scope, data);
    }

    /**
     * @Author: tyx
     * @Description: 充电桩开关
     * @Param: [deviceId, value] 1开 0关
     * @return: boolean
     * @Date: 2021/3/13
     */
    public static boolean setPilesSwitch (String deviceId, DeviceAttributeConstants.Switch value) throws JsonProcessingException {
        HashMap<String, String> params = new HashMap<>();
        params.put(DeviceAttributeConstants.ChargingPileSwitch, value.toString());
        return CLIENT.saveDeviceAttributes(DeviceId.fromString(deviceId), Scop.SHARED_SCOPE.toString(), new ObjectMapper().readTree(new Gson().toJson(params)));
    }

}
