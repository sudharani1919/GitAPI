package resources;


import java.util.ArrayList;
import java.util.List;


import pojo.AddPlaceSereilization;
import pojo.SereilizationLocation;

public class TestDataBuild {

	public AddPlaceSereilization addplacepayload(String name, String language, String address)
	{
		AddPlaceSereilization p= new AddPlaceSereilization();
		p.setAccuracy(50);
		p.setAddress(address); 
		p.setLanguage(language);
		p.setPhone_number("5787873897");
		p.setWebsite("https://rahulshettyacademy.com");
		p.setName(name);
		
		List<String> myList=new ArrayList<String>();
		myList.add("shoe park");
		myList.add("park");
		p.setTypes(myList);
		SereilizationLocation l =new SereilizationLocation	();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		return p;
	}

   public String deletePlacePayload(String placeId)
   {
      return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
   }
}

