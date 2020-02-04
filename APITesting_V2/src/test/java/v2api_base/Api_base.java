package v2api_base;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import io.restassured.specification.RequestSpecification;
import v2api_Repo.Api_repoObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import v2api_Utilities.PanGenerators;
import v2api_Utilities.RandomGenerator;

public class Api_base {

	public static RequestSpecification httprequest;
	public static Response Response;
	public static String responsebody;
	public static Logger logger;
	public static String mobile_number = RandomGenerator.getRandomMobileNumber();
	public static String Success = "Request processed succssesfully";
	public static int statuscode;
	public static Properties requestdata = new Properties();
	public static String sheet;

	@BeforeSuite
	@Parameters({ "Set_Flow", "host_name" ,"Sheet_name"})
	public void sethost(String Set_Flow, String host_name, String Sheet_name) {
		GlobalVariables.Flow = Set_Flow;
		GlobalVariables.Host = host_name;
		sheet = Sheet_name;
		GlobalVariables.Pancard = PanGenerators.Attachpan(GlobalVariables.Flow);
	}

	@BeforeClass
	public void setup() {
		logger = Logger.getLogger("APITesting_V2");
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.DEBUG);
		RestAssured.baseURI = GlobalVariables.Host;
	}

	@BeforeMethod
	public void initialization() {
		Api_repoObject.Requestpayload.clear();
		httprequest = RestAssured.given();
		httprequest.contentType("application/json");
		if (GlobalVariables.Mobile_Token != null)
			httprequest.headers("mobile-token", GlobalVariables.Mobile_Token);
		else
			httprequest.header("api-key", requestdata.getProperty("key_value"));
	}

	@AfterMethod
	public void RequestResponsetoLog() {
		responsebody = Response.getBody().asString();
		logger.info("Response body is ****" + responsebody + "*******");
	}

	@BeforeTest
	public void SetTestData() throws IOException {
		FileInputStream testfile = new FileInputStream(
				"C:\\Users\\Dell\\eclipse-workspace\\APITesting_V2\\src\\test\\java\\v2api_TestDataProperties\\TestData.properties");
		requestdata.load(testfile);
	}
	@AfterTest()
	private void TestCompletion() {
		logger.info("***********Test Completed************");
		logger.info("***********Application id is "+GlobalVariables.App_id+"************");
		logger.info("***********Customer id is "+GlobalVariables.Cust_id+"************");
	}
}
