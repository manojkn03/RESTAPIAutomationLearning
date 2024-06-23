package testing.com.crud.misc;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import org.testng.collections.Objects;

import java.util.LinkedHashMap;
import java.util.Map;

public class PayloadUsingMap {
   @Test
    public void test1(){

        /*String payload= "{\n" +
                "    \"firstname\" : \"manu\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";*/

        Map<String, Object> payload= new LinkedHashMap();
        payload.put("firstname", "Manoj");
        payload.put("lastname","kn");
        payload.put("depositpaid",true);
        payload.put("totalprice",111);
        payload.put("additionalneeds","Breakfast");

        Map<String, Object> payload2= new LinkedHashMap();
        payload2.put("checkin","2018-01-01");
        payload2.put("checkout","2019-01-01");

       payload.put("bookingdates",payload2);




            RequestSpecification r= RestAssured.given();
            r.baseUri("https://restful-booker.herokuapp.com");
            r.basePath("/booking");
            r.contentType(ContentType.JSON);
            r.body(payload);

            Response response= r.when().post();
            String res= response.asString();
            System.out.println(res);

            ValidatableResponse vResponse=response.then();
            vResponse.statusCode(200);

        }

    }

