package v2api_Repo;


import static org.testng.Assert.assertEquals;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import v2api_Utilities.Validatable;
import v2api_base.Api_base;
import v2api_base.GlobalVariables;

public class FinancialUpdate extends Api_base{

	public void Getfinancial() {
		 Response=httprequest.request(Method.GET,"/customer/get/financial/"+GlobalVariables.Cust_id);
		   Validatable.success(Response, 200, null);
		   assertEquals(Response.jsonPath().getString("response.CUSTOMER_ID"), GlobalVariables.Cust_id);
	}
	public void BankDetailUpdate() {
		Api_repoObject.Requestpayload.put("CUSTOMER_ID", GlobalVariables.Cust_id);
		Api_repoObject.Requestpayload.put("ACCOUNT_NUMBER", requestdata.getProperty("accountno"));
		Api_repoObject.Requestpayload.put("IFSC_CODE", requestdata.getProperty("IFSE"));
		httprequest.body(Api_repoObject.Requestpayload.toJSONString());
		Response=httprequest.request(Method.POST,"/customer/financial/update");
		Validatable.success(Response, 200, null);
		JsonPath path = Api_repoObject.generics.rawToJSON(Response);
		assertEquals(requestdata.getProperty("accountno"), path.get("response.BANK.ACCOUNT[0].ACCOUNT_NUM"));
		assertEquals(requestdata.getProperty("IFSE"), path.get("response.BANK.ACCOUNT[0].IFSC_CODE"));
	}
}
