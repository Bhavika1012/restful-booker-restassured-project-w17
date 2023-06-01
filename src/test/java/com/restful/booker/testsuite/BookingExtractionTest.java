package com.restful.booker.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class BookingExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        response= given()
                .when()
                .get("/booking")
                .then().statusCode(200);

    }
    //1) Extract all the bookingid
    @Test
    public void extractBookingId()
    {
        List<Integer> idList= response.extract().path("bookingid");
        System.out.println("List Of Id is : " + idList);

    }
    //2. Extract the id of the 5th object
    @Test
    public void extractId(){
        Integer id= response.extract().path("bookingid[4]");
        System.out.println("Id of the 5th object  : " + id);

    }
}
