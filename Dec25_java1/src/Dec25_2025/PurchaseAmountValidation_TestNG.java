package Dec25_2025;

import org.testng.annotations.Test;

import files.payload_library;
import io.restassured.path.json.JsonPath;

public class PurchaseAmountValidation_TestNG {

	//Verify if Sum of all Course prices matches with Purchase Amount	
	@Test
	public void SumofCourse()
	{
		JsonPath js= new JsonPath(payload_library.CoursePrice());
		int course_count=js.getInt("courses.size()");
		int sum = 0;
		
		for (int i = 0; i < course_count; i++) {
		    int price = js.getInt("courses[" + i + "].price");
		    int copiesSold = js.getInt("courses[" + i + "].copies");
		    sum = sum + (price * copiesSold);
		}
		System.out.println("Sum of all the course prices are " + sum);
	}
}
