package v2api_Repo;

import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertEquals;
import org.hamcrest.core.Is;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import v2api_Utilities.Validatable;
import v2api_base.Api_base;
import v2api_base.GlobalVariables;

public class Decision1Execution extends Api_base {

	public void DE1Init() {
		Response = httprequest.request(Method.GET, "/decision/DE1/init/"+GlobalVariables.Cust_id);
		Validatable.success(Response, 200, null);
		GlobalVariables.DE1Txn_id = Response.jsonPath().get("response.TRANSACTION_ID");
		logger.info(GlobalVariables.DE1Txn_id);
	}

	public void DE1Status() throws InterruptedException {
		Response = httprequest.request(Method.GET, "/decision/DE1/status/"+GlobalVariables.DE1Txn_id);
		Validatable.success(Response, 200, null);
		String Status = Response.jsonPath().get("response.STATUS");
		if (Status.equals("PENDING")) {
			logger.info("************DE1 Transaction Status is Pending***********");
			Thread.sleep(2000);
			DE1Status();
		} else
			logger.info("************DE1 Transaction Status is Completed**********");
	}

	public void DE1Result(String Flow) {
		Response = httprequest.request(Method.GET, "/decision/DE1/retrieve/"+GlobalVariables.DE1Txn_id);
		Validatable.success(Response, 200, null);
		if (Flow.equalsIgnoreCase("Yellow")) {
			assertThat(Response.jsonPath().get("response.JOURNEY_TYPE").toString(), Is.is("2"));
			GlobalVariables.JournyType= Response.jsonPath().getInt("response.JOURNEY_TYPE");
			String RejectDescription = Response.jsonPath().getString("response.REJECT_DESC");
			logger.info("*****DE1 journy type is 2 due to " + RejectDescription + "**********");
		} else if(Flow.equalsIgnoreCase("light_green")||Flow.equalsIgnoreCase("dark_green")) {
			//assertThat(Response.jsonPath().get("response.JOURNEY_TYPE").toString(), Is.is("1"));
			//GlobalVariables.JournyType= Response.jsonPath().getInt("response.JOURNEY_TYPE");
			logger.info("*****DE1 journy type is Darkgreen**********");
		}
	}
}
