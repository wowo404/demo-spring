package org.liu.demo.mongodb.util;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * JSON 工具类
 *
 * @author 芋道源码
 */
public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(ObjectId.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(LocalDateTime.class, LocalDateTimeSerializer.INSTANCE);
        simpleModule.addDeserializer(LocalDateTime.class, LocalDateTimeDeserializer.INSTANCE);
        objectMapper.registerModule(simpleModule);
        objectMapper.setTimeZone(TimeZone.getDefault());
    }

    /**
     * 初始化 objectMapper 属性
     * <p>
     * 通过这样的方式，使用 Spring 创建的 ObjectMapper Bean
     *
     * @param objectMapper ObjectMapper 对象
     */
    public static void init(ObjectMapper objectMapper) {
        JsonUtils.objectMapper = objectMapper;
    }

    public static String toJsonString(Object object) {
        if (Validator.isEmpty(object)) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T parseObject(String text, Class<T> clazz) {
        if (StrUtil.isEmpty(text)) {
            return null;
        }
        try {
            return objectMapper.readValue(text, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T parseObject(byte[] bytes, Class<T> clazz) {
        if (ArrayUtil.isEmpty(bytes)) {
            return null;
        }
        try {
            return objectMapper.readValue(bytes, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T parseObject(String text, TypeReference<T> typeReference) {
        if (StrUtil.isBlank(text)) {
            return null;
        }
        try {
            return objectMapper.readValue(text, typeReference);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <K, V> Map<K, V> parseMap(String text) {
        if (StrUtil.isBlank(text)) {
            return null;
        }
        try {
            return objectMapper.readValue(text, new TypeReference<Map<K, V>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> ArrayList<T> parseArrayList(String text, Class<T> clazz) {
        if (StrUtil.isEmpty(text)) {
            return new ArrayList<>();
        }
        try {
            return objectMapper.readValue(text, objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<List<T>> parseInnerArrayList(String text, Class<T> clazz) {
        if (StrUtil.isEmpty(text)) {
            return new ArrayList<>();
        }
        try {
            JavaType innerType = objectMapper.getTypeFactory().constructParametricType(List.class, clazz);
            return objectMapper.readValue(text, objectMapper.getTypeFactory().constructCollectionType(List.class, innerType));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T, C> List<Map<T, C>> parseInnerMapList(String text, Class<T> clazz1, Class<C> clazz2) {
        if (StrUtil.isEmpty(text)) {
            return new ArrayList<>();
        }
        try {
            JavaType innerType = objectMapper.getTypeFactory().constructParametricType(Map.class, clazz1, clazz2);
            return objectMapper.readValue(text, objectMapper.getTypeFactory().constructCollectionType(List.class, innerType));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> HashSet<T> parseSet(String text, Class<T> clazz) {
        if (StrUtil.isEmpty(text)) {
            return new HashSet<>();
        }
        try {
            return objectMapper.readValue(text, objectMapper.getTypeFactory().constructCollectionType(Set.class, clazz));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonNode stringToJsonNode(String json) {
        try {
            return objectMapper.readTree(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T jsonNodeToObject(JsonNode jsonNode, Class<T> clazz) {
        try {
            return objectMapper.treeToValue(jsonNode, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isArray(String text) {
        return null != text && text.startsWith("[") && text.endsWith("]");
    }

    public static boolean isObject(String text) {
        return null != text && text.startsWith("{") && text.endsWith("}");
    }

    public static boolean isJson(String text) {
        return isArray(text) || isObject(text);
    }
}
