package com.github.joponie.flyer.common.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author 刘杰鹏
 * @since 2019-11-27
 */
public abstract class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
    }

    /**
     * 将 POJO 对象转为 JSON 字符串
     */
    public static <T> String toJson(T pojo) {
        String json;
        try {
            json = objectMapper.writeValueAsString(pojo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    /**
     * 将 JSON 字符串转为 POJO 对象
     */
    public static <T> T fromJson(String json, Class<T> type) {
        T pojo;
        try {
            pojo = objectMapper.readValue(json, type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return pojo;
    }

    public static <T> List<T> fromListJson(String json, Class<T> type) {
        List<T> pojo = Lists.newArrayList();
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, type);
            pojo = objectMapper.readValue(json, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pojo;
    }

    public static <K, T> Map<K, T> fromMapJson(String json, Class<K> keyType, Class<T> valueType) {
        Map<K, T> pojo = Maps.newHashMap();
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(Map.class, keyType, valueType);
            pojo = objectMapper.readValue(json, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pojo;
    }
}
