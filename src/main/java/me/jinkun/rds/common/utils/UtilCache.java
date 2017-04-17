package me.jinkun.rds.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: HelloWorld！ <br/>
 * Autor: Created by jinkun on 2017/3/24.
 */
public class UtilCache {
    private static Map<String, Object> map = new HashMap<>();

    public static Object get(String key) {
        return map.get(key);
    }

    public static void put(String key, Object object) {
        map.put(key, object);
    }

    public static void put(String key, Object object, long expire) {
        map.put(key, object);
    }

    public static void expire(String token) {
        map.remove(token);
    }
}
