package Jan_2025_code;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class Jira_Createbug_attachscreenshot {
	
	public static void main(String[] args)
	{
		RestAssured.baseURI="https://aprateek040.atlassian.net";
		//CREATE A BUG IN JIRA
		String create_bug_response=given().log().all().
					header("Content-Type","application/json").
					header("Authorization","Basic YXByYXRlZWswNDBAZ21haWwuY29tOkFUQVRUM3hGZkdGMENhelpOZk1qMGhhMko0VmQtSHZWclRSNGFyU0hBNVBscUpVdXJReU9mWUxGMGxCaUY0NjQweERyMnFkaVc3TjVBTmxRT2Jrb0E2ajkwOFd6UTRPSzdzRG55bk92MmVqNmhDUDV5Mko3aEpEQUVSQW5Vbm5tZnl1enZwaUZfWjFOMDlhT1hjWU5YbDJkR1FVbU9lNUx3LWl1VjBxOEJDRTVtQlhYTGdjLXN2az01NjRCRTZCMA==").
					body("{\r\n"
							+ "  \"fields\": {\r\n"
							+ "    \"project\": { \"key\": \"AT\" },\r\n"
							+ "    \"summary\": \"new bug created3\",\r\n"
							+ "    \"description\": {\r\n"
							+ "      \"type\": \"doc\",\r\n"
							+ "      \"version\": 1,\r\n"
							+ "      \"content\": [\r\n"
							+ "        {\r\n"
							+ "          \"type\": \"paragraph\",\r\n"
							+ "          \"content\": [\r\n"
							+ "            {\r\n"
							+ "              \"type\": \"text\",\r\n"
							+ "              \"text\": \"Creating of an issue using project keys and issue type names using the REST API\"\r\n"
							+ "            }\r\n"
							+ "          ]\r\n"
							+ "        }\r\n"
							+ "      ]\r\n"
							+ "    },\r\n"
							+ "    \"issuetype\": { \"name\": \"Bug\" }\r\n"
							+ "  }\r\n"
							+ "}\r\n"
							+ "")
				.when().post("rest/api/3/issue")
				.then().log().all().
					assertThat().statusCode(201).
					extract().response().asString();
			System.out.println("create_bug_response= " + create_bug_response);
			
			
			JsonPath js=new JsonPath(create_bug_response);
			String bugid=js.getString("id");
			System.out.println("bugid =" + bugid);
			
		//ADD SCREENSHOT TO THE BUG
		//TO ADD A IMAGE ATTACHMENT WE ARE USING DIFF METHOD
		// We will be passing the bugid variable on the post url
			
			given().log().all().
				header("Authorization","Basic YXByYXRlZWswNDBAZ21haWwuY29tOkFUQVRUM3hGZkdGMENhelpOZk1qMGhhMko0VmQtSHZWclRSNGFyU0hBNVBscUpVdXJReU9mWUxGMGxCaUY0NjQweERyMnFkaVc3TjVBTmxRT2Jrb0E2ajkwOFd6UTRPSzdzRG55bk92MmVqNmhDUDV5Mko3aEpEQUVSQW5Vbm5tZnl1enZwaUZfWjFOMDlhT1hjWU5YbDJkR1FVbU9lNUx3LWl1VjBxOEJDRTVtQlhYTGdjLXN2az01NjRCRTZCMA==").
				header("X-Atlassian-Token","no-check").multiPart("file",new File("C:\\Users\\Hp\\git\\RestApi_VandanaLearning\\Dec25_java1\\src\\files\\attachment1.png"))
				
			.when().post("rest/api/3/issue/"+bugid+"/attachments")
			.then().assertThat().statusCode(200).log().all();
			
		
		
	}
}
