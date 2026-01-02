package Jan_2025_code;

import org.testng.annotations.Test;

import files.payload_books;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Addbook {
	
	@Test
	public void addbook()
	{
		RestAssured.baseURI="http://216.10.245.166";
		String response=given().log().all().
		header("Content-Type","application/json").
		body(payload_books.Addbook())	
		
		.when().post("Library/Addbook.php")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(response);
		
		//to parse the response body to fetch if the book id is available or no
		JsonPath js=new JsonPath(response);
		String book_id=js.getString("ID");
		System.out.println("book_id =" + book_id);
				
	}
}
