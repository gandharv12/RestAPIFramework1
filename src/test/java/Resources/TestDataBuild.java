package Resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Addplace1;
import pojo.Location;

public class TestDataBuild {
	
	public Addplace1 addPlacePayLoad( String name, String language, String address) {
		
		Addplace1 p = new Addplace1();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		p.setPhone_number("+76 99999983");
		p.setName(name);
		p.setWebsite("www.//sagdka9/");
		List<String> mylist = new ArrayList<String>();
		mylist.add("Xenophobeia11");
		mylist.add("implicit11");
			
			p.setTypes(mylist);
			
			
			Location loc = new Location();
			loc.setLat(-67.988744);
			loc.setLng(836984.333447);
			p.setLocation(loc);
			return p;
	}
	
	public String Deleteplacepayload(String place_Id)
	{
		return"{\n"
				+ "    \"place_id\":\""+place_Id+"\"\n"
				+ "}";
	}

}
