package shopperStack;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

public class Login {
	
	Object token;
	Object userId;
	Object productId;
	
	@Test
	public void login()
	{
		
		HashMap reqBody = new HashMap();
		
		reqBody.put("email","sunilkumar@gmail.com");
		
		reqBody.put("password","Sunil@17");
		
		reqBody.put("role","SHOPPER");
		
		String ResponseBody = RestAssured.given().contentType(ContentType.JSON).body(reqBody)
		.when().post("https://www.shoppersstack.com/shopping/users/login")
		.then().assertThat().statusCode(200).log().all().extract().response().asString();
		
		JsonPath js = new JsonPath(ResponseBody);
		
		userId = js.get("data.userId");
		
		token = js.get("data.jwtToken");
		
		System.out.println("Token: "+token);
		
		System.out.println("UserID: "+userId);
		
		
	}
	
	@Test(priority =1)
	public void getProduct()
	{
		String ResponseBody = RestAssured.given()
		.when().get("https://www.shoppersstack.com/shopping/products/alpha")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = new JsonPath(ResponseBody);
		productId = js.get("data[4].productId");
		
		System.out.println("ProductID: "+productId);
	
	}
	
	
	@Test(priority = 2)
	public void addProductToWishList()
	{
		HashMap ReqBody = new HashMap();
		
		ReqBody.put("productId",productId);
		
		ReqBody.put("quantity",2);
		
		RestAssured.given().contentType(ContentType.JSON).body(ReqBody).header("Authorization","Bearer "+token)
		.when().post("https://www.shoppersstack.com/shopping/shoppers/"+userId+"/wishlist")
		.then().assertThat().statusCode(201).log().all();
		
	}
	

}
