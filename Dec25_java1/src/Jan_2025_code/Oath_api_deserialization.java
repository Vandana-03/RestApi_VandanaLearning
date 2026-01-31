package Jan_2025_code;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import Serialization_deserialization_POJO_classes.Api;
import Serialization_deserialization_POJO_classes.Get_course_details;
import Serialization_deserialization_POJO_classes.Webautomation;
import io.restassured.path.json.JsonPath;

public class Oath_api_deserialization {

	public static void main(String[] args) {
		///Authorization server setup
		String Outh_post_request=given().
			formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
			.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
			.formParam("grant_type", "client_credentials").formParam("scope", "trust")
		
		.when().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
		.then().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js=new JsonPath(Outh_post_request);
		String access_token=js.getString("access_token");
		System.out.println("Access token=" + access_token);
		
		//GetCourseDetails API
		Get_course_details gc=given().queryParam("access_token", access_token).
		when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").
		then().log().all().statusCode(401).extract().response().as(Get_course_details.class);
		
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getInstructor());
		
		//get the price of SoapUI Webservices testing from courses-->api-->coursetitle-->price
		
		System.out.println(gc.getCourses().getApi().get(1).getPrice());
		
		// in order to get the price we can't rely on the indexing because index can change over time
		//So we need to take all the list elements and then search for the course title and get the price
		
		List<Api> apicourses = gc.getCourses().getApi();
		for(int i=0; i<apicourses.size();i++)
		{
			if(apicourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
			{
				System.out.println("Price of SoapUI Webservices testing book is = " + apicourses.get(i).getPrice());
			}
		}
		
		//Get all the coursetitles from webautomation 
		
		List<Webautomation> webautomation_courses = gc.getCourses().getWebAutomation();
		for (int i=0; i<webautomation_courses.size();i++)
		{
			System.out.println(webautomation_courses.get(i).getCourseTitle());
		}
		
		// Real time use case compare actual vs expected results
		System.out.println("************compare actual vs expected results***********");
		//declare the expected result in a string of array format
		//Use Arraylist since the api response of array is dynamic in nature
		
		//This is array
		String[] expected_Webautomation_getCourseTitle= {"Selenium Webdriver Java","Cypress","Protractor"};
				//Now we need to compare actual arraylist and expected array list
				//it is difficult to compare array and arraylist
				//so we convert expected_Webautomation_getCourseTitle to arraylist
		List<String> expected=Arrays.asList(expected_Webautomation_getCourseTitle);
		
		//get the actual coursetitle from the api
		ArrayList<String> a=new ArrayList<String>();
		//get all the course titles
		for(int i=0;i<webautomation_courses.size();i++)
		{		
			//webautomation_courses.get(i).getCourseTitle();
			//add this course titles to arraylist
			a.add(webautomation_courses.get(i).getCourseTitle());
		}

		Assert.assertEquals(a, expected);
	}

}
