import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class DenisTest {
    @Test
    public void test2(){
        RestAssured.baseURI = "https://reqres.in/api";

        RestAssured.
                given().
                get("/users?page=2").
                then().
                statusCode(200).
                body("data[4].first_name",equalTo("George"));
        //body("data.first_name", hasItems("George","Raquel"));
    }

    @Test
    public void testPost(){
        JSONObject request =new  JSONObject();

        request.put("name","Denis");
        request.put("job","Quality Assurance");

        RestAssured.baseURI = "https://reqres.in/api";

        RestAssured.
                given().
                header("Content-Type","Application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                post("/users").
                then().
                statusCode(201);
    }
}
