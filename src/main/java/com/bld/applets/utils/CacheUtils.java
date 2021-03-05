package com.bld.applets.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tyx
 * @title: CacheUtils
 * @projectName applets
 * @description: TODO
 * @date 2021/3/4 17:32
 */
public class CacheUtils {

    private static final ConcurrentHashMap<String, String> cacheMap = new ConcurrentHashMap<>();

    public static String get (String key) {
        return cacheMap.get(key);
    }

    public static String put (String key, String value) {
        return cacheMap.put(key, value);
    }

    public static void putAll (Map<String, String> map) {
        cacheMap.putAll(map);
    }

    public static void clean () {
        cacheMap.clear();
    }

}
