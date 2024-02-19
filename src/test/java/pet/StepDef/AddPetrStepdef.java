package pet.StepDef;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import io.cucumber.java.an.E;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import pet.Pojo.AddPetsPojo;
import pet.RequestSpec;

import java.io.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

public class AddPetrStepdef {
  private AddPetsPojo pojo=AddPetsPojo.getInstance();

    @Given("set the token")
    public void set_the_token() {
        RequestSpec.getInstance().setRequestSpec();
    }
    @When("buillt the payload with following details id {string} and name {string} and status {string}")
    public void buillt_the_payload_with_following_details_id_and_name_and_status(String id, String name, String status) {
    if(id!=null && !name.isEmpty() && !status.isEmpty())
    {
        pojo.setId(Integer.parseInt(id));
        pojo.setName(name);
        pojo.setStatus(status);
    }
    }
    @Then("add the pet category")
    public void add_the_pet_category(io.cucumber.datatable.DataTable dataTable) {
     List<Map<String, Object>>datas= dataTable.asMaps(String.class,Object.class);
     Map<String,Object>category=new LinkedHashMap<>();
     for (Map<String,Object>map:datas)
     {
         for (String key:map.keySet())
         {
             switch (key)
             {
                 case "id":if (!map.get(key).toString().isEmpty())category.put(key,map.get(key));else throw new RuntimeException("id not available in category map");
                 break;
                 case "name":if (!map.get(key).toString().isEmpty())category.put(key,map.get(key)); else throw new RuntimeException("name not available in categoru");
             }
         }
     }
       pojo.setCategory(category);
    }
    @Then("add photo urls")
    public void add_photo_urls(io.cucumber.datatable.DataTable dataTable) {
      List<Map<String,String>>datas= dataTable.asMaps(String.class,String.class);
      List<String> photo_urls=new ArrayList<>();
      for (Map<String,String>map:datas)
      {
         for (String key:map.keySet())
         {
             photo_urls.add(map.get(key));
         }
      }
        this.pojo.setPhotoUrls(photo_urls);
    }
    @Then("add the tags")
    public void add_the_tags(io.cucumber.datatable.DataTable dataTable) {
    List<Map<String,Object>>datas= dataTable.asMaps(String.class,Object.class);
    int flag=0;
    for (Map<String,Object>map:datas)
    {
        Map<String,Object>m=new LinkedHashMap<>();
        for (String key:map.keySet())
        {
            switch (key)
            {
                case "id":if (!map.get(key).toString().isEmpty()) flag=1;else throw new RuntimeException("id not found  in tags array");
                break;
                case "name":if (!map.get(key).toString().isEmpty())flag=1;else throw new RuntimeException("name is not found in array");
                break;
            }
        }
        if (flag==1)
        {
            pojo.setTags(datas);
        }
    }
    }
String payload;
    @Then("generate the pet payload")
    public void generateThePetPayload() {
        ObjectMapper mapper=new ObjectMapper();
        try
        {
            this.payload=mapper.writerWithDefaultPrettyPrinter().writeValueAsString(pojo);
           // System.out.println(payload);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Then("validate the schema of the payload")
    public void validateTheSchemaOfThePayload() {
      try {
          InputStream inputStream=new FileInputStream("src/main/resources/addPet.json");
          ObjectMapper mapper=new ObjectMapper();
         JsonNode node= mapper.readTree(this.payload);
        Set<ValidationMessage> message= JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4)
                  .getSchema(inputStream).validate(node);
        if (message.isEmpty())
        {
            System.out.println("schema validation completed");
        }
        else {
            for (ValidationMessage v:message)
            {
                System.out.print(v.getMessage());
            }
        }
      }
      catch (Exception e)
      {
          e.printStackTrace();
      }
    }

    @Then("trigger the POST request to add resource")
    public void triggerThePOSTRequestToAddResource() throws IOException {
        FileOutputStream  outputStream = new FileOutputStream("src/main/resources/evidences/addUser.json",true);
       Instant instant= Instant.now();
        ZoneId zoneId=ZoneId.of("Asia/Kolkata");
        ZonedDateTime zonedDateTime=ZonedDateTime.ofInstant(instant,zoneId);
        outputStream.write(zonedDateTime.toString().getBytes());
        given(requestSpecification)
                .contentType(ContentType.JSON)
                .filter(ResponseLoggingFilter.logResponseTo(new PrintStream(outputStream), LogDetail.BODY))
                .body(this.payload)
                .when().post("/pet")
                .then().assertThat().statusCode(200).body("", Matchers.hasValue(pojo.getId()));

    }
}
