package LandlordTest;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static com.jayway.restassured.RestAssured.*;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.given;


public class Scripts {

	public static String q3_id;
	public static Pojo_ReqAdd_landlord update_landlord2 = new Pojo_ReqAdd_landlord("Antony","ca",false);

	// gets base uri and path for all tests
	@BeforeTest
	public void setUp()
	{
		System.out.println("in before test");
		RestAssured.baseURI = "http://localhost:2222";
		RestAssured.basePath="/landlords";
	}

	
	// verify empty db of landlords
	@Test(priority = 1)
	public static void q1_getAllLandlords_empty_db()
	{

		given()
		.get()
		.then()
		.body("",Matchers.empty()).statusCode(200);
		System.out.println("done 1");

	}
	

	// create a new landlord using pojo
	// validate creation - validate status code - validate schema compliance
	
	@Test(priority = 2)
	public static void q2_add_lanlord_usingPOJO()
	{

		// create landlord
		Pojo_ReqAdd_landlord landlord1 = new Pojo_ReqAdd_landlord("aana","sa",false);

		// give to body using pojo
		Response response = given()
				.when()
				.contentType(ContentType.JSON)
				.body(landlord1)
				.post();

		// validate: creation -  status code -  schema compliance	
		response
		.then()
		.assertThat()
		.body("firstName", Matchers.equalTo("aana"))
		.and()
		.statusCode(201)		
		.and()
		.body(matchesJsonSchemaInClasspath("post_schema.json"));
		System.out.println("done 2");
	}

	// create a new landlord 
	// validate  : apartments data empty -   status code
	// edit created lanlord's name - PUT
	// validate : status code -  success message-  schema compliance

	@Test(priority = 3)
	public static void q3_edit_lanlord()
	{

		Pojo_ReqAdd_landlord landlord2 = new Pojo_ReqAdd_landlord("john","KL",false);

		// create john
		Response response = given()
				.when()
				.contentType(ContentType.JSON)
				.body(landlord2)
				.post();
		
		// validate appartments[] is empty
		response
		.then()
		.assertThat()
		.body("apartments", Matchers.empty())
		.and()
		.assertThat()
		.statusCode(201);
		//store id
		q3_id = response.body().path("id");

		// edit john to antony

		Response edit_resp = given()
				.when()
				.pathParam("param",q3_id)
				.contentType(ContentType.JSON)
				.body(update_landlord2)
				.put("/{param}");

		//validate status code  , response message
		edit_resp
		.then()
		.assertThat()
		.statusCode(200)
		.and()
		.body("message", Matchers.equalTo("LandLord with id: " +q3_id +" successfully updated"))
		.and()
		.body(matchesJsonSchemaInClasspath("put_schema.json"));

		System.out.println("done 3");

	}

	// try to edit with invalid id
	// validate response message

	@Test(priority = 4)
	public static void q4_invalid_edit_landlord()
	{


		Pojo_ReqAdd_landlord update_landlord2 = new Pojo_ReqAdd_landlord("Fail_update","ca",false);

		// update with invalid id
		Response edit_resp = given()
				.when()
				.pathParam("param","4DKlS34")
				.contentType(ContentType.JSON)
				.body(update_landlord2)
				.put("/{param}");

		//verify 404 error and response message
		edit_resp
		.then()
		.assertThat()
		.statusCode(404)
		.and()
		.body("message", Matchers.equalTo("There is no LandLord with id: 4DKlS34"));
		
		System.out.println("done 4");
	}

	//get landlord by using q3's id
	// verify request body

	@Test(priority = 5)
	public static void q5_get_landlord_by_id()
	{
		Response response = given()
				.when()
				.pathParam("param",q3_id)
				.get("/{param}");

		response
		.then()
		.assertThat()
		.statusCode(200)
		.and()
		.body("firstName",Matchers.equalTo(update_landlord2.firstName))
		.and()
		.body("lastName",Matchers.equalTo(update_landlord2.lastName));
		
		System.out.println("done 5");
		
	}

}
