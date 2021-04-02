package RestApiScenarios;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class APITestCase {
    private String API_ROOT = "http://localhost:3000/api/books";
    JSONObject requestParams;

    public APITestCase() {
        RestAssured.baseURI = this.API_ROOT;
    }

    public Response getResponseBodyJsonPath(String additionalRoute) {
        Response response = RestAssured.given().
                when().
                get(API_ROOT+additionalRoute);

        return response;
    }

    public void InitializeBookStorePutData(JSONObject requestParams) {
        this.requestParams = requestParams;
    }

    public Response MakePutRequest(String idRoute) {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(this.requestParams.toJSONString())
                .when()
                .put(idRoute);

        return response;

    }
}
