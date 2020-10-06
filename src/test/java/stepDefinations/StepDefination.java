package stepDefinations;
import static io.restassured.RestAssured.given;



import java.io.IOException;

import static org.junit.Assert.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils{

	ResponseSpecification resspec;
	RequestSpecification res;
	Response response;
	String resp;
	TestDataBuild data=new TestDataBuild();
	static String place_id;
	
	@Given("Add Place Payload {string} {string} {string}")
	public void add_Place_Payload(String name, String language, String address) throws IOException {

	res=given().spec(requestspecification()).body(data.addplacepayload(name,language,address));
	}

	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
	    // Write code here that turns the phrase above into concrete actions
		APIResources resourceAPI=APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		
		resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("POST")) 
		response=res.when().post(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("GET"))
	    response=res.when().get(resourceAPI.getResource());		
				
	}

	@Then("API call is success with status code {int}")
	public void api_call_is_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	 assertEquals( response.getStatusCode(),200);
	}

	@Then("{string} in reponse body is {string}")
	public void in_reponse_body_is(String keyvalue, String expectedvalue) {
	    // Write code here that turns the phrase above into concrete actions
		
		assertEquals(getjsonpath(response, keyvalue),expectedvalue);
	}
	
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedname, String resource) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		 place_id=getjsonpath(response,"place_id");
	 res= given().spec(requestspecification()).queryParam("place_id",place_id);
	   user_calls_with_http_request(resource,"GET");
	   String actualname=getjsonpath(response,"name");
	   assertEquals(actualname,expectedname);
	   
	}
	
	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	   
		res =given().spec(requestspecification()).body(data.deletePlacePayload(place_id));
	}

	


	




	

}
