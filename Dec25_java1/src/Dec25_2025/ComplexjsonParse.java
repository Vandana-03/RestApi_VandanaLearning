package Dec25_2025;

import files.payload_library;
import io.restassured.path.json.JsonPath;

public class ComplexjsonParse {
	public static void main(String[] args) {
		JsonPath js1= new JsonPath(payload_library.CoursePrice());
		
		//Print No of courses returned by API
		int no_of_courses=js1.getInt("courses.size()");
		System.out.println("Total no of courses are= " + no_of_courses);
		
		//Print Purchase Amount
		int purchase_amount=js1.getInt("dashboard.purchaseAmount");
		System.out.println("purchase_amount=" + purchase_amount);
		
		//Print Title of the first course
		String title_course1=js1.getString("courses[0].title");
		System.out.println("Course1 title= " + title_course1);
		
		//Print All course titles and their respective Prices
		for(int i=0; i<no_of_courses;i++)
		{	
			String title=js1.getString("courses["+i+"].title");
			int price=js1.getInt("courses["+i+"].price");
			System.out.println(title + price);
		}
		// if you want to convert to single line sysout statement for line no 25, 26
		//System.out.println(js1.getInt("courses["+i+"].price").toString());
		
		System.out.println("*************");
		//Print no of copies sold by RPA Course
		//Another way to print the no of copies sold by RPA course by matching the title name of the course
		for(int i=0;i<no_of_courses;i++)
		{
			String titlename=js1.getString("courses["+i+"].title");
			if(titlename.equalsIgnoreCase("RPA"))
			{
				int copies=js1.getInt("courses["+i+"].copies");
				System.out.println("no of copies sold by RPA course is: " +copies);
				break;
			}			
		}
		
		//Verify if Sum of all Course prices matches with Purchase Amount		
		int sum = 0;
		for (int i = 0; i < no_of_courses; i++) {
		    int price = js1.getInt("courses[" + i + "].price");
		    int copiesSold = js1.getInt("courses[" + i + "].copies");
		    sum = sum + (price * copiesSold);
		}
		System.out.println("Sum of all the course prices are " + sum);
		if(sum==purchase_amount)
			System.out.println("Sum of all the course prices is equal to the purchase amount");
		else

			System.out.println("The sum of all course prices is not equal to the purchase amount");
		}
}
