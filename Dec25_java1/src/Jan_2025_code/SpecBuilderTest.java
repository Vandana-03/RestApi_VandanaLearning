package Jan_2025_code;
import static io.restassured.RestAssured.*;
import java.util.ArrayList;
import java.util.List;
import Serialize_POJO_classes.AddPlace;
import Serialize_POJO_classes.Location;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilderTest {
	public static void main(String[] args) {
				
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
		String response= 
				  given()
					.spec(req)
					.body(ap)
				  .when()
				  	.post("maps/api/place/add/json")
				  .then().spec(resp).extract().response().asString();
		System.out.println("*****" + response);
	
	
		
	}

}
