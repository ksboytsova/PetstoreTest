package com.ksboytsova.spring.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ksboytsova.spring.exception.TestApiException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class JsonUtil {
    public static final ObjectMapper objectMapper;

    private JsonUtil() {
    }

    static {
        objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        objectMapper.registerModule(module);
    }

    /**
     * Представление объекта в виде строки
     *
     * @param obj для перевода в строку
     * @return строковое представление объекта
     */
    public static String objectToString(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new TestApiException(e.getMessage());
        }
    }

}
