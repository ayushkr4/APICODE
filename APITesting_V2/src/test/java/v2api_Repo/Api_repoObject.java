package v2api_Repo;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class Api_repoObject {

	public static UserLogin userlogin = new UserLogin();
	public static CreateCustomer createcustomer = new CreateCustomer();
	public static Decision1Execution executeDe1 = new Decision1Execution();
	public static Decision2Execution executeDe2 = new Decision2Execution();
	public static LinkData linkdata = new LinkData();
	public static ApplicationCreate appcreate = new ApplicationCreate();
	public static AddressUpdate addressupdate = new AddressUpdate();
	public static GenericMethods generics = new GenericMethods();
	public static EmploymentDetails employmentdetails = new EmploymentDetails();
	public static FinancialUpdate financialupdate = new FinancialUpdate();
	public static ArtifactUpdate artifactupdate = new ArtifactUpdate();

	// JSON-SIMPLE class
	public static JSONObject Requestpayload = new JSONObject();
	public static JSONArray RequestArray = new JSONArray();

}
