package v2api_Repo;

import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertEquals;

import javax.xml.bind.annotation.XmlElementDecl.GLOBAL;

import org.hamcrest.core.Is;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import v2api_Utilities.PanGenerators;
import v2api_Utilities.Validatable;
import v2api_base.Api_base;
import v2api_base.GlobalVariables;

public class CreateCustomer extends Api_base {

	public void fatchGioLocation() {
		Response = httprequest.request(Method.GET, "/geo/get/"+requestdata.getProperty("lat")+"/"+requestdata.getProperty("lng"));
		GlobalVariables.Pincode = Response.jsonPath().get("response.PINCODE");
	   Validatable.success(Response, 200, "Request processed succssesfully");
	}

	public void CheckServiceable() {
		Response = httprequest.request(Method.GET, "/pincode/serviceable/PL/" + GlobalVariables.Pincode);
		Validatable.success(Response, 200, null);
		Response.then().assertThat().body("response.SERVICEABLE", Is.is(true));
	}

	public void ValidatePan() {
		logger.info("pan in payload"+GlobalVariables.Pancard);
		Api_repoObject.Requestpayload.put("PAN",GlobalVariables.Pancard);
		httprequest.body(Api_repoObject.Requestpayload.toJSONString());
		System.out.println("pan in payload "+Api_repoObject.Requestpayload.toJSONString());
		Response = httprequest.request(Method.POST, "/pan/validate");
		Validatable.success(Response, 200, "Document Validated Successfully");
	}

	public void CreateCustomer() {
		Api_repoObject.Requestpayload.put("PAN","PLREI0061A");
		Api_repoObject.Requestpayload.put("MOBILE", mobile_number);
		//Api_repoObject.Requestpayload.put("AADHAAR", requestdata.getProperty("aadhaar"));
		Api_repoObject.Requestpayload.put("CREDIT_VIDYA_ID", requestdata.getProperty("cvid"));
		Api_repoObject.Requestpayload.put("PINCODE", GlobalVariables.Pincode);
		Api_repoObject.Requestpayload.put("DOB", requestdata.getProperty("dob"));
		Api_repoObject.Requestpayload.put("DEDUPE", requestdata.getProperty("dedupe"));
		httprequest.body(Api_repoObject.Requestpayload.toJSONString());
		Response = httprequest.request(Method.POST, "/customer/create");
	    logger.info(Response.getBody().asString());
		Validatable.success(Response, 200, null);
		GlobalVariables.Cust_id = Response.jsonPath().get("response.CUSTOMER_ID");
		GlobalVariables.F_name = Response.jsonPath().get("response.FNAME");
		GlobalVariables.M_name = Response.jsonPath().get("response.MNAME");
		GlobalVariables.L_name = Response.jsonPath().get("response.LNAME");
	}

}