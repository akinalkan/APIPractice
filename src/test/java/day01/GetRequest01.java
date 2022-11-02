package day01;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequest01 {


    @Test
    public void test01() {
        String url = "https://restful-booker.herokuapp.com/booking";
        Response response = given().when().get(url);//==> End point'e gondermek icin request olusturduk.
        //Response response ==>api tarafindan bana donen cevap.

        //Response response2=given().auth().basic("username","password").when().get(url);
        //auth().basic ile request gondermek icin
        //response.prettyPrint();

       /* System.out.println("***************************");
        response.prettyPeek();
        System.out.println("***************************");
        response.asPrettyString();
        System.out.println("***************************");
        response.peek();*/
        response.print();//==>String olarak response'u yazdirir.
        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.statusLine() = " + response.statusLine());
        System.out.println("response.contentType() = " + response.contentType());

        //1-) Junit assert'leri ile API testi yapabiliriz
        assertEquals("Status kod hatali", 200, response.statusCode());
        assertEquals("HTTP/1.1 200 OK", response.statusLine());
        assertEquals("application/json; charset=utf-8", response.contentType());

        //2-) assertthat ile
        response.then().
                statusCode(200).
                contentType("application/json; charset=utf-8").
                statusLine("HTTP/1.1 200 OK");


    }


}
