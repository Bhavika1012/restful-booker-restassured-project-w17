package com.restful.booker.testsuite;

import com.restful.booker.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class AuthorisationPostTest extends TestBase {

    static ValidatableResponse response;
    String token;


    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        response = given()
                .when()
                .get("/booking")
                .then().statusCode(200);
    }

    @Test
    public void createAuthToken() {
        Response response = given()
                .auth()
                .basic("admin", "password123")
                .header("Content-Type", "application/json")
                .when()
                .post("/auth");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    public String getToken() {
        return token;
    }
}