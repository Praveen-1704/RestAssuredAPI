package Regres;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class ListUsers {
	
	@Test
	public void listuser()
	{
		RestAssured.given().log().all()
		.when().get("https://reqres.in/api/users?page=2")
		.then().assertThat().statusCode(200).log().all();
	}
	
//	@Test
//	public void CreateUser()
//	{
//		RestAssured.given().body("{\r\n"
//				+ "    \"name\": \"morpheus\",\r\n"
//				+ "    \"job\": \"leader\"\r\n"
//				+ "}").log().all()
//		.when().post("https://reqres.in/api/users")
//		.then().assertThat().statusCode(201).log().all();
//	}
	
	@Test
	public void CreateUser()
	{
		HashMap RequestBody = new HashMap();
		
		RequestBody.put("name", "morpheus12");
		RequestBody.put("job","leader");
		
		RestAssured.given().body(RequestBody)
		.when().post("https://reqres.in/api/users")
		.then().assertThat().statusCode(201).log().all();
	}
}
