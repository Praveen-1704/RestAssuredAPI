package Regres;

import java.util.HashMap;
import java.util.Set;

import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Regres {
	
	@Test
	
	public void singleUser()
	{
		String ResponseBody = RestAssured.given()
		.when().get("https://reqres.in/api/users/2")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = new JsonPath(ResponseBody);
		int id = js.get("data.id");
		System.out.println(id);
	}
	
	
	@Test
	public void SingleUserNotFound()
	{
		RestAssured.given()
		.when().get("https://reqres.in/api/users/23")
		.then().assertThat().statusCode(404);	
	}
	
	
	@Test
	public void listOfResources()
	{
		RestAssured.given()
		.when().get("https://reqres.in/api/users?page=2")
		.then().assertThat().statusCode(200).log().all();
	}
	
	@Test
	
	public void singleResource()
	{
		String responseBody = RestAssured.given()
		.when().get("https://reqres.in/api/unknown/2")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = new JsonPath(responseBody);
		String name = js.get("data.name");
		Reporter.log("The name is: "+name,true);
	}
	
	@Test
	
	public void singleResourceNotFound()
	{
		RestAssured.given()
		.when().get("https://reqres.in/api/unknown/23")
		.then().assertThat().statusCode(404);
	}
	
	@Test
	
	public void CreateUser()
	{
		HashMap requestBody = new HashMap();
		
		requestBody.put("name","morpheus");
		requestBody.put("job","leader");
		
		String responseBody = RestAssured.given().body(requestBody)
		.when().post("https://reqres.in/api/users")
		.then().assertThat().statusCode(201).log().all().extract().response().asString();
		
		JsonPath js = new JsonPath(responseBody);
		String name = js.get("name");
		
		System.out.println(name);
		
		String name1 = null;
		
		Set s = requestBody.keySet();
		for(Object o:s)
		{
			if(o.equals(name))
			{
				name1=(String) requestBody.get(o);
			}
		}
		
		System.out.println(name.equals(name1));
	}
	

}
