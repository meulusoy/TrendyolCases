package RestApiScenarios;

import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class APITestScenarios extends APITestCase {
    private final List<String> requiredFields = Arrays.asList("author", "title");

    @Test
    public void VerifyApiStartsWithEmptyStore() {
        // Sending a plain get request to check if response body is empty
        Response response = getResponseBodyJsonPath("");
        Assert.assertTrue(response.jsonPath().getList("$").size() == 0 && response.statusCode() != 500);
    }

    @Test
    public void VerifyRequiredFieldsInRequest() {
        JSONObject requestParams = new JSONObject();
        int bodyId = 1;
        String idWithRoute = "/1";
        Response responseBody;

        requestParams.put("id", bodyId);
        // Just added the id param to the request body and then api can return an error about related fields required
        InitializeBookStorePutData(requestParams);
        responseBody = MakePutRequest(idWithRoute);
        Assert.assertTrue(responseBody.statusCode() != 500);
        for(int i = 0 ; i < this.requiredFields.size() ; i++){
            Assert.assertTrue(responseBody.asString().contains(this.requiredFields.get(i)) && responseBody.asString().contains("required"));
        }

    }

    @Test
    public void VerifyThatRequiredFieldsCannotBeEmpty(){
        JSONObject requestParams = new JSONObject();
        int bodyId = 1;
        String idWithRoute = "/1";
        Response responseBody;

        requestParams.put("id", bodyId);
        requestParams.put("author","");
        requestParams.put("title","");
        // author and title keys' values are empty so we are expecting to get related error message
        InitializeBookStorePutData(requestParams);

        responseBody = MakePutRequest(idWithRoute);
        Assert.assertTrue(responseBody.statusCode() != 500);
        for(int i = 0 ; i < this.requiredFields.size(); i++){
            Assert.assertTrue(responseBody.asString().contains(this.requiredFields.get(i)) && responseBody.asString().contains("cannot be empty"));
        }
    }

    @Test
    public void VerifyThatIdIsReadonly(){
        JSONObject requestParams = new JSONObject();
        int bodyId = 2;
        String route = "/";
        String routeId = "1";
        Response responseBody;

        requestParams.put("id", bodyId);
        requestParams.put("author","Test Author");
        requestParams.put("title","Test Title");

        InitializeBookStorePutData(requestParams);
        // Try to make a put request with different body id and url route id so we can see if route id would override the id in the request body
        responseBody = MakePutRequest(route + routeId);
        Assert.assertTrue(responseBody.statusCode() != 500);
        Assert.assertTrue(responseBody.jsonPath().get("id").equals(routeId));
    }

    @Test
    public  void VerifyCreateNewBookWithPut(){
        JSONObject requestParams = new JSONObject();
        int bodyId = 1;
        String idWithRoute = "/1";
        Response responseBody;
        String author = "Trendyol Arthur";
        String title = "Trendin Yolu";

        requestParams.put("id", bodyId);
        requestParams.put("author",author);
        requestParams.put("title",title);

        InitializeBookStorePutData(requestParams);
        MakePutRequest(idWithRoute);

        responseBody = getResponseBodyJsonPath(idWithRoute);
        Assert.assertTrue(responseBody.statusCode() != 500);
        // Made a get request with same id so that we could see if we created the book
        Assert.assertTrue(responseBody.asString().contains(author) && responseBody.asString().contains(title) && responseBody.asString().contains(String.valueOf(bodyId)));

    }

    @Test
    public  void VerifyCannotCreateDuplicateBook(){
        JSONObject requestParams = new JSONObject();
        int bodyId = 1;
        String idWithRoute = "/1";
        int bodyId2 = 2;
        String idWithRoute2 = "/2";
        Response responseBody, responseBody2;
        String author = "";
        String title = "";

        // Get a book item that already exist
        responseBody = getResponseBodyJsonPath(idWithRoute);

        author = responseBody.path("author");
        title = responseBody.path("title");

        requestParams.put("id", bodyId2);
        requestParams.put("author",author);
        requestParams.put("title",title);

        InitializeBookStorePutData(requestParams);

        // Then try to put existed book informations and make a put request
        responseBody2 = MakePutRequest(idWithRoute2);

        Assert.assertTrue(responseBody2.statusCode() != 500);
        Assert.assertTrue(responseBody2.asString().contains("already exists"));


    }
}
