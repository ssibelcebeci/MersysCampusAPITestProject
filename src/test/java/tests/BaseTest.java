package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected RequestSpecification request;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://campus.techno.study";

        request = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }
}