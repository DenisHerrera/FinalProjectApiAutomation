import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Before;
import static org.hamcrest.Matchers.notNullValue;

public class PostMethodTest {

    @Before
    public void setup(){
        RestAssured.baseURI = "https//reqres.in";
        RestAssured.basePath = "/api/register";
        RestAssured.filters(new RequestLoggingFilter(),new ResponseLoggingFilter());
    }

    @Test
    public void loginRegisterTest(){

        RestAssured
                .given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"pistol\"\n" +
                        "}")
                .post("https://reqres.in/api/register")
                .then()
                .log().all()
                .extract()
                .asString();

    }
    // Verify server response status code 200
    @Test
    public void validateLoginRegister(){
        RestAssured
                .given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"pistol\"\n" +
                        "}")
                .post("https://reqres.in/api/register")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .body("token",notNullValue(),"id", notNullValue());
    }
}

