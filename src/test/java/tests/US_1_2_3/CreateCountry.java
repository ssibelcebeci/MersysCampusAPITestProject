package tests.US_1_2_3;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CreateCountry extends BaseTest {

    public static String countryId;

    @Test
    public void createCountry() {
        String name = "SibelCountry_" + System.currentTimeMillis();
        String code = "SC" + new Random().nextInt(1000);

        Map<String, Object> body = new HashMap<>();
        body.put("name", name);
        body.put("code", code);
        body.put("hasState", true);

        Response response =
                request
                        .body(body)
                        .when()
                        .post("school-service/api/countries");

        response
                .then()
                .statusCode(201);

        String resName = response.jsonPath().getString("name");
        String resCode = response.jsonPath().getString("code");
        Boolean hasState = response.jsonPath().getBoolean("hasState");

        countryId = response.jsonPath().getString("id");

        Assert.assertEquals(resName, name);
        Assert.assertEquals(resCode, code);
        Assert.assertTrue(hasState);
        Assert.assertNotNull(countryId);
    }
}