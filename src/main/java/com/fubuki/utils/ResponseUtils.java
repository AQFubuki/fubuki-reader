package com.fubuki.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseUtils {
    private String code;
    private String message;
    private Map data = new LinkedHashMap();

    public ResponseUtils() {
        this.code = "0";
        this.message = "success";
    }

    public ResponseUtils(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseUtils put(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public String toJosnString() {
        //返回JSON结果
        //JsonInclude.Include.ALWAYS 默认
        //JsonInclude.Include.NON_DEFAULT 属性为默认值不序列化
        //JsonInclude.Include.NON_EMPTY 属性为 空（“”） 或者为 NULL 都不序列化
        //JsonInclude.Include.NON_NULL 属性为NULL 不序列化
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            //模块注册，用于解决JSON无法转化日期的错误
            //objectMapper.registerModule(new JavaTimeModule());
            //return data == null ? null : objectMapper.writeValueAsString(data);

            String json = objectMapper.writeValueAsString(this);
            return json;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }
}
