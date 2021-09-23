package basePage;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;

public abstract  class BaseTest {

    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeClass
    public static void setup(){
        logger.info("Inicando configuracion");
        RestAssured.baseURI = "https://reqres.in/";
        RestAssured.basePath = "api/";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.requestSpecification = new RequestSpecBuilder().setContentType(ContentType.JSON).build();
        logger.info("Configuracion exitosa");
    }

}
