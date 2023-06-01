package com.restful.booker.testbase;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class TestBase {

    @BeforeClass
    public static void inIT()
    {
        RestAssured.baseURI="https://restful-booker.herokuapp.com";

    }
}