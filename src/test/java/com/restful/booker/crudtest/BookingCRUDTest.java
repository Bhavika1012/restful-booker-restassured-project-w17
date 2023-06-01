package com.restful.booker.crudtest;

import com.restful.booker.testsuite.AuthorisationPostTest;

import com.restful.booker.model.BookingPojo;
import com.restful.booker.testbase.TestBase;
import com.restful.booker.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class BookingCRUDTest extends TestBase {
    static String firstName = "John" + TestUtils.getRandomValue();
    static String lastName = "Smith";
    static int totalPrice = 121;
    static String additionalNeeds = "Breakfast";
    static int bookingId;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }

    @Test
    public void createBooking() {
        HashMap<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstName(firstName);
        bookingPojo.setLastName(lastName);
        bookingPojo.setTotalPrice(totalPrice);
        bookingPojo.setDepositPaid(true);
        bookingPojo.setBookingDates(bookingDates);
        bookingPojo.setAdditionalNeeds(additionalNeeds);
        Response response = given()
                .header("Content-Type", "application/json")
                .body(bookingPojo)
                .when()
                .post("/booking");
        response.prettyPrint();
        response.then().log().all().statusCode(200);
        bookingId = response.jsonPath().get("bookingid");
        System.out.println("Booking Id : " + bookingId);
    }

    @Test
    public void getBooking() {
        Response response = given()
                .when()
                .get("/booking" + "/" + bookingId);
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void UpdateTheBooking() {
        AuthoriseCreateTest authoriseCreateTest = new AuthoriseCreateTest();
        String token = authoriseCreateTest.getToken();

        HashMap<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin","2023-03-29");
        bookingDates.put("checkout", "2023-04-02");

        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstName(firstName);
        bookingPojo.setLastName(lastName);
        bookingPojo.setTotalPrice(1000);
        bookingPojo.setDepositPaid(true);
        bookingPojo.setBookingDates(bookingDates);
        bookingPojo.setAdditionalNeeds("Dinner");



        Response response = given()
                .auth().preemptive()
                .basic("admin","password123")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token =02f5fd5946ffa39" )
                .body(bookingPojo)
                .when()
                .put("/booking" + "/" + bookingId);
        response.then().log().all().statusCode(200);
        response.prettyPrint();

    }

    @Test
    public void deleteTheBooking() {

        AuthorisationPostTest authorisationPostTest = new AuthorisationPostTest();
        String token = authorisationPostTest.getToken();

        Response response = given()
                .auth().preemptive()
                .basic("admin","password123")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token = 02f5fd5946ffa39")

                .when()
                .delete("/booking" + "/" + bookingId);
        response.then().statusCode(201);
        response.prettyPrint();
    }

    //Get all the bookings
    @Test
    public void getAllBookings()
    {
        Response response = given()
                .when()
                .get("/booking");
        response.then().statusCode(200);
        response.prettyPrint();

    }


}
