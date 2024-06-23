package testing.com.crud.PojoClass;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class PostRequest {

    @Test
    public void testPostRequest(){

     //   Prepare payload
        Faker faker= new Faker();
        String expFirstName= faker.name().firstName();
        String expLastName= faker.name().lastName();
        Integer expectedPrice= faker.number().numberBetween(100,200);
        Booking booking = new Booking();
        booking.setFirstname(expFirstName);
        booking.setLastname(expLastName);
        booking.setDepositpaid(true);
        booking.setTotalprice(expectedPrice);
        booking.setAdditionalneeds("Breakfast");

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-01-01");
        bookingdates.setCheckout("2024-02-02");

        booking.setBookingdates(bookingdates);

        //converting object to json using Gson library  (Serialization)

        Gson gson= new Gson();
        String jsonPayload= gson.toJson(booking);
        System.out.println(jsonPayload);

        RequestSpecification r= RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com");
         r.basePath("/booking");
         r.contentType(ContentType.JSON);
         r.body(jsonPayload);

        Response response= r.when().post();
        System.out.println("Booking response is: ");
         String bookingResponse=response.asString();
        System.out.println(bookingResponse);

        ValidatableResponse vResponse= response.then();
        vResponse.statusCode(200);

        // deserialization (Json-to object)
       BookingResponse bookingRes= gson.fromJson(bookingResponse, BookingResponse.class);

        assertThat(bookingRes.getBooking().getFirstname()).isEqualTo(expFirstName);
        assertThat(bookingRes.getBooking().getLastname()).isEqualTo(expLastName);
        assertThat(bookingRes.getBooking().getTotalprice()).isEqualTo(expectedPrice);
        assertThat(bookingRes.getBookingid()).isNotNull();
        assertThat(bookingRes).hasFieldOrProperty("bookingid");




    }
}
