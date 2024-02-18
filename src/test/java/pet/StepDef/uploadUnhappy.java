package pet.StepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import pet.RequestSpec;
import pet.util.ApiManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class uploadUnhappy {
    @Given("token generation unhappy")
    public void token_generation_unhappy() {
        RequestSpec.getInstance().setRequestSpec();
    }
    @When("test update petid {string}  additional data {string} the payload and trigger unhappy")
    public void testUpdatePetidAdditionalDataThePayloadAndTriggerUnhappy(String petId, String ad) {
        String donload_url="https://github.com/appium/appium/raw/master/packages/appium/sample-code/apps/ApiDemos-debug.apk";
        InputStream inputStream= ApiManager.getInstance().get_download(donload_url);
        File f=new File("download/ApiDemos-debug.apk");
        FileOutputStream outputStream=null;
        try {
            outputStream=new FileOutputStream(f,true);
            byte[] bytes=new byte[inputStream.available()];
            inputStream.read(bytes);
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
            given(requestSpecification).contentType(ContentType.MULTIPART)
                    .pathParam("petId",petId)
                    .filter(ResponseLoggingFilter.logResponseTo(new PrintStream("src/main/resources/evidences/uploadUnhappy.txt"), LogDetail.BODY))
                    .formParam("additionalMetadata",ad)
                    .multiPart(f).when().post("/pet/{petId}/uploadImage")
                    .then().assertThat().statusCode(404)
                    .body("code", Matchers.comparesEqualTo(404),"type",Matchers.containsString("unknown"),"",Matchers.hasKey("message"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
