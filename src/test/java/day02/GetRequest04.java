package day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequest04 {
    /*
    http://dummy.restapiexample.com/api/v1/employees  url’ine
GET request’i yolladigimda gelen response’un
status kodunun 200 ve content type’inin “application/json”
ve employees sayisinin 24
ve employee’lerden birinin “Ashton Cox”
ve gelen yaslar icinde 21, 61, ve 23 degerlerinden birinin oldugunu test edin.
     */


    @Test
    public void test04() {
        String url="http://dummy.restapiexample.com/api/v1/employees";
        Response response= RestAssured.given().when().get(url);

        response.then().contentType(ContentType.JSON).statusCode(200);
        response.then().assertThat().body("data",hasSize(24),"data.employee_name",hasItem("Ashton Cox"));

        response.then().assertThat().body("data.employee_age",hasItems(21,61,23));
        JsonPath json= given().when().get(url).jsonPath();
        List<Integer> age=json.getList("data.employee_age");
        System.out.println("age = " + age);
        List<String> name=json.getList("data.employee_name");
        System.out.println("name = " + name);


    }
}
