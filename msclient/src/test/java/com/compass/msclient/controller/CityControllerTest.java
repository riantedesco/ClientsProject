package com.compass.msclient.controller;

import com.compass.msclient.domain.dto.CityDto;
import com.compass.msclient.fixture.CityFixture;
import com.compass.msclient.service.CityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.webAppContextSetup;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CityControllerTest {

    @Autowired
    private CityService cityService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        webAppContextSetup(webApplicationContext);
    }

    @Test
    public void saveCity_WhenSendMethodPost_ExpectedStatus201() {
        given()
                .contentType("application/json")
                .body(CityFixture.getCityFormDto())
                .when()
                .post("/v1/city")
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void saveCity_WhenSendMethodPost_ExpectedStatus400() {
        given()
                .contentType("application/json")
                .body(CityFixture.getCityFormDtoWithInvalidAttribute())
                .when()
                .post("/v1/city")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void findByNameCity_WhenSendMethodGetByName_ExpectedStatus200() {
        CityDto citySaved = cityService.save(CityFixture.getCityFormDto());

        given()
                .param("name", citySaved.getName())
                .when()
                .get("/v1/city/findByName").then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void findByNameCity_WhenSendMethodGetByName_ExpectedStatus404() {
        given()
                .param("name", "Nome blá blá blá")
                .when()
                .get("/v1/city/findByName").then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void findByStateCity_WhenSendMethodGetByState_ExpectedStatus200() {
        CityDto citySaved = cityService.save(CityFixture.getCityFormDto());

        given()
                .param("state", citySaved.getState())
                .when()
                .get("/v1/city/findByState").then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void findByStateCity_WhenSendMethodGetByState_ExpectedStatus404() {
        given()
                .param("state", "Nome blá blá blá")
                .when()
                .get("/v1/city/findByState").then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

}