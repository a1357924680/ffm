package com.family.financial.management.utils;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.annotation.JSONPOJOBuilder;
import com.family.financial.management.exception.FFMException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.HashMap;
import java.util.Map;

import static com.family.financial.management.emun.FFMExceptionEnum.ERROR_RETURN_TYPE;
import static com.family.financial.management.emun.FFMExceptionEnum.SYSTEM_ERROR;


public class ResultMapUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static Map<String, String> getSuccessResult() {
        Map<String, String> result = new HashMap<>(3);
        result.put("code", "200");
        result.put("msg", "success");
        return result;
    }

    public static Map<String, String> getSuccessResult(String msg,Object o) throws FFMException {
        Map<String, String> result = new HashMap<>();
        result.put("code", "200");
        result.put("msg", "success");

        String object = null;
        try {
            object = objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new FFMException(ERROR_RETURN_TYPE);
        }
        result.put(msg,object);
        return result;
    }


    public static Map<String, String> getErrorResult(int code, String msg) {
        Map<String, String> result = new HashMap<>(3);
        result.put("code", String.valueOf(code));
        result.put("msg", msg);
        return result;
    }

    public static Map<String, String> getErrorResult() {
        Map<String, String> result = new HashMap<>();
        result.put("code", String.valueOf(SYSTEM_ERROR.getCode()));
        result.put("msg", SYSTEM_ERROR.getMsg());
        return result;
    }

    public static Map<String, String> getResultMap(String subcode, String code, String message, String value) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("subcode", subcode);
        map.put("code", code);
        map.put("message", message);
        map.put("value", value);
        return map;
    }

}
