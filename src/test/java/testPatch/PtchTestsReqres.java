package testPatch;

import basePage.BaseTest;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.junit.Test;
import pojo.PatchUserRequest;
import pojo.PatchUserResponse;

import java.lang.ref.ReferenceQueue;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class PtchTestsReqres extends BaseTest{

    @Test
    public void patchUser(){
        JSONObject request =new  JSONObject();

        //request.put("name","Denis");
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

    @Test
    public void patchUserWithPojo(){
        PatchUserRequest request = new PatchUserRequest();

        request.setName("Denis");

        PatchUserResponse response = given().
                body(request).
                patch("users/2").
                then().
                statusCode(HttpStatus.SC_OK).
                extract().
                as(PatchUserResponse.class);

        assertThat(request.getName(), equalTo("Denis"));
    }

    @Test
    public void patchUserWithoutId(){
        PatchUserRequest request = new PatchUserRequest();

        request.setName("Diego Lopez");

        PatchUserResponse response = given().
                body(request).
                patch("users/").
                then().
                statusCode(HttpStatus.SC_NOT_FOUND).
                extract().
                as(PatchUserResponse.class);
    }
}
