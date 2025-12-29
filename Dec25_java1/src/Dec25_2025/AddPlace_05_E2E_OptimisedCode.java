package Dec25_2025;

import static io.restassured.RestAssured.given;


import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import files.payload_AddPlace;
import files.payload_update_place;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class AddPlace_05_E2E_OptimisedCode {
	public static void main(String[] args) {
		//Validate if add place api is working or no
		//given... all input details
		//when .....submit the api, resource , https method
		//then.....validate the response
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		// ADD PLACE API
		System.out.println("*********************************");
		System.out.println("*********ADD PLACE API***********");
		System.out.println("*********************************");
		String response = given().log().all()
							.queryParam("key", "qaclick123")
							.header("Content-Type","application/json")
							.body(payload_AddPlace.AddPlace())
						.when().post("maps/api/place/add/json")
						.then().assertThat().statusCode(200)
							.body("scope", equalTo("APP"))
							.header("server", "Apache/2.4.52 (Ubuntu)")
							.extract().response().asString();
		
		System.out.println(response);
		
		// to parse the response body 
		JsonPath js=new JsonPath(response);
		String place_id=js.getString("place_id");
		System.out.println("place_id=" + place_id);
		
		//UPDATE PLACE API
		System.out.println("*********************************");
		System.out.println("*********UPDATE PLACE API********");
		System.out.println("*********************************");
		String newAddress="Summer walk USA johncity E24342";
		
		given().log().all().
		param("key", "qaclick123").
		header("Content-Type","application/json").
		body(payload_update_place.updateplace(place_id, newAddress))
		.when().put("/maps/api/place/update/json")
		.then().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		

		//GET PLACE API.... NEED TO CHECK IF THE ADDRESS HAS BEEN CHANGED
		System.out.println("**************************************");
		System.out.println("*************GET PLACE API************");
		System.out.println("**************************************");
		String getPlaceResponse=given().log().all().
									queryParam("key", "qaclick123").
									queryParam("place_id", place_id)
								.when().get("maps/api/place/get/json")
								.then().log().all().
									assertThat().
									statusCode(200).
									extract().response().asString();		
		
		JsonPath addr = new JsonPath(getPlaceResponse);
		String Actual_Address=addr.getString("address");
		System.out.println("Actual_Address is: " +Actual_Address);
		
		//TO COMPARE ADD PLACE ADDRESS == GET PLACE ADDRESS
		//WE NEED TO USE TESTNG OR JUNIT
		 
		
		Assert.assertEquals(Actual_Address, newAddress);
		
		
	}
}
