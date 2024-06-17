package testing.com.crud.GET;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class GetRequestNonBDD {

    @Test
    public void getRequestNegetive1(){

        RequestSpecification r=  RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/booking/qwe");

        r.when().log().all().get();

        r.then().log().all().statusCode(404);
        r.log().all();
    }

    @Test
    public void getRequestNegetive2(){

        RequestSpecification r=  RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/booking/-1");

        r.when().log().all().get();

        r.then().log().all().statusCode(404);
        r.log().all();
    }

    @Test
    public void getRequestPositive(){

        RequestSpecification r=  RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/booking/1");

        r.when().log().all().get();

        r.then().log().all().statusCode(200);
        r.log().all();
    }

}
