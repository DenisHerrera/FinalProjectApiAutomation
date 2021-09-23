package testDelete;

import basePage.BaseTest;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class DeleteTestReqres extends BaseTest {


    @Test
    public void deleteUserTest() {

        String response = given()
                .delete("users/2")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT).
                extract().
                asString();

        assertThat(response,equalTo(""));
    }

    @Test
    public void deleteWithoutUserIdTest() {

       String response = given()
                .delete("users/")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .extract()
                .asString();
    }

    @Test
    public void deleteWithLetterIdTest() {

        String response = given()
                .delete("users/a")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .extract()
                .asString();
    }
}
