package Jan_2025_code;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.payload_books;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Addbook_01 {
	
	@Test(dataProvider= "Books_dynamic_data")
	public void addbook(String isbn, String aisle)
	{
		RestAssured.baseURI="http://216.10.245.166";
		String addbook_response=given().log().all().
									header("Content-Type","application/json").
									body(payload_books.Addbook(isbn,aisle))	
								.when().post("Library/Addbook.php")
								.then().assertThat().
									statusCode(200).
									extract().response().asString();
		System.out.println(addbook_response);
		
		//to parse the response body to fetch if the book id is available or no
		JsonPath js=new JsonPath(addbook_response);
		String book_id=js.getString("ID");
		System.out.println("book_id =" + book_id);
		
		//Delete the added book
		/*
		String deletebook_response=given().header("Content-Type","application/json").
											body(payload_books.Deletebook(book_id))
										.when().post("Library/DeleteBook.php")
										.then().log().all().
											assertThat().statusCode(200).
											extract().response().toString();
		//System.out.println(deletebook_response);
		*/
	}
	 
	
	@DataProvider(name="Books_dynamic_data")
	public Object[][] Addbook_data()
	{
		//lets create an array of 7 books to pass dynamically to Addbook() 
		return new Object[][] {{"abcd","1233"},{"abcde","12334"},{"qwer","2435"},{"ertr","4566"},{"yuyu","1233"},{"uiio","9878"},{"pooik","9786"}};
	}
}
