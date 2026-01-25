package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Login extends BaseTest{

    @Test
    public void login(){
        String createBody= """
                {
                  "username": "TS_Staj",
                  "password": "TS_Staj2026",
                  "rememberMe": true
                }
                """;

        Response response=
                given()
                        .spec(request)
                        .body(createBody)
                        .when()
                        .post("auth/login")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract().response();

        System.out.println(response.body());

    }
}
