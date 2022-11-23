package com.compass.mscustomer.controller;

import com.compass.mscustomer.domain.dto.CityDto;
import com.compass.mscustomer.fixture.CityFixture;
import com.compass.mscustomer.service.CityService;
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
    public void save_WhenSendSaveWithValidBody_ExpectedStatus201() {
        given()
                .contentType("application/json")
                .body(CityFixture.getCityFormDto())
                .when()
                .post("/v1/cities")
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void save_WhenSendSaveWithInvalidBody_ExpectedStatus400() {
        given()
                .contentType("application/json")
                .body(CityFixture.getCityFormDtoWithInvalidAttribute())
                .when()
                .post("/v1/cities")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void findByName_WhenSendFindByNameWithExistingName_ExpectedStatus200() {
        CityDto citySaved = cityService.save(CityFixture.getCityFormDto());

        given()
                .when()
                .get("/v1/cities/name={name}", citySaved.getName()).then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void findByName_WhenSendFindByNameWithNonExistingName_ExpectedStatus404() {
        given()
                .when()
                .get("/v1/cities/name={name}", "Nome blá blá blá").then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void findByState_WhenSendFindByStateWithExistingState_ExpectedStatus200() {
        CityDto citySaved = cityService.save(CityFixture.getCityFormDto());

        given()
                .when()
                .get("/v1/cities/state={state}", citySaved.getState()).then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void findByState_WhenSendFindByStateWithNonExistingState_ExpectedStatus404() {
        given()
                .when()
                .get("/v1/cities/state={state}", "Nome blá blá blá").then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

}