package com.monese.bank.util;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.web.server.LocalServerPort;

public class RestAssuredUtil {

    @LocalServerPort
    private int serverPort;

    @BeforeEach
    void initRestAssured() {
        RestAssured.port = serverPort;
        RestAssured.baseURI = "http://localhost";
        RestAssured.filters(new ResponseLoggingFilter());
        RestAssured.filters(new RequestLoggingFilter());
    }
}

