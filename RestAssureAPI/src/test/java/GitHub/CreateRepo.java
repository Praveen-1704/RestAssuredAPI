package GitHub;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static org.hamcrest.Matchers.*;

public class CreateRepo {

	
	@Test(priority = 1)
	public void createRepo()
	{
		HashMap reqBody = new HashMap();
		
		reqBody.put("name","KGF2");
		reqBody.put("private","true");
		
		
		RestAssured.given().body(reqBody).header("Authorization","bearer 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")
		.when().post("https://api.github.com/user/repos")
		.then().assertThat().statusCode(201)
		.body("name",equalTo("KGF2"));
		
	}
	
	@Test(priority = 2)
	public void deleteRepo()
	{
		RestAssured.given().header("Authorization","bearer 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")
		.when().delete("https://api.github.com/repos/Praveen-1704/KGF2")
		.then().assertThat().statusCode(204);
	}
}
