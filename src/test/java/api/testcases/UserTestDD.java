package api.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class UserTestDD {
	
	@Test(priority=1,dataProvider="Alldata",dataProviderClass= DataProviders.class)
	public void createTestUser(String userId,String userName, String fName, String lName, String email, String pwd, String phone )
	{
		
         User userPayload = new User();
		
		userPayload.setId(Integer.parseInt(userId));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fName);
		userPayload.setLastName(lName);
		userPayload.setEmail(email);
		userPayload.setPassword(pwd);
		userPayload.setPhone(phone);
		
		Response response  = UserEndPoints.createUser(userPayload);
		
		System.out.println("Create user data");
		//log response
		response.then().log().all();
		
		//Validation
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	

	@Test(priority=2,dataProvider="UserNameData",dataProviderClass= DataProviders.class)
	public void testDeleteUser(String username)
	{
		
		Response response  = UserEndPoints.deleteUser(username);
		
		System.out.println("Delete user data");
		//log response
		response.then().log().all();
		
		//Validation
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}


}
