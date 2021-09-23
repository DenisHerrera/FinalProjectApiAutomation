package testPatch;

import basePage.BaseTest;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.junit.Test;
import pojo.CreareUserRequest;
import pojo.CreateUserResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ReqResTests extends BaseTest{



    /*@Test
    public void post(){

                given().
                body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"pistol\"\n" +
                        "}").
                post("register").
                then().
                statusCode(HttpStatus.SC_OK).
                body("token", notNullValue());

    }

    @Test
    public void getSingleTest(){
                given().
                get("users/2").
                then().
                statusCode(HttpStatus.SC_OK).
                body("data.id", equalTo(2));

    }

    @Test
    public void deleteUserTest(){
        given().
                delete("users/2").
                then().
                statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void patchUserTest(){
        String nameUpdate = given().
                when().
                body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}").
                patch("users/2").
                then().
                statusCode(HttpStatus.SC_OK).
                extract().
                jsonPath().
                getString("name");
        assertThat(nameUpdate,equalTo("morpheus"));
    }

    @Test
    public void putUserTest(){
        String nameUpdate = given().
                when().
                body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}").
                put("users/2").
                then().
                statusCode(HttpStatus.SC_OK).
                extract().
                jsonPath().
                getString("job");
        assertThat(nameUpdate,equalTo("zion resident"));
    }

    @Test
    public void getAllUsersTest(){

         Response response = given().
                get("users?page=2");

        Headers headers = response.getHeaders();
        int statusCode = response.getStatusCode();
        String body = response.getBody().asString();
        String contentType = response.contentType();

        assertThat(statusCode, equalTo(HttpStatus.SC_OK));

        System.out.println("**************************");
        System.out.println(headers.get("Content-type"));
        System.out.println(headers.get("Transfer-encoding"));
        System.out.println("**************************");
    }

    @Test
    public void getAllUsers2(){

        String response = given().
                when().
                get("users?page=2").
                then().
                extract().
                body().
                asString();

        int page = from(response).get("page");
        int totalPages = from(response).get("total_pages");
        int idFirstUsers = from(response).get("data[0].id");

        System.out.println("page: "+ page);
        System.out.println("Total pages " + totalPages);
        System.out.println("Id first users: "+ idFirstUsers);

        List<Map> usersWithGreaterThan10 = from(response).get("data.findAll {user -> user.id > 10}");
        String email = usersWithGreaterThan10.get(0).get("email").toString();
        System.out.println("Email is :" + email);

        List<Map> users = from(response).get("data.findAll { user -> user.id > 10 && user.last_name == 'Howell'}");
        int id = Integer.valueOf(users.get(0).get("id").toString());
        System.out.println("Id is : " + id);
    }

    @Test
    public void createUserTest(){
        String response = given().
                when().
                body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}").
                post("users").
                then().
                extract().
                body().
                asString();

        User user = from(response).getObject("", User.class);
        System.out.println("*************************");
        System.out.println("Id is :"+ user.getId());
        System.out.println("Users job is: "+ user.getJob());

    }
*/
    @Test
    public void registerUserTest(){

        CreareUserRequest dataUserCreate = new CreareUserRequest();
        dataUserCreate.setEmail("eve.holt@reqres.in");
        dataUserCreate.setPassword("pistol");


        CreateUserResponse response = given().
                when().
                body(dataUserCreate).
                post("register").
                then().
                statusCode(HttpStatus.SC_OK).
                contentType(equalTo("application/json; charset=utf-8")).
                extract().
                body().
                as(CreateUserResponse.class);

        assertThat(response.getId(),equalTo(4));
        assertThat(response.getToken(),equalTo("QpwL5tke4Pnpja7X4"));
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
