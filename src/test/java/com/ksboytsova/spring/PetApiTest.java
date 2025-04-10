package com.ksboytsova.spring;

import com.ksboytsova.spring.models.*;
import com.ksboytsova.spring.service.PetApiService;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.Assert.assertEquals;

public class PetApiTest {
    private PetApiService petApiService;
    private Pet pet;

    @BeforeEach
    void setUp() {
        petApiService = new PetApiService();
        pet = new Pet(122299, new Category(0, "category"), "name11", new ArrayList<>(Arrays.asList("photoUrl")), new ArrayList<>(Arrays.asList(new Tag(0, "tag"))), Status.AVAILABLE);
    }

    void checkPetAndCreate() {
        Response getPet = petApiService.getPet(pet.getId());
        if (getPet.getStatusCode() != 200 || !getPet.as(Pet.class).getName().equals(pet.getName())) {
            Response createResponse = petApiService.createPet(pet);
        }
    }

    void checkPetAndDelete() {
        Response getPet = petApiService.getPet(pet.getId());
        if (getPet.getStatusCode() == 200) {
            Response deleteResponse = petApiService.deletePet(pet.getId());
        }
    }

    @Test
    @DisplayName("Валидное создание питомца")
    void createPetTest() {
        Response response = petApiService.createPet(pet);
        assertEquals(200, response.getStatusCode());
        assertEquals(pet.getName(), response.as(Pet.class).getName());
    }

    @Test
    @DisplayName("Невалидное создание питомца (отсутствует обязательное поле)")
    void createInvalidPetTest() {
        pet.setName(null);
        Response response = petApiService.createPet(pet);
        assertEquals(400, response.getStatusCode());
    }

    @Test
    @DisplayName("Получение существующего питомца")
    void getPetTest() {
        petApiService.createPet(pet);
        Response response = petApiService.getPet(pet.getId());
        assertEquals(200, response.getStatusCode());
        assertEquals(pet.getName(), response.as(Pet.class).getName());
    }

    @Test
    @DisplayName("Получение НЕсуществующего питомца")
    void getNotFoundPetTest() {
        checkPetAndDelete();
        try {
            Thread.sleep(5000); // Таймаут 15 секунд
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Response response = petApiService.getPet(pet.getId());
        assertEquals(404, response.getStatusCode());
    }

    @Test
    @DisplayName("Изменение существующего питомца")
    void putPetTest() {
        checkPetAndCreate();
        pet.setName("name11New");
        Response response = petApiService.putPet(pet);
        assertEquals(200, response.getStatusCode());
        assertEquals(pet.getName(), response.as(Pet.class).getName());
    }

    @Test
    @DisplayName("Удаление существующего питомца")
    void deleteNotFoundPetTest() {
        checkPetAndCreate();
        Response response = petApiService.deletePet(pet.getId());
        assertEquals(200, response.getStatusCode());
    }

    @Test
    @DisplayName("Удаление НЕсуществующего питомца")
    void deletePetTest() {
        checkPetAndDelete();
        try {
            Thread.sleep(5000); // Таймаут 15 секунд
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Response response = petApiService.deletePet(pet.getId());
        assertEquals(404, response.getStatusCode());
    }

    @Test
    @DisplayName("Загрузка файла для существующего питомца")
    void uploadImage() {
        checkPetAndCreate();
        Response response = petApiService.uploadImageForPet(pet.getId(), "src/test/1528641301_5.jpg");
        assertEquals(200, response.getStatusCode());
    }
}
