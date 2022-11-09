package day03;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.GmiBankBaseURL;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

public class GetRequest08 extends GmiBankBaseURL {
    /*
    http://www.gmibank.com/api/tp-customers/43703

          “firstName”: “Alda”,
          “lastName”: “Monahan”,
          “middleInitial”: “Nichelle Hermann Kohler”,
          “email”: “com.github.javafaker.Name@7c011174@gmail.com”,
          “mobilePhoneNumber”: “909-162-8114”,
          “city”: “St Louis”,
          “ssn”: “108-53-6655"

          1) MATCHERS CLASS
          2) JSON PATH
          3) De-Serialization
     */
    //1. Set The URL
    // 2. Set The Expected Data ( put, post, patch)
    // 3. Send The Request And Get The Response
    // 4. Do Assertion

    @Test
    public void get08() {
        //1. Set The URL
        spec01.pathParams("first", "tp-customers", "second", 43703);

        // 2. Set The Expected Data ( put, post, patch)
        Map<String,Object> expectedData= new HashMap<>();
        expectedData.put("firstName","Alda");
        expectedData.put("lastName","Monahan");
        expectedData.put("middleInitial","Nichelle Hermann Kohler");
        expectedData.put("email","com.github.javafaker.Name@7c011174@gmail.com");
        expectedData.put("mobilePhoneNumber","909-162-8114");
        expectedData.put("city","St Louis");
        expectedData.put("ssn","108-53-6655");

        System.out.println("expectedData = " + expectedData);

        // 3. Send The Request And Get The Response
        Response response = given().spec(spec01).headers("Authorization", "Bearer " + generateToken()).when().get("/{first}/{second}");
        response.prettyPrint();

        // 4. Do Assertion

        response.
                then().
                assertThat().
                body("firstName", equalTo("Alda"),
                        "lastName", equalTo("Monahan"),
                        "middleInitial", equalTo("Nichelle Hermann Kohler"),
                        "email", equalTo("com.github.javafaker.Name@7c011174@gmail.com"),
                        "mobilePhoneNumber", equalTo("909-162-8114"),
                        "city", equalTo("St Louis"),
                        "ssn", equalTo("108-53-6655"));

        // Json Path ile doğrula
        JsonPath json = response.jsonPath();
        assertEquals("Alda", json.getString("firstName"));
        assertEquals("Monahan", json.getString("lastName"));
        assertEquals("com.github.javafaker.Name@7c011174@gmail.com", json.getString("email"));
        assertEquals("Nichelle Hermann Kohler", json.getString("middleInitial"));
        assertEquals("909-162-8114", json.getString("mobilePhoneNumber"));
        assertEquals("St Louis", json.getString("city"));
        assertEquals("108-53-6655", json.getString("ssn"));

        //De Serialization
        Map<String,Object> actualData=response.as(HashMap.class);
        System.out.println("actualData = " + actualData);
        Assert.assertEquals(actualData.get("firstName"),actualData.get("firstName"));
        Assert.assertEquals(actualData.get("lastName"),actualData.get("lastName"));
        Assert.assertEquals(actualData.get("email"),actualData.get("email"));
        Assert.assertEquals(actualData.get("middleInitial"),actualData.get("middleInitial"));
        Assert.assertEquals(actualData.get("mobilePhoneNumber"),actualData.get("mobilePhoneNumber"));
        Assert.assertEquals(actualData.get("city"),actualData.get("city"));
        Assert.assertEquals(actualData.get("ssn"),actualData.get("ssn"));



    }
}
