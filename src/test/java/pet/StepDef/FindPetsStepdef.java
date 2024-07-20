package pet.StepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import io.restassured.config.EncoderConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import pet.RequestSpec;

import java.io.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static io.restassured.RestAssured.*;

public class FindPetsStepdef {
    @Given("set the token to find pets")
    public void set_the_token_to_find_pets() {
        RequestSpec.getInstance().setRequestSpec();
    }
    @When("get call to find pets status is {string}")
    public void get_call_to_find_pets_status_is(String status) throws IOException {
        FileOutputStream outputStream=new FileOutputStream("src/main/resources/evidences/FindPets.json",true);
      Instant instant=  Instant.now();
        ZoneId zoneId=ZoneId.of("Asia/Kolkata");
        ZonedDateTime zonedDateTime=ZonedDateTime.ofInstant(instant,zoneId);
        outputStream.write(zonedDateTime.toString().getBytes());
        given(requestSpecification)
                .queryParam("status",status)
                .contentType(ContentType.JSON)
                .filter(ResponseLoggingFilter.logResponseTo(new PrintStream(outputStream), LogDetail.BODY))
                .config(config().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .when().get("/pet/findByStatu").then().assertThat().statusCode(200);
    }

}
