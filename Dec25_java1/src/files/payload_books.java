package files;

public class payload_books {
	public static String Addbook(String isbn, String aisle)
	{
		return "{\r\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\"Ramen\"\r\n"
				+ "}\r\n"
				+ "";
	}
	
	
	public static String Deletebook(String book_id) {
		return "{ \r\n"
				+ "\"ID\" : \""+book_id+"\"\r\n"
				+ "} \r\n"
				+ "";
		
	}
}
