package LandlordTest;

import org.testng.annotations.BeforeTest;

import com.jayway.restassured.RestAssured;

public class Setup {
	@BeforeTest
	public void setUp()
	{
		System.out.println("in before test");
		RestAssured.baseURI = "http://localhost:2222/landlords";
		RestAssured.basePath="";
	}

}


