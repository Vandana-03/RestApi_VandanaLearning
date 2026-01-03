package Jan_2025_code;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import files.payload_AddPlace;
import io.restassured.RestAssured;

public class AddPlace_03_externalfile {

	public static void main(String[] args) throws IOException {
		//Validate if add place api is working or no
		//given... all input details
		//when .....submit the api, resource , https method
		//then.....validate the response
		//You are using file path to read the body from external file
		//First, you need to convert the file to a string-->Then the content of the file can be converted into bytes-->Then byte data to string.
	
		RestAssured.baseURI="https://rahulshettyacademy.com";
		// ADD PLACE API
		System.out.println("*********ADD PLACE API********");
		String response = given().log().all()
							.queryParam("key", "qaclick123")
							.header("Content-Type","application/json").
							body(new String(Files.readAllBytes(Paths.get("C:\\Users\\Hp\\git\\RestApi_VandanaLearning\\Dec25_java1\\src\\files\\addPlace.json.txt"))))
						.when().post("maps/api/place/add/json")
						.then().assertThat().statusCode(200)
							.body("scope", equalTo("APP"))
							.header("server", "Apache/2.4.52 (Ubuntu)")
							.extract().response().asString();
		
		System.out.println(response);
	

	}

}
