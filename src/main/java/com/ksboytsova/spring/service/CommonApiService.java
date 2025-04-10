package com.ksboytsova.spring.service;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.stereotype.Component;
import java.io.File;

@Component
public class CommonApiService {

    public static String baseUrl = "https://petstore.swagger.io/v2/";

    public static Response postRequest(RequestSpecification spec, String body, String url, String contentType) {
        return spec
                .body(body)
                .contentType(contentType)
                .post(url);
    }

    public static Response putRequest(RequestSpecification spec, String body, String url, String contentType) {
        return spec
                .body(body)
                .contentType(contentType)
                .put(url);
    }

    public static Response deleteRequest(RequestSpecification spec, String url) {
        return spec
                .delete(url);
    }

    public static Response getRequest(RequestSpecification spec, String url) {
        return spec
                .get(url);
    }

    public static Response postUploadRequest(RequestSpecification spec, String parameterName, String filePath, String url, String contentType) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Файл не найден: " + file.getAbsolutePath());
        } else {
            System.out.println("Файл найден: " + file.getAbsolutePath());
        }
        return spec
                .multiPart(parameterName, file) // Прикрепление файла к запросу
                .contentType(contentType)
                .post(url);
    }
}
