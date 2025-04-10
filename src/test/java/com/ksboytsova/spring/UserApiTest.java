package com.ksboytsova.spring;

import com.ksboytsova.spring.models.*;
import com.ksboytsova.spring.service.UserApiService;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static junit.framework.Assert.assertEquals;

public class UserApiTest {

    private UserApiService userApiService;
    private User user;

    @BeforeEach
    void setUp() {
        userApiService = new UserApiService();
        user = new User(434543, "mary11112", "Мария", "Петрова", "test@test.ru", "Qwerty12345", "+79098884747", 22);
    }

    @Test
    @DisplayName("Создание пользователя")
    void createUser() {
        Response response = userApiService.createUser(user);
        assertEquals(200, response.getStatusCode());
//        assertEquals(user.getUsername(),response.as(User.class).getUsername());
    }

    @Test
    @DisplayName("Изменение пользователя")
    void putUser() {
        user.setLastName("Сидорова");
        Response response = userApiService.putUser(user, user.getUsername());
        assertEquals(200, response.getStatusCode());

    }

    @Test
    @DisplayName("Вход пользователем")
    void loginUser() {
        Response response = userApiService.loginUser(user.getUsername(), user.getPassword());
        assertEquals(200, response.getStatusCode());
    }
}
