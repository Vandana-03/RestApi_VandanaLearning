package Jan_2025_code;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;


public class Oath_api_1 {

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
		String outh_GetCourseDetails=given().queryParam("access_token", access_token).
		when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails?access_token=OlADvLIGmTLdCtCydvJLlA==").
		then().log().all().extract().response().asString();
		System.out.print("outh_GetCourseDetails" + outh_GetCourseDetails);
		

	}

}
