package testing.com.crud.PATCH;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class partialUpdateNonBDD {

    String payload= "{\n" +
            "    \"firstname\" : \"manu\",\n" +
            "    \"lastname\" : \"k\"  \n" +
            "}";
    String token= "dd1c5ec7cd3af3a";
   @Test
    public void partialUpdatePositive(){

       RequestSpecification   r= RestAssured.given();
           r.baseUri("https://restful-booker.herokuapp.com");
           r.basePath("/booking/4762");
           r.cookie("token",token);
           r.contentType(ContentType.JSON);
           r.body(payload);

          Response response=r.when().patch();
          String resString= response.asString();
          System.out.println(resString);

          ValidatableResponse vResponse= response.then();
          vResponse.statusCode(200);



    }

}
