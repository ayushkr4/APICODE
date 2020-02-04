package v2api_Repo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import v2api_base.GlobalVariables;

public class GenericMethods {

	public JsonPath rawToJSON(Response body) {
		JsonPath path = new JsonPath(body.getBody().asString());
		return path;
	}

	public JSONArray ArtifactRequest(String Aadhaar, String PANCARD, String Statement, String payslip) {

		if (Aadhaar != null && Aadhaar.equalsIgnoreCase("aadhaar")) {
			Api_repoObject.RequestArray.clear();
			JSONArray key1 = new JSONArray();
			key1.add("99914b932bd37a50b983c5e7c90ae93b/21fa5afd05d4a5311459b0feb7029dfd");
			JSONObject filecollections1 = new JSONObject();
			filecollections1.put("FILE_KEY", key1);
			filecollections1.put("FILE_NAME", "24072019_5314.jpg");
			JSONArray Filecollection1 = new JSONArray();
			Filecollection1.add(filecollections1);
			JSONObject file1 = new JSONObject();
			file1.put("FILE_COLLECTION", Filecollection1);
			file1.put("DOC_TYPE", "PL_IN_ID_PRF");
			file1.put("DOC_NAME", "PL_PAN_CRD");
			file1.put("CUSTOMER_ID", GlobalVariables.Cust_id);
			file1.put("APPLICATION_ID", GlobalVariables.App_id);
			Api_repoObject.RequestArray.add(file1);
		}

		if (PANCARD != null && PANCARD.equalsIgnoreCase("pancard")) {
			JSONArray key2 = new JSONArray();
			key2.add("99914b932bd37a50b983c5e7c90ae93b/ad3eb0ab04d3881ad62b1281370f2e9e");
			JSONObject filecollections2 = new JSONObject();
			filecollections2.put("FILE_KEY", key2);
			filecollections2.put("FILE_NAME", "24072019_5324.jpg");
			JSONArray Filecollection2 = new JSONArray();
			Filecollection2.add(filecollections2);
			JSONObject file2 = new JSONObject();
			file2.put("FILE_COLLECTION", Filecollection2);
			file2.put("DOC_TYPE", "PL_IN_ADR_PRF");
			file2.put("DOC_NAME", "PL_AADHAAR");
			file2.put("CUSTOMER_ID", GlobalVariables.Cust_id);
			file2.put("APPLICATION_ID", GlobalVariables.App_id);
			Api_repoObject.RequestArray.add(file2);
		}
		if (Statement != null && Statement.equalsIgnoreCase("Statement")) {
			JSONArray key3 = new JSONArray();
			key3.add("99914b932bd37a50b983c5e7c90ae93b/380519ece069b5ef996f758f20a378b4");
			JSONObject filecollections3 = new JSONObject();
			filecollections3.put("FILE_KEY", key3);
			filecollections3.put("FILE_NAME", "24072019_5324.jpg");
			JSONArray Filecollection3 = new JSONArray();
			Filecollection3.add(filecollections3);
			JSONObject file3 = new JSONObject();
			file3.put("FILE_COLLECTION", Filecollection3);
			file3.put("DOC_TYPE", "PL_IN_ADR_PRF");
			file3.put("DOC_NAME", "PL_BNK_STT");
			file3.put("CUSTOMER_ID", GlobalVariables.Cust_id);
			file3.put("APPLICATION_ID", GlobalVariables.App_id);
			Api_repoObject.RequestArray.add(file3);
		}
		if (payslip != null && payslip.equalsIgnoreCase("payslip")) {
			JSONArray key4 = new JSONArray();
			key4.add("99914b932bd37a50b983c5e7c90ae93b/6e97576a1ac47475937c1041892b78dc");
			JSONObject filecollections4 = new JSONObject();
			filecollections4.put("FILE_KEY", key4);
			filecollections4.put("FILE_NAME", "24072019_5324.jpg");
			JSONArray Filecollection4 = new JSONArray();
			Filecollection4.add(filecollections4);
			JSONObject file4 = new JSONObject();
			file4.put("FILE_COLLECTION", Filecollection4);
			file4.put("DOC_TYPE", "PL_OTHER");
			file4.put("DOC_NAME", "PL_SALARY_SLIP");
			file4.put("CUSTOMER_ID", GlobalVariables.Cust_id);
			file4.put("APPLICATION_ID", GlobalVariables.App_id);
			Api_repoObject.RequestArray.add(file4);
		}
		return Api_repoObject.RequestArray;
	}
}