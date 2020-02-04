package v2api_Repo;

import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertEquals;

import org.hamcrest.core.Is;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import v2api_Utilities.Validatable;
import v2api_base.Api_base;
import v2api_base.GlobalVariables;
import org.json.simple.JSONObject;

public class EmploymentDetails extends Api_base {

	public void Getqualification() {
		Response = httprequest.request(Method.GET, "/master/get/QUALIFICATION");
		Validatable.success(Response, 200, null);
		JsonPath path = Api_repoObject.generics.rawToJSON(Response);
		assertThat(path.getString("response[0].DESC"), Is.is("10th Pass"));
		assertThat(path.getString("response[1].DESC"), Is.is("12th Pass"));
		assertThat(path.getString("response[2].DESC"), Is.is("Graduate"));
		assertThat(path.getString("response[3].DESC"), Is.is("Others"));
		assertThat(path.getString("response[4].DESC"), Is.is("Post Graduate"));
		assertThat(path.getString("response[5].DESC"), Is.is("Professional Course"));
		GlobalVariables.qualification = Response.jsonPath().getString("response[2].DESC");
	}

	public void GetEmployment() {
		Response = httprequest.request(Method.GET, "/customer/get/employment/" + GlobalVariables.Cust_id);
		Validatable.success(Response, 200, null);
	}

	public void UpdateEmployment() {
		Api_repoObject.Requestpayload.clear();
		Api_repoObject.Requestpayload.put("CUSTOMER_ID", GlobalVariables.Cust_id);
		Api_repoObject.Requestpayload.put("EMAIL", requestdata.getProperty("office_mail"));
		Api_repoObject.Requestpayload.put("NET_MONTHLY_SALARY", requestdata.getProperty("income"));
		Api_repoObject.Requestpayload.put("WORK_EXP", requestdata.getProperty("experience"));
		Api_repoObject.Requestpayload.put("OFFICE_LANDLINE", requestdata.getProperty("office_landline"));
		Api_repoObject.Requestpayload.put("QUALIFICATION", "Graduate");
		JSONObject company = new JSONObject();
		company.put("CATEGORY", "Super Prime A");
		company.put("CATEGORY_CODE", "A+");
		company.put("NAME", "FINOLEX CABLES LIMITED");
		Api_repoObject.Requestpayload.put("COMPANY", company);
		httprequest.body(Api_repoObject.Requestpayload.toJSONString());
		logger.info(Api_repoObject.Requestpayload.toJSONString());
		Response = httprequest.request(Method.POST, "/customer/employment/update");
		Validatable.success(Response, 200, null);

	}
}
