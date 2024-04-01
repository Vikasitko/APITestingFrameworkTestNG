package api.endpoints;
import static io.restassured.RestAssured.given;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class UserEndPoints {
	
	/*curl -X 'POST' \
	  'https://petstore.swagger.io/v2/user' \
	  -H 'accept: application/json' \
	  -H 'Content-Type: application/json' \
	  -d '{
	  "id": 0,
	  "username": "string",
	  "firstName": "string",
	  "lastName": "string",
	  "email": "string",
	  "password": "string",
	  "phone": "string",
	  "userStatus": 0
	}'*/
	
	public static Response createUser(User payload)
	{
		Response response =given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.body(payload)
		
		.when()
		.post(Routes.post_url);
		return response;
	}
	public static Response getUser(String userName)
	{
		Response response =given()
		.accept(ContentType.JSON)
		
		.pathParam("username", userName)
		
		
		.when()
		.get(Routes.get_url);
		return response;
	}
	
	public static Response updateUser(String userName, User payload)
	{
		Response response =given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.pathParam("username", userName)
		.body(payload)
		.when()
		.put(Routes.put_url);
		return response;
	}
	
	public static Response deleteUser(String userName)
	{
		Response response =given()
		.accept(ContentType.JSON) //it is header
		.pathParam("username", userName)
	
		.when()
		.delete(Routes.del_url);
		return response;
	}



}
