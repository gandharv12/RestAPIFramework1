package stepdefinations;



import cucumber.api.java.Before;

public class Hooks {
	
	
	@Before("@DeletePlace")
	public void beforescenario() throws Exception
	{
		//execute this code when place id is null
		//write a code which will give you a placeid
		stepDefination m= new stepDefination();
		
		if (stepDefination.place_id==null)
		{
		m.add_place_Payload("Rolo", "Spanish", "897 NYC st rd");
		m.user_calls_with_post_Http_Request("AddPlaceAPI", "post");
		m.verify_place_Id_create_maps_to_using("Rolo", "getPlaceAPI");
	}
	}

}
