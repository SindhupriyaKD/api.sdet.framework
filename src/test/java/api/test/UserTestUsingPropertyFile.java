package api.test;

import java.util.ResourceBundle;

import org.testng.annotations.Test;

import api.payload.Project;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class UserTestUsingPropertyFile {
	
	@Test
	public void create()
	{
		ResourceBundle route = ResourceBundle.getBundle("routes");
		System.out.println(route.getString("post_url"));
		Project data= new Project("dshf", "ds67u", "completed", 23);
		
		given()
			.contentType(ContentType.JSON)
			.body(data)
		
		.when()
			.post(route.getString("post_url"))
		
		.then()
			.assertThat().statusCode(201)
			.log().all();
	}

}
