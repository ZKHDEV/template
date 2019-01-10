package com.zkh.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 字符串工具类
 */
public class StringUtil {
    /**
     * 判断字符串是否为空或NULL
     * @param value
     * @return
     */
    public static boolean isEmpty(String value) {
        return value == null || "".equals(value.trim());
    }

    /**
     * 将对象转化为JSON字符串
     * @param obj
     * @param <T>
     * @return
     */
    public static  <T> String toJsonString(T obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
