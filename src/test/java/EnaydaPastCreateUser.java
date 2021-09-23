import static io.restassured.RestAssured.*;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


import static io.restassured.config.LogConfig.logConfig;
import static org.hamcrest.Matchers.*;

public class EnaydaPastUserCreate {
    //https://reqres.in/api/users
   /* @Test
    public void newUSerCreateSuccessful(){
    given().queryParam("format","json")
                .config(config().logConfig(logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
            .when().get("https://reqres.in/api/users").
    then().log().all()
                .assertThat().statusCode(is(equalTo(404)));

    }

    @Test
    public void testGetUsers(){
        baseURI = "https://reqres.in/api";

        given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("data[1].first_name", equalTo("Janet"));
    }*/

    @Test
    public void testPostUser(){
        baseURI = "https://reqres.in/api";
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("name", "Alejandra");
        map.put("job", "Costumer Success");
        given()
                .log().all()
                .body(map.toString())
                .when()
                .post("/users")
                .then()
                .log().all()
                .statusCode(201);
    }
    @Test
    public void testPostUserOnlyName(){
        baseURI = "https://reqres.in/api";
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("name", "Alejandra");
        //map.put("job", "Costumer Success");
        given()
                .log().all()
                .body(map.toString())
                .when()
                .post("/users")
                .then()
                .log().all()
                .statusCode(201);
    }
    @Test
    public void testPostUserOnlyJob(){
        baseURI = "https://reqres.in/api";
        Map<String, Object> map = new HashMap<String,Object>();
        //map.put("name", "Alejandra");
        map.put("job", "Costumer Success");
        given()
                .log().all()
                .body(map.toString())
                .when()
                .post("/users")
                .then()
                .log().all()
                .statusCode(201);
    }
    @Test
    public void testPostUserEmpty(){
        baseURI = "https://reqres.in/api";
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("name", "");
        map.put("job", "");
        given()
                .log().all()
                .body(map.toString())
                .when()
                .post("/users")
                .then()
                .log().all()
                .statusCode(201);
    }
}