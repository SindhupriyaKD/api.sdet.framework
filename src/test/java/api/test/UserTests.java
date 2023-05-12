package api.test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.Project;
import io.restassured.response.Response;

public class UserTests {
	
	Project projectpayload;
	
	@BeforeClass
	public void setProjectData() {
		Random random= new Random();
		String pName = "sindhu"+random.nextInt(500);
		projectpayload= new Project("skafhudjgj", pName, "completed", 10);
	}
	
	@Test(priority = 1)
	public void testCreateProject(ITestContext context)
	{
		Response response = UserEndPoints.createProject(projectpayload);
		String pid = response.jsonPath().get("projectId");
		int actualStatusCode = response.getStatusCode();
		response.then().assertThat().statusCode(actualStatusCode)
		.time(Matchers.lessThan(1000l), TimeUnit.MILLISECONDS).log().all();
		context.setAttribute("projectId", pid);
		System.out.println(context.getAttribute("projectId"));
	}
	
	@Test(priority = 2)
	public void testGetAllProject() {
		Response response = UserEndPoints.getAllProject();
		String value = response.getHeader("Content-Type");
		response.then().assertThat().statusCode(200);
		response.then().assertThat().header("Content-Type", value).log().body();
	}
	
	@Test(priority = 3)
	public void testGetSingleproject(ITestContext context) {
		
		//getAttribute returns Object Type so typecasting it to String
		String pid =  context.getAttribute("projectId").toString();
		Response response = UserEndPoints.getSingleproject(pid);
		response.then().log().all();
		
	}
	
	@Test(priority = 4)
	public void testUpdateProject(ITestContext context)
	{
		String pid =  context.getAttribute("projectId").toString();
		Response response = UserEndPoints.updateSingleProject(pid);
		response.then().log().all();
	}
	
	@Test(priority = 5)
	public void testDeleteProject(ITestContext context)
	{
		Response response = UserEndPoints.deleteProject(context.getAttribute("projectId").toString());
		response.then().assertThat().statusCode(204);
		response.then().log().all();
	}
	

}
