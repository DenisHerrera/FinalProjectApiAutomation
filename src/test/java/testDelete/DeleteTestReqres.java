package testDelete;

import basePage.BaseTest;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class DenisTest extends BaseTest {


    @Test
    public void deleteUserTest() {
        given().log().all()
                .delete("users/2")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
