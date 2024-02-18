package pet;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.LogDetail;

import static io.restassured.RestAssured.*;

public class RequestSpec {
    private static RequestSpec requestSpec;

   private RequestSpec()
    {

    }
    public static RequestSpec getInstance()
    {
        if (requestSpec==null)
        {
            requestSpec=new RequestSpec();
            return requestSpec;
        }
        else
        {
            return requestSpec;
        }
    }
    public void setRequestSpec()
    {
        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder();
      requestSpecification =  requestSpecBuilder.setBaseUri("https://petstore.swagger.io")
                .setBasePath("/v2")
                .setAuth(oauth2("20f6bdf3-778c-4c46-b221-4260f5ddba84"))
                .setConfig(config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .build();
    }
}
