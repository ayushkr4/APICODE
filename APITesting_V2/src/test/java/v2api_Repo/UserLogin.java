package v2api_Repo;

import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertEquals;
import org.hamcrest.core.Is;
import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import v2api_Utilities.Validatable;
import v2api_base.Api_base;
import v2api_base.GlobalVariables;

public class UserLogin extends Api_base {
	public void Generate_OTP() {
		Api_repoObject.Requestpayload.put("MOBILE", mobile_number);
		httprequest.body(Api_repoObject.Requestpayload.toJSONString());
		Response = httprequest.request(Method.POST, "/otp/generate");
		Validatable.success(Response, 200, null);
		GlobalVariables.Lead_id = Response.jsonPath().get("response.LEAD_ID");
		logger.info(GlobalVariables.Lead_id);
	}
	
	public void VerifyOTP() {
		Api_repoObject.Requestpayload.put("LEAD_ID", GlobalVariables.Lead_id);
		Api_repoObject.Requestpayload.put("OTP", requestdata.getProperty("otp"));
		httprequest.body(Api_repoObject.Requestpayload.toJSONString());
		Response = httprequest.request(Method.POST, "/otp/verifymobile");
		Validatable.success(Response, 200, "OTP successfully verified");
		GlobalVariables.Mobile_Token = Response.jsonPath().get("response.USER_KEY");
		
	}

	public void GetPin() {
		Response = httprequest.request(Method.GET, "/customer/profile/securitypin/get");
        Validatable.success(Response, 200, null);
	}

	public void UpdatePin() {
		Api_repoObject.Requestpayload.put("SECURITY_PIN", requestdata.getProperty("pin"));
		httprequest.body(Api_repoObject.Requestpayload.toJSONString());
		Response = httprequest.request(Method.POST, "/customer/profile/securitypin/update");
		Validatable.success(Response, 200, null);
		

	}

	public void FatchApplicationDetails() {
		Response = httprequest.request(Method.GET, "/customer/new/application/" + mobile_number);
		Validatable.success(Response, 200, null);
		assertThat(Response.jsonPath().get("response.allow").toString(), Is.is("true"));
	}

}
