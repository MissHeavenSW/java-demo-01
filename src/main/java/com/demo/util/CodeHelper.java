package com.demo.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 判断是否为空的工具类
 */
public class CodeHelper {
    private CodeHelper() {
    }

    public static boolean isNull(Object param) {
        return null == param;
    }

    public static boolean isNotNull(Object param) {
        return null != param;
    }

    public static boolean isNotNullOrEmpty(List<? extends Object> list) {
        return isNotNull((Object)list) && !list.isEmpty();
    }

    public static boolean isNotNullOrEmpty(Map<? extends Object, ? extends Object> map) {
        return isNotNull((Object)map) && !map.isEmpty();
    }

    public static boolean isNotNullOrEmpty(String str) {
        return isNotNull((Object)str) && !str.isEmpty();
    }

    public static boolean isNullOrEmpty(String str) {
        return isNull((Object)str) || str.isEmpty();
    }

    public static boolean isNull(Integer params) {
        return null == params;
    }

    public static boolean isNotNull(Integer params) {
        return null != params;
    }

    public static boolean isNotNull(Long params) {
        return null != params;
    }

    public static boolean isNotNullOrEmpty(Object[] params) {
        return isNotNull((Object)params) && params.length > 0;
    }

    public static boolean isNullOrEmpty(Collection<?> list) {
        return isNull((Object)list) || list.isEmpty();
    }

    public static boolean isNullOrEmpty(Map<? extends Object, ? extends Object> map) {
        return isNull((Object)map) || map.isEmpty();
    }
}
