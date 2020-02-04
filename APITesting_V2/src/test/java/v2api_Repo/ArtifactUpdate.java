package v2api_Repo;

import org.json.simple.JSONArray;
import io.restassured.http.Method;
import v2api_Utilities.Validatable;
import v2api_base.Api_base;
import v2api_base.GlobalVariables;

public class ArtifactUpdate extends Api_base{

	public void getartifacts() {
		Response=httprequest.request(Method.GET,"/artifact/list/"+GlobalVariables.App_id);
		Validatable.success(Response, 200, null);
	}
	public void UpdateArtifact() {
		JSONArray ArtifactRequest = Api_repoObject.generics.ArtifactRequest(null, "PANCARD", "Statement", "payslip");
		httprequest.body(ArtifactRequest.toJSONString());
		logger.info(ArtifactRequest.toJSONString());
		Response=httprequest.request(Method.POST,"/artifact/create");
		Validatable.success(Response, 200, null);
	}
	public void ImageUpload() {
		Api_repoObject.RequestArray.clear();
		Api_repoObject.Requestpayload.clear();
		Api_repoObject.RequestArray.add("99914b932bd37a50b983c5e7c90ae93b/d56e28d7ecbfa1a4e53024dcad669436");
		Api_repoObject.Requestpayload.put("IMAGE", Api_repoObject.RequestArray);
		Api_repoObject.Requestpayload.put("CUSTOMER_ID", GlobalVariables.Cust_id);
		httprequest.body(Api_repoObject.Requestpayload.toJSONString());
		Response=httprequest.request(Method.POST,"/customer/profile/update");
		Validatable.success(Response, 200, null);
	}
	
}
