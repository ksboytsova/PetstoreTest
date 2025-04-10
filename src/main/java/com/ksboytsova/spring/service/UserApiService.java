package com.ksboytsova.spring.service;

import com.ksboytsova.spring.helper.JsonUtil;
import com.ksboytsova.spring.models.User;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserApiService extends CommonApiService {
    static String url = baseUrl + "user";

    public static Response createUser(User user) {
        return postRequest(given(), JsonUtil.objectToString(user), url, "application/json;charset=UTF-8");
    }

    public static Response putUser(User user, String username) {
        return putRequest(given(), JsonUtil.objectToString(user), url + "/" + username, "application/json;charset=UTF-8");
    }

    public static Response loginUser(String username, String password) {
        return getRequest(given().queryParam("username", username).queryParam("password", password), url + "/" + "login");

    }

    public static Response createArraysUsers(List<User> users) {
        return postRequest(given(), JsonUtil.objectToString(users), url + "/createWithArray", "application/json;charset=UTF-8");
    }
}
