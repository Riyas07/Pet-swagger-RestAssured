package pet.util;

import io.cucumber.java.an.E;
import io.restassured.config.EncoderConfig;
import io.restassured.config.LogConfig;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

import java.io.InputStream;

public class ApiManager {
    private ApiManager() {}

    static ApiManager apiManager=new ApiManager();
    public static ApiManager getInstance()
    {
        return apiManager;
    }
    public InputStream get_download(String url)
    {
        return given()
                .contentType(ContentType.MULTIPART)
                .config(config().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .when().get(url)
                .asInputStream();
    }
}
