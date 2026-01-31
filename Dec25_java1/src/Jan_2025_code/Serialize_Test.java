package Jan_2025_code;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import Serialize_POJO_classes.AddPlace;
import Serialize_POJO_classes.Location;
import io.restassured.RestAssured;


public class Serialize_Test {
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
				
RestAssured.baseURI="https://rahulshettyacademy.com";
		
		String request=given().log().all().
								queryParam("key","qaclick123").
								body(ap).
								
						when().post("maps/api/place/add/json").
						then().log().all().statusCode(200).extract().response().asString();		
	}

}
