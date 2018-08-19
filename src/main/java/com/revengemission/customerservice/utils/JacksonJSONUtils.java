package com.revengemission.customerservice.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhang wanchao on 16-11-3.
 */
public class JacksonJSONUtils {

    /**
     * Object to JSON in String
     *
     * @param object
     * @return
     */
    public static String objectToJSONString(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        //mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return new String(mapper.writeValueAsBytes(object), Charset.forName("UTF-8"));
    }

    /**
     * String to Object
     *
     * @param jsonString
     * @param t
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T JSONStringToObject(String jsonString, Class<T> t) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper.readValue(jsonString, t);
    }

    public static String multiValueMapToJSONString(Map<String, String[]> object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
//        美化输出
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        Map<String, String> newMap = new HashMap<>();
        if (object != null && object.size() > 0) {
            object.forEach((k, v) -> {
                if (v != null && v.length > 0) {
                    newMap.put(k, StringUtils.join(v, ","));
                }
            });
        }
        return objectToJSONString(newMap);
    }

    public static Map<String, String> JSONStringToMap(String jsonString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper.readValue(jsonString, new TypeReference<Map<String, String>>() {
        });
    }

}
