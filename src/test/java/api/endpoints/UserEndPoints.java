package api.endpoints;
import static io.restassured.RestAssured.*;

import api.payload.Project;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {
	
	public static Response createProject(Project payload)
	{
		Response response = given()
			.contentType(ContentType.JSON)
			.body(payload)
		.when()
			.post(Routes.post_url);
		return response;
	}
	
	public static Response getAllProject()
	{
		Response response = given()
		.when()
		.get(Routes.GetAllProjects_url);
		return response;
	}
	
	public static Response getSingleproject(String pid) {
		Response response = given()
			.pathParam("pid", pid)
		
		.when()
			.get(Routes.GetOneProject_url);
		
		return response;
	}
	
	public static Response deleteProject(String pid) {
		Response response = given()
			.pathParam("pid", pid)
		
		.when()
			.delete(Routes.delete_url);
		return response;
	}
	
	public static Response updateSingleProject(String pid)
	{
		Response response = given()
			.pathParam("pid",pid)
		
		.when()
			.put(Routes.update_url);
		return response;
	}
	
	
	
	
	
	
	
	
	
}
