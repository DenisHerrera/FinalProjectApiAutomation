import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.junit.Before;
import static org.hamcrest.MatcherAssert.*;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

public class DenisTest {

    @Before
    public void setup(){
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.requestSpecification=new RequestSpecBuilder().setContentType(ContentType.JSON).build();
    }

    @Test
    public void deleteUserTest() {
        given().log().all()
                .delete("/users/2")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void patchUser(){
        JSONObject request =new  JSONObject();

        request.put("name","Denis");
        request.put("job","Quality Assurance");

        String nameUpdated= given()
                .body(request.toJSONString())
                .patch("users/2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath()
                .getString("job");

        assertThat(nameUpdated,equalTo("Quality Assurance"));
    }
}
