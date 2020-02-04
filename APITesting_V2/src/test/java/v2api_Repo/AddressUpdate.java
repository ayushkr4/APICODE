package v2api_Repo;

import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertEquals;

import org.hamcrest.core.Is;
import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import v2api_Utilities.Validatable;
import v2api_base.Api_base;
import v2api_base.GlobalVariables;

public class AddressUpdate extends Api_base{

	public void AddressUpdate() {
		JSONObject json = new JSONObject();
		json.put("ADDRESS_LINE_2", GlobalVariables.addressline2);
		json.put("PINCODE", GlobalVariables.Pincode);
		json.put("ADDRESS_TYPE", requestdata.getProperty("addressType"));
		json.put("ADDRESS", GlobalVariables.address);
		Api_repoObject.RequestArray.add(json);
		Api_repoObject.Requestpayload.put("ADDRESS", Api_repoObject.RequestArray);
		Api_repoObject.Requestpayload.put("CUSTOMER_ID", GlobalVariables.Cust_id);
		httprequest.body(Api_repoObject.Requestpayload.toJSONString());
		Response=httprequest.request(Method.POST,"/customer/address/update");
	}
	
	public void Fetchaddress() {
			Response = httprequest.request(Method.GET, "/customer/get/address/"+GlobalVariables.Cust_id);
		assertEquals(Response.getStatusCode(), 200);
		JsonPath path = Api_repoObject.generics.rawToJSON(Response);
		assertEquals(path.get("response.ADDRESS.size()"), 1);
		GlobalVariables.address = path.get("response.ADDRESS[0].ADDRESS");
		GlobalVariables.addressline2 = path.get("response.ADDRESS[0].ADDRESS_LINE_2");
		
	}
}
