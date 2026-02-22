package Stepdefinitions;

import static io.restassured.RestAssured.given;
//import static org.testng.Assert.assertEquals;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import POJO.AddPlace;
import POJO.Location;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.cucumber.java.en.Then;

public class StepDefinition {
	RequestSpecification res;
	Response response;	
	
		@Given("Add place Payload")
	public void add_place_payload() {
	    // Write code here that turns the phrase above into concrete actions
			AddPlace ap= new AddPlace();
			List<String> typelist=new ArrayList<String>();
			typelist.add("shoe park");
			typelist.add("shop");
			typelist.add(0, "shoe park");
			
			ap.setAccuracy(30);
			ap.setName("Frontline house99");
			ap.setPhone_number("(+91) 983 893 3937");
			ap.setAddress("99, right layout, valmount 99");
			ap.setTypes(typelist);
			ap.setWebsite("https://www.youtube.com");
			ap.setLanguage("German");	
			
			Location l=new Location();
			l.setLat(-39.383494);
			l.setLat(33.333333);
			ap.setLocation(l);
					
			// Build Request Specification
			RequestSpecification req=new RequestSpecBuilder()
					.setBaseUri("https://rahulshettyacademy.com")
					.addQueryParam("key","qaclick123")
					.setContentType(ContentType.JSON)
					.build();
			// Build Response Specification
			ResponseSpecification resp=new ResponseSpecBuilder()
					.expectStatusCode(200)
					.expectContentType(ContentType.JSON)
					.build();
			// API call
			 res= 
					  given()
						.spec(req)
						.body(ap);
			
	}
	@When("User calls the {string} with a POST https request")
	public void user_calls_the_with_a_post_https_request(String string) {
		
		 response= res.when()
			  	.post("maps/api/place/add/json");
	}
	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {
	    
		//.then().spec(resp).extract().response().asString();
		response.then().assertThat().statusCode(200);
	}
	
	
	
	@Then("{string} in the response body is {string}")
	public void in_the_response_body_is(String Actualkey, String ExpectedValue) {
		String resp_body=response.asString();
		JsonPath js=new JsonPath(resp_body);
		assertEquals(js.get(Actualkey).toString(),ExpectedValue);
			
	}
}
