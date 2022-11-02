package day02;

import io.restassured.response.Response;
import org.junit.Test;
import utilities.Authentication;

import static io.restassured.RestAssured.given;

public class GetRequest06 {
    @Test
    public void test06() {
        System.out.println("Authentication.generateToken() = " + Authentication.generateToken());
        String url = "https://www.gmibank.com/api/tp-customers/114351";

        Response response = given().headers("Authorization", "Bearer " + Authentication.generateToken()).when().get(url);
        response.prettyPrint();



    }
}
