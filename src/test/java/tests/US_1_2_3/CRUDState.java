package tests.US_1_2_3;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.util.HashMap;
import java.util.Map;

public class CRUDState extends BaseTest {

    public static String stateId;

    @Test(dependsOnMethods = "tests.US_1_2_3.Country.CreateCountry.createCountry")
    public void createState() {

        String stateName = "SibelState_" + System.currentTimeMillis();

        Map<String, Object> country = new HashMap<>();
        country.put("id", CreateCountry.countryId);

        Map<String, Object> body = new HashMap<>();
        body.put("name", stateName);
        body.put("country", country);

        Response response =
                request
                        .body(body)
                        .when()
                        .post("school-service/api/states");

        response.then().statusCode(201);

        stateId = response.jsonPath().getString("id");
        Assert.assertNotNull(stateId);
    }

    @Test(dependsOnMethods = "createState")
    public void updateState() {

        Map<String, Object> country = new HashMap<>();
        country.put("id", CreateCountry.countryId);

        Map<String, Object> body = new HashMap<>();
        body.put("id", stateId);
        body.put("name", "UpdatedState_" + System.currentTimeMillis());
        body.put("country", country);

        request
                .body(body)
                .when()
                .put("school-service/api/states")
                .then()
                .statusCode(200);
    }

    @Test(dependsOnMethods = "updateState")
    public void deleteState() {

        request
                .when()
                .delete("school-service/api/states/" + stateId)
                .then()
                .statusCode(200);
    }
}
