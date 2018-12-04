package com.zkh.core.utils;

public class StringUtil {
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }
}
