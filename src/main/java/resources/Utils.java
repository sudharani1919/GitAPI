package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyData;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification req;
	public RequestSpecification requestspecification() throws IOException
	{
		if(req==null)
		{
           
		PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));
		
		 req=new RequestSpecBuilder().setBaseUri(getGlobalvalue("baseurl")).addQueryParam("key", "qaclick123")
		.addFilter(RequestLoggingFilter.logRequestTo(log))
		.addFilter(ResponseLoggingFilter.logResponseTo(log))
		.setContentType(ContentType.JSON).build();
          return req;
		}
		return req;
	}
          
    //here loggingfilter is used to generate logs such as output of request and response
          
          public static String getGlobalvalue(String key) throws IOException
          {
        	  Properties prop=new Properties();
        	  FileInputStream fis=new  FileInputStream("C:\\API Automation Workspace\\API\\src\\main\\java\\resources\\globalvalue.properties");
        	  prop.load(fis);
        	  return prop.getProperty(key);
        	 
          }
          
          public String  getjsonpath(Response response,String key)
          {
        	 String resp=response.asString();
      		JsonPath js=new JsonPath(resp);
      		 return js.get(key).toString();
      		
          }
	
	
}
