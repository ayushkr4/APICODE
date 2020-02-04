package v2api_Repo;

import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertEquals;
import org.hamcrest.core.Is;
import org.json.simple.JSONObject;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.internal.http.HttpResponseContentTypeFinder;
import io.restassured.path.json.JsonPath;
import v2api_Utilities.PanGenerators;
import v2api_Utilities.Validatable;
import v2api_base.Api_base;
import v2api_base.GlobalVariables;

public class ApplicationCreate extends Api_base {

	public void AppCreation() {
		Api_repoObject.Requestpayload.put("CUSTOMER_ID", GlobalVariables.Cust_id);
		Api_repoObject.Requestpayload.put("PLATFORM", "PL-ALPHA-ANDROID");
		Api_repoObject.Requestpayload.put("LOAN_TYPE", "PL");
		Api_repoObject.Requestpayload.put("LOAN_SCHEME", "PL_ALPHA");
		Api_repoObject.Requestpayload.put("PINCODE", GlobalVariables.Pincode);
		Api_repoObject.Requestpayload.put("STATUS", "INCOMPLETE");
		Api_repoObject.Requestpayload.put("APP_VERSION_CODE", 184);
		Api_repoObject.Requestpayload.put("JOURNEY_TYPE", GlobalVariables.JournyType);
		JSONObject de1 = new JSONObject();
		de1.put("JOURNEY_TYPE", GlobalVariables.JournyType);
		JSONObject Dec = new JSONObject();
		Dec.put("DE1", de1);
		Api_repoObject.Requestpayload.put("DECISION", Dec);
		httprequest.body(Api_repoObject.Requestpayload.toJSONString());
		Response = httprequest.request(Method.POST, "/application/create");
		GlobalVariables.App_id = Response.jsonPath().getString("response.APPLICATION_ID");
		System.out.println(GlobalVariables.App_id);
		Validatable.success(Response, 200, null);
	}

	public void fetchprofile(String Flow) {
		Response = httprequest.request(Method.GET, "/customer/get/profile/" + GlobalVariables.Cust_id);
		JsonPath path = Api_repoObject.generics.rawToJSON(Response);
		assertThat(path.getString("response.PROFILE.CUSTOMER_ID"), Is.is(GlobalVariables.Cust_id));
		assertThat(path.getString("response.PROFILE.DOB"), Is.is(requestdata.getProperty("dob")));
		assertThat(path.getString("response.PROFILE.FNAME"), Is.is(GlobalVariables.F_name));
		assertThat(path.getString("response.PROFILE.MNAME"), Is.is(GlobalVariables.M_name));
		assertThat(path.getString("response.PROFILE.LNAME"), Is.is(GlobalVariables.L_name));
		assertThat(path.getString("response.PROFILE.AADHAAR"), Is.is(requestdata.getProperty("aadhaar")));
		assertThat(path.getString("response.PROFILE.CONTACT[0].VALUE"), Is.is(mobile_number));
			assertThat(path.getString("response.PROFILE.PAN"), Is.is(GlobalVariables.Pancard));
	}

	public void Profileupdate() {
		Api_repoObject.Requestpayload.clear();
		Api_repoObject.Requestpayload.put("CUSTOMER_ID", GlobalVariables.Cust_id);
		Api_repoObject.Requestpayload.put("FNAME", GlobalVariables.F_name);
		Api_repoObject.Requestpayload.put("FNAME", GlobalVariables.M_name);
		Api_repoObject.Requestpayload.put("FNAME", GlobalVariables.L_name);
		Api_repoObject.Requestpayload.put("FATHER_NAME", requestdata.getProperty("father"));
		Api_repoObject.Requestpayload.put("MOTHER_NAME", requestdata.getProperty("mother"));
		Api_repoObject.Requestpayload.put("GENDER", requestdata.getProperty("gender"));
		Api_repoObject.Requestpayload.put("DEPENDENTS", requestdata.getProperty("dependents"));
		Api_repoObject.RequestArray.add(" ");
		Api_repoObject.Requestpayload.put("IMAGE", Api_repoObject.RequestArray);
		httprequest.body(Api_repoObject.Requestpayload.toJSONString());
		Response = httprequest.request(Method.POST, "/customer/profile/update");
		Validatable.success(Response, 200, null);

	}
	public void UpdateAppFlow() {
		Api_repoObject.Requestpayload.clear();
		Api_repoObject.Requestpayload.put("STATUS", "INCOMPLETE");
		Api_repoObject.Requestpayload.put("APPLICATION_ID", GlobalVariables.App_id);
	    JSONObject DEtype = new JSONObject();
	    DEtype.put("JOURNEY_TYPE", GlobalVariables.JournyType);
	    JSONObject Decision =new JSONObject();
	    Decision.put("DE2", DEtype);
		Api_repoObject.Requestpayload.put("DECISION", Decision);
		httprequest.body(Api_repoObject.Requestpayload.toJSONString());
		Response=httprequest.request(Method.POST,"/application/update");		
		Validatable.success(Response, 200, null);
		
	}
	public  void LoanRequirmentupdate() {
		
		Api_repoObject.Requestpayload.put("APPLICATION_ID", GlobalVariables.App_id);
		Api_repoObject.Requestpayload.put("PLATFORM", "PL-ALPHA-ANDROID");
		Api_repoObject.Requestpayload.put("LOAN_AMOUNT", requestdata.getProperty("amount"));
		Api_repoObject.Requestpayload.put("PLATFORM", requestdata.getProperty("tenure"));
		Api_repoObject.Requestpayload.put("LOAN_TYPE", "PL");
		httprequest.body(Api_repoObject.Requestpayload.toJSONString());
		Response=httprequest.request(Method.POST,"/application/update");
		Validatable.success(Response, 200, null);
	}
	public void AppInprogress() {
		Api_repoObject.Requestpayload.put("APPLICATION_ID", GlobalVariables.App_id);
		Api_repoObject.Requestpayload.put("STATUS", "COMPLETED");
		httprequest.body(Api_repoObject.Requestpayload.toJSONString());
		Response=httprequest.request(Method.POST,"/application/complete");
		Validatable.success(Response, 200, null);
	}
}
