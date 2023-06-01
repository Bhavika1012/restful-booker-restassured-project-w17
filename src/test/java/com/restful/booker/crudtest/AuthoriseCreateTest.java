package com.restful.booker.crudtest;

import com.restful.booker.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class AuthoriseCreateTest extends TestBase {

    String token;
    @Test
    public void createToken() {
        Response response = given()
                .auth().preemptive()
                .basic("admin", "password123")
                .header("Content-Type", "application/json")
                .when()
                .post("/auth");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    public String getToken() {
        AuthoriseCreateTest auth = new AuthoriseCreateTest();
        return auth.token;
    }
}
