package testing.com.crud.POST;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.text.MatchesPattern;
import org.testng.annotations.Test;

@Test
public class CreateTokenNonBDDStyle {

    // Given part is assigned to Request specification interface
    // when part is assigned to Response interface
    // Then part is assigned to ValidatableResponse interface

    String payload= "{\n" +
            "    \"username\" : \"admin\",\n" +
            "    \"password\" : \"password123\"\n" +
            "}";

    public void createToken(){
        RequestSpecification r= RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/auth");
        r.contentType(ContentType.JSON);
        r.body(payload);

        Response response= r.when().post();
        String resString= response.asString();
        System.out.println(resString);

        ValidatableResponse vResponse= response.then();
        vResponse.statusCode(200);

    }


}
