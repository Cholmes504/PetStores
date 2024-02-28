package api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

//4 Tests. Post, Get, Update Delete

public class UserTest {
	 
	Faker faker;
	User userPayload;
	
	public Logger logger;
	
	
	@BeforeClass
	public void setupData() {
		//This class is used to generate Fake Data for Test
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
	
		//logs
		logger = LogManager.getLogger(this.getClass());
	
	}
	@Test(priority=1)
	public void testPostUser(){
		logger.info("********* CREATE USER ********");
		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("********* USER CREATED ********");
	}
	@Test(priority=2)
	
	public void testGetUserByName() {
		logger.info("********* READING USER INFO ********");
		
		Response response = UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.statusCode(),200);
		
		logger.info("********* USER INFO IS DISPLAYED ********");
	}
	
	@Test(priority=3)
	
	public void testUpdateUserByName() {
		
		logger.info("********* UPDATE USER ********");
		
		//update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("********* USER UPDATED ********");
		
		//Checking data after update
		Response responseAfterupdate = UserEndPoints.readUser(this.userPayload.getUsername());
		Assert.assertEquals(response.statusCode(),200);
		
	}
	@Test(priority=4)
	public void testDeleteUserByName() {
		
		logger.info("********* DELETE USER ********");
		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.statusCode(), 200);
		
		logger.info("********* USER IS DELETED ********");
		
	}
	
}




	
