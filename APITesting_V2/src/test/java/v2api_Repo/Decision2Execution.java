package v2api_Repo;

import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertEquals;

import org.hamcrest.core.Is;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import v2api_Utilities.Validatable;
import v2api_base.Api_base;
import v2api_base.GlobalVariables;

public class Decision2Execution extends Api_base{

	public void DE2Init() {
		Response = httprequest.request(Method.GET, "/decision/DE2/init/"+GlobalVariables.App_id+"/"+GlobalVariables.Cust_id);
		Validatable.success(Response, 200, null);
		GlobalVariables.DE2Txn_id = Response.jsonPath().get("response.TRANSACTION_ID");
		logger.info(GlobalVariables.DE2Txn_id);
	}

	public void DE2Status() throws InterruptedException {
		Thread.sleep(2000);
		Response = httprequest.request(Method.GET, "/decision/DE2/status/"+GlobalVariables.DE2Txn_id);
		Validatable.success(Response, 200, null);
		String Status = Response.jsonPath().get("response.STATUS");
		if (Status.equals("PENDING")) {
			logger.info("************DE2 Transaction Status is Pending***********");
			Thread.sleep(5000);
			DE2Status();
		} else
			logger.info("************DE2 Transaction Status is Completed**********");
	}

	public void DE2Result(String Flow) {
		Response = httprequest.request(Method.GET, "/decision/DE2/retrieve/" + GlobalVariables.DE2Txn_id);
		Validatable.success(Response, 200, null);
		if (Flow.equalsIgnoreCase("Yellow")) {
			assertThat(Response.jsonPath().get("response.JOURNEY_TYPE").toString(), Is.is("3"));
			GlobalVariables.JournyType= Response.jsonPath().getInt("response.JOURNEY_TYPE");
			String RejectDescription = Response.jsonPath().getString("response.REJECT_DESC");
			logger.info("*****DE2 journy type is 2 due to " + RejectDescription + "**********");
		} else {
			assertThat(Response.jsonPath().get("response.JOURNEY_TYPE").toString(), Is.is("2"));
			logger.info("*****DE2 journy type is LightGreen**********");
		}
	}

	
}
