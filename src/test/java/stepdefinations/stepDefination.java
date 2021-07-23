package stepdefinations;

import static io.restassured.RestAssured.given;
import Resources.Utils;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;

import Resources.Apiresource;
import Resources.TestDataBuild;
import Resources.Utils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Addplace1;
import pojo.Location;

@RunWith(Cucumber.class)
public class stepDefination  extends Utils{
	Addplace1 p;
	RequestSpecification req;
	Response response ;
	ResponseSpecification resspec;	
	RequestSpecification res;
	String 	responseString;
	JsonPath js;
	String resource;
	static String place_id;
	
	TestDataBuild td = new TestDataBuild();
	
	
	@Given("^Add place Payload with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void add_place_Payload(String name, String language, String address) throws Exception {
		
	 res =given().spec(requestSpecification())
		.body(td.addPlacePayLoad(name, language, address));
		
	
		
	    
	}

	@When("^User calls \"([^\"]*)\" with \"([^\"]*)\" Http Request$")
	public void user_calls_with_post_Http_Request(String resource, String method) throws Exception {
		System.out.println("when is executed with:"+ resource);
		//Calling Constructor with value of resource from class Apiresource
		
		Apiresource resourceAPI=Apiresource.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		
		resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();	

		
		if(method.equalsIgnoreCase("POST"))
		{
			response= res.when().post(resourceAPI.getResource());
		}
		else if(method.equalsIgnoreCase("GET"))
		{
			response= res.when().get(resourceAPI.getResource());
		}

   
	    
	}

	@Then("^the APi call success eith status code (\\d+)$")
	public void the_APi_call_success_eith_status_code(int arg1) throws Exception {
		System.out.println("then is executed  "+ response.statusCode());
		assertEquals(response.statusCode(), 200);
	   
	}

	@Then("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	public void in_response_body_is(String Keyvalue, String expectedvalue) throws Exception {
	    // Write code here that turns the phrthrow new PendingException();
		
		   

	  String ss= getJsonpath(response, Keyvalue);
	       
	    assertEquals(ss, expectedvalue);
	       

	}
	@Then("^Verify place_Id create maps to \"([^\"]*)\" using \"([^\"]*)\"$")
	public void verify_place_Id_create_maps_to_using(String expectedname, String Apiname) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		
		
	
		place_id=getJsonpath(response, "place_id");
		
		res=given().spec(requestSpecification()).queryParam("place_id", place_id);
		System.out.println("-------------"+ res + resource);
		user_calls_with_post_Http_Request(Apiname, "GET");
		
		String nameres=getJsonpath(response, "name");
		assertEquals(nameres, expectedname);
	   
	}
	
	@Given("^Deleteplace Payload$")
	public void deleteplace_Payload() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		
		System.out.println("---------Placeid value is"+ place_id);
	   res= given().spec(requestSpecification()).body("{\n"
				+ "    \"place_id\":\""+place_id+"\"\n"
				+ "}");
	}



}
