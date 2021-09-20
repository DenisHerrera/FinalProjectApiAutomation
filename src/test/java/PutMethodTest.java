import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Before;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class PutMethodTest {
    @Before
    public void setup() {

        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.requestSpecification=new RequestSpecBuilder().setContentType(ContentType.JSON).build();

    }

    @Test
    public void modifiedUser(){

        String nameUpdated= given()
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}")
                .patch("users/2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath()
                .getString("job");

        assertThat(nameUpdated,equalTo("zion resident"));
    }
    @Test
    public void ResponseTypeString (){
        String responseBody = given ().
                when ().
                get ("http://reqres.in/api/users/2") .
                getBody ().
                asString ();
        //System.out.println("AQUI-->"+responseBody);
        JsonPath resJson = new JsonPath(responseBody);
        String nameString = resJson.getString ("data.first_name");
        Assert.assertEquals ("Janet", nameString);



    }
}

