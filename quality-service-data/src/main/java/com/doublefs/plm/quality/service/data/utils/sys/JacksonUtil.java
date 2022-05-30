package com.doublefs.plm.quality.service.data.utils.sys;

import com.doublefs.plm.quality.service.data.common.exception.SysException;
import com.doublefs.scm.open.common.util.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Jackson工具类 是对 ObjectMapper 进行了简单封装，待完善和扩展
 *
 * @author liuxing@doublefs.com
 * @date 2021/7/9
 */
@Slf4j
public class JacksonUtil {

    private static ObjectMapper objectMapper;

    private JacksonUtil() {
        throw new IllegalStateException("JacksonUtil class");
    }

    public static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    public static void setObjectMapper(ObjectMapper objectMapper) {
        JacksonUtil.objectMapper = objectMapper;
    }

    /**
     * json 串转换成实体类 （常用）
     *
     * @param content json 串
     * @param clazz
     * @param <T>     将要转换的类
     * @return
     */
    public static <T> T toEntity(String content, Class<T> clazz) {
        if (StringUtils.isBlank(content)) {
            return null;
        } else {
            try {
                return getObjectMapper().readValue(content, clazz);
            } catch (JsonProcessingException e) {
                log.error("JSON转换失败", e);
                throw new SysException("JSON转换失败");
            }
        }
    }

    /**
     * json 串转换成范型实体类
     *
     * @param content
     * @param valueTypeRef
     * @param <T>
     * @return
     */
    public static <T> T toEntity(String content, TypeReference<T> valueTypeRef) {
        if (!StringUtils.isBlank(content) && valueTypeRef != null) {
            try {
                return (T) getObjectMapper().readValue(content, valueTypeRef);
            } catch (JsonProcessingException e) {
                log.error("JSON转换失败", e);
                throw new SysException("JSON转换失败");
            }
        } else {
            return null;
        }
    }

    public static <T> List<T> toList(String content, Class<T> clazz) {
        try {
            return getObjectMapper().readValue(content,
                    getObjectMapper().getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (JsonProcessingException e) {
            log.error("JSON类型转换失败，请核对传入的数据", e);
            throw new SysException(e.getMessage());
        }
    }

    public static <K, V> List<Map<K, V>> toListMap(String content, Class<K> keyClass, Class<V> valueClass) {
        try {
            return getObjectMapper().readValue(content, getObjectMapper().getTypeFactory().constructCollectionType(
                    List.class, getObjectMapper().getTypeFactory().constructMapType(Map.class, keyClass, valueClass)));
        } catch (JsonProcessingException e) {
            log.error("JSON类型转换失败，请核对传入的数据", e);
            throw new SysException(e.getMessage());
        }
    }

    public static Map<String, Object> toMap(String content) {
        try {
            return (Map<String, Object>) getObjectMapper().readValue(content,
                    new TypeReference<Map<String, Object>>() {
                    });
        } catch (JsonProcessingException e) {
            throw new SysException(e.getMessage());
        }
    }

    public static <K, V> Map<K, V> toMap(String content, Class<K> keyClass, Class<V> valueClass) {
        try {
            return getObjectMapper().readValue(content,
                    getObjectMapper().getTypeFactory().constructMapType(Map.class, keyClass, valueClass));
        } catch (JsonProcessingException e) {
            throw new SysException(e.getMessage());
        }
    }

    /**
     * 转换成 json 串 （常用）
     *
     * @param value
     * @return
     */
    public static String toJson(Object value) {
        try {
            return getObjectMapper().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new SysException(e.getMessage());
        }
    }
}
