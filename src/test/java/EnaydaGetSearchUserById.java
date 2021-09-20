

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.config;
import static io.restassured.config.LogConfig.logConfig;
import static org.hamcrest.Matchers.*;


public class EnaydaSearchUserById {
    @Test
    public void requestSearchSingleUserByCorrectId(){
        RestAssured
                .given().queryParam("format","json")
                .config(config().logConfig(logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
                .when().get("https://reqres.in/api/users/2").
                then().log().all()
                .assertThat().statusCode(is(equalTo(200)));

    }
    @Test
    public void requestSearchSingleUserByIncorrectId(){
        RestAssured
                .given().queryParam("format","json")
                .config(config().logConfig(logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
                .when().get("https://reqres.in/api/users/23").
                then().log().all()
                .assertThat().statusCode(is(equalTo(404)));

    }
}