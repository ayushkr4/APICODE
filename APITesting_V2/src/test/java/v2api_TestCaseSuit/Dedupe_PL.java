package v2api_TestCaseSuit;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import v2api_Repo.Api_repoObject;
import v2api_Utilities.PanGenerators;
import v2api_Utilities.readExcel;
import v2api_base.Api_base;

public class Dedupe_PL extends Api_base{

	
	@Test(dataProvider="Dedupe_data")
public void TestDedupe(String PAN , String MOBILE, String Message) {
			//Api_repoObject.createcustomer.CreateCustomer(PAN, MOBILE, Message);
			
}

	
	@DataProvider(name = "Dedupe_data")
	public Object[][] readPLDecisionData() {
		Object[][] data = readExcel.readExcel("C:\\Users\\ayush\\eclipse-workspace\\APITesting_V2\\File\\dedupe.xlsx",sheet);
		return data;
	}
}
