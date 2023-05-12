package api.test;

import java.util.Random;

import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.Project;
import api.utilities.DataProviderClass;
import io.restassured.response.Response;

public class DDT {
	
	@Test(dataProvider = "data", dataProviderClass = DataProviderClass.class)
	public void dataDrivenTest(String createdBy, String projectName, String status, String teamSize)
	{
		Random ran= new Random();
		 Project data = new Project(createdBy, projectName+ran.nextInt(450), status, Integer.parseInt(teamSize));
		Response response = UserEndPoints.createProject(data);
		response.then().log().all();
		response.then().assertThat().statusCode(201);
	}
}
