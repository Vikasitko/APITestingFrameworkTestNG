package api.testcases;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPointsToreadURLSFromPropertiesFile;
import api.payload.User;
import io.restassured.response.Response;




public class UserTest2CreatedtotakeURLSfromPropertiesFile {
	
	Faker faker;
	User userPayload;
	 public static Logger Logger;
	
	@BeforeClass
	public void generateTestData()
	{
		faker = new Faker();
		userPayload = new User();
		
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		Logger = LogManager.getLogger("RestAssuredAPIFramework");
		
	}
	@Test(priority=1)
	public void createTestUser()
	{
		Response response  = UserEndPointsToreadURLSFromPropertiesFile.createUser(userPayload);
		
		System.out.println("Create user data");
		//log response
		response.then().log().all();
		
		//Validation
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		
		Logger.info("Create User executed");
	}

	@Test(priority=2)
	public void testGetUserData()
	{
		Response response  = UserEndPointsToreadURLSFromPropertiesFile.GetUser(this.userPayload.getUsername());
		
		System.out.println("Read user data");
		//log response
		response.then().log().all();
		
		//Validation
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Logger.info("Create User got");
	}
	
	@Test(priority=3)
	public void testUpdateUser()
	{
		userPayload.setFirstName(faker.name().firstName());
		Response response  = UserEndPointsToreadURLSFromPropertiesFile.UpdateUser(this.userPayload.getUsername(),userPayload);
		
		System.out.println("Update user data");
		//log response
		response.then().log().all();
		
		//Validation
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		
		//Read  user data to check if first name is updated or not
		
		Response responsePostUpdate  = UserEndPointsToreadURLSFromPropertiesFile.GetUser(this.userPayload.getUsername());
		System.out.println("After Update user data");
		responsePostUpdate.then().log().all();
		Logger.info("Create User updated");
	}
	
	@Test(priority=4)
	public void testDeleteUser()
	{
		userPayload.setFirstName(faker.name().firstName());
		Response response  = UserEndPointsToreadURLSFromPropertiesFile.DeleteUser(this.userPayload.getUsername());
		
		System.out.println("Delete user data");
		//log response
		response.then().log().all();
		
		//Validation
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Logger.info("Create User deleted");
	}

}
