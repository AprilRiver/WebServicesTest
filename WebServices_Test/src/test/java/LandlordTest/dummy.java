//package LandlordTest;
//
//import static com.jayway.restassured.RestAssured.given;
//
//import org.hamcrest.Matchers;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
//
//import com.jayway.restassured.RestAssured;
//import com.jayway.restassured.http.ContentType;
//import com.jayway.restassured.response.Response;
//
//import static io.restassured.module.jsv.JsonSchemaValidator.*;
//import io.restassured.module.jsv.JsonSchemaValidator;
//
//public class dummy {
//	@BeforeTest
//	public void setUp()
//	{
//		System.out.println("in before test");
//		RestAssured.baseURI = "http://localhost:2222";
//		RestAssured.basePath="/landlords";
//	}
//	@Test(priority = 3)
//	public static void q3_edit_lanlord()
//	{
//
//		Pojo_ReqAdd_landlord landlord2 = new Pojo_ReqAdd_landlord("john","KL",false);
//
//		// create john
//		Response response = given()
//				.when()
//				.contentType(ContentType.JSON)
//				.body(landlord2)
//				.post();
//
//		// print response
//			
//
//		// validate appartments[] is empty
//		response
//		.then()
//		.assertThat()
//		.body("apartments", Matchers.empty())
//		.and()
//		.assertThat()
//		.statusCode(201);
//		//store id
//		q3_id = response.body().path("id");
//
//		// edit john to antony
//
//		Response edit_resp = given()
//				.when()
//				.pathParam("param",q3_id)
//				.contentType(ContentType.JSON)
//				.body(update_landlord2)
//				.put("/{param}");
//
//		//validate status code  , response message
//		edit_resp
//		.then()
//		.assertThat()
//		.statusCode(200)
//		.and()
//		.body("message", Matchers.equalTo("LandLord with id: " +q3_id +" successfully updated"))
//		.and()
//		.body(matchesJsonSchemaInClasspath("put_schema.json"));
//
//		System.out.println("done 3");
//
//	}
//
//}
