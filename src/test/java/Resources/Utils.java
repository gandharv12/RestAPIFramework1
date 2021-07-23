package Resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import javax.print.attribute.standard.PrinterState;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	public static RequestSpecification req;
	Response response ;
	
	public RequestSpecification requestSpecification() throws IOException
	{
	
if (req==null)
	
	{
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
	
		req=new RequestSpecBuilder().setBaseUri(getglobalValues("Baseurl")).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		
		
				return req;
	}
	return req;

}
	public static String getglobalValues(String key) throws IOException{
		Properties prop= new Properties();
		FileInputStream fis= new FileInputStream("src/test/java/Resources/global.properties");
		prop.load(fis);
		return prop.getProperty(key);
		
	}
	
	public String getJsonpath(Response response, String Key)
	
	{
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(Key).toString();
	}

}
