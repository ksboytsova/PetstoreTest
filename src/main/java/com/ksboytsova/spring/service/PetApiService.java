package com.ksboytsova.spring.service;

import com.ksboytsova.spring.helper.JsonUtil;
import com.ksboytsova.spring.models.Pet;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class PetApiService extends CommonApiService {
    static String url = baseUrl + "pet";

    public static Response createPet(Pet pet) {
        return postRequest(given(), JsonUtil.objectToString(pet), url, "application/json;charset=UTF-8");
    }

    public static Response getPet(int id) {
        return getRequest(given(), url + "/" + id);
    }

    public static Response putPet(Pet pet) {
        return putRequest(given(), JsonUtil.objectToString(pet), url, "application/json;charset=UTF-8");
    }

    public static Response deletePet(int id) {
        return deleteRequest(given(), url + "/" + id);
    }

    public static Response uploadImageForPet(int id, String filePath) {
        return postUploadRequest(given(), "file", filePath, url + "/" + id + "/uploadImage", "multipart/form-data");
    }

}
