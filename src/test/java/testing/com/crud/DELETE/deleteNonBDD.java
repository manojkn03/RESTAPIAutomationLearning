package testing.com.crud.DELETE;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

@Test
public class deleteNonBDD {

    String token= "fb963eb79b16052";

    public void deleteByID() {

        RequestSpecification r = RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/booking/714");
        r.cookie("token",token);
        r.contentType(ContentType.JSON);

        Response response=  r.when().delete();


         ValidatableResponse  vResponse=response.then();
         vResponse.statusCode(201);


    }

}
