package v2api_Repo;

import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertEquals;
import org.hamcrest.core.Is;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import v2api_Utilities.Validatable;
import v2api_base.Api_base;
import v2api_base.GlobalVariables;

public class LinkData extends Api_base{

	public void LocationUpdate() {
		Api_repoObject.Requestpayload.put("CUSTOMER_ID",GlobalVariables.Cust_id);
		Api_repoObject.Requestpayload.put("LAT",requestdata.getProperty("lat"));
		Api_repoObject.Requestpayload.put("LNG",requestdata.getProperty("lng"));
		Api_repoObject.Requestpayload.put("SOURCE",requestdata.getProperty("source"));
		Api_repoObject.Requestpayload.put("ADDRESS",requestdata.getProperty("address"));
		Api_repoObject.Requestpayload.put("PINCODE", GlobalVariables.Pincode);
		httprequest.body(Api_repoObject.Requestpayload.toJSONString());
		Response=httprequest.request(Method.POST,"/customer/mobile/location");
		Validatable.success(Response, 200, null);
	}
	
	public void SaveMobileContacts() {
		Api_repoObject.Requestpayload.put("CUSTOMER_ID",GlobalVariables.Cust_id);	
		Api_repoObject.Requestpayload.put("data","");
	    httprequest.body(Api_repoObject.Requestpayload.toJSONString());
	    Response=httprequest.request(Method.POST,"/customer/mobile/app_cnct");
		Validatable.success(Response, 200, null);
	}
	public void GetContacts() {
	    Response=httprequest.request(Method.GET,"/customer/get/contact/"+GlobalVariables.Cust_id);
		Validatable.success(Response, 200, null);
		Response.then().assertThat().body("response.CONTACT[0].VALUE", Is.is(mobile_number));
		
	}
	public void AdditionalContacts() {
		Api_repoObject.Requestpayload.put("EMAIL", requestdata.getProperty("Personal_mail"));
		Api_repoObject.Requestpayload.put("CUSTOMER_ID",GlobalVariables.Cust_id);
		httprequest.body(Api_repoObject.Requestpayload.toJSONString());
		Response=httprequest.request(Method.POST,"/customer/contact/update");
		Validatable.success(Response, 200, null);
	}
}
