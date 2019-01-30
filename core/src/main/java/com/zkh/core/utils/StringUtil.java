package com.zkh.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

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
     * @throws JsonProcessingException
     */
    public static  <T> String toJsonString(T obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }

    /**
     * JSON字符串转化为对象
     * @param json
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T fromJsonString(String json,Class<T> clazz) throws IOException {
        return (T)new ObjectMapper().readValue(json, clazz);
    }


}
