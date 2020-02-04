package v2api_TestCaseSuit;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import v2api_Repo.Api_repoObject;
import v2api_Utilities.PanGenerators;
import v2api_base.Api_base;
import v2api_base.GlobalVariables;


public class Alpha_yellow_suit extends Api_base {

	@Test(priority = 1)
	public void GenerateOTP() {
		Api_repoObject.userlogin.Generate_OTP();
	}

	@Test(priority = 2)
	public void ValidateOTP() {
		Api_repoObject.userlogin.VerifyOTP();
	}

	@Test(priority = 3)
	public void GetDigitalPin() {
		Api_repoObject.userlogin.GetPin();
	}

	@Test(priority = 4)
	public void UpdateDigitalPin() {
		Api_repoObject.userlogin.UpdatePin();
	}

	@Test(priority = 5)
	public void FatchCustomerDetail() {
		Api_repoObject.userlogin.FatchApplicationDetails();
	}

	@Test(priority = 6)
	public void FetchGPSlocation() {
		Api_repoObject.createcustomer.fatchGioLocation();
	}

	@Test(priority = 7)
	public void ServiceablityCheck() {
		Api_repoObject.createcustomer.CheckServiceable();
	}

	@Test(priority = 8)
	public void ValidatePancard() {
		Api_repoObject.createcustomer.ValidatePan();
	}

	@Test(priority = 9)
	public void CreateCustomer() {
		Api_repoObject.createcustomer.CreateCustomer();
	}

	@Test(priority = 10)
	public void LinkLocationDetails() {
		Api_repoObject.linkdata.LocationUpdate();
	}

	/*
	 * @Test(priority=11) public void LinkContactsDetail() throws
	 * InterruptedException { Api_repoObject.linkdata.SaveMobileContacts(); }
	 */
	@Test(priority = 12)
	public void Decision1Initiate() {
		Api_repoObject.executeDe1.DE1Init();
	}

	@Test(priority = 13)
	public void Decision1Status() throws InterruptedException {
		Api_repoObject.executeDe1.DE1Status();
	}
	
	@Test(priority = 14)
	public void Decision1Result() {
		Api_repoObject.executeDe1.DE1Result(GlobalVariables.Flow);;
	}
	@Test(priority=15)
	public void Applicationcreate() {
		Api_repoObject.appcreate.AppCreation();
	}
	
	@Test(priority=16)
	public void GetAddressdetails() {
		Api_repoObject.addressupdate.Fetchaddress();
	}
	@Test(priority=17)
	public void AddressUpdate() {
		Api_repoObject.addressupdate.AddressUpdate();
	}
	@Test(priority=18)
	public void FatchProfile() {
		Api_repoObject.appcreate.fetchprofile(GlobalVariables.Flow);
	}
	@Test(priority=19)
	public void FatchContacts() {
		Api_repoObject.linkdata.GetContacts();
	}
	@Test(priority=20)
	public void ProfileUpdate() {
		Api_repoObject.appcreate.Profileupdate();
	}
	@Test(priority=21)
	public void ContactUpdate() {
		Api_repoObject.linkdata.AdditionalContacts();
	}
	@Test(priority=22)
	public void QalificationMasters() {
		Api_repoObject.employmentdetails.Getqualification();
	}
	@Test(priority=23)
	public void FatchEmploynment() {
		Api_repoObject.employmentdetails.GetEmployment();
	}
	@Test(priority=24)
	public void EmploymentUpdate() {
		Api_repoObject.employmentdetails.UpdateEmployment();
	}
	@Test(priority=25)
	public void Decision2Initiate() {
		Api_repoObject.executeDe2.DE2Init();
	}
	@Test(priority=26)
	public void Decision2Status() throws InterruptedException {
		Api_repoObject.executeDe2.DE2Status();
	}
	@Test(priority=27)
	public void Decision2Result() {
		Api_repoObject.executeDe2.DE2Result(GlobalVariables.Flow);
	}
	@Test(priority=28)
	public void AppJourneyUpdate() {
		Api_repoObject.appcreate.UpdateAppFlow();
	}
	@Test(priority=29)
	public void LoanRequirment() {
		Api_repoObject.appcreate.LoanRequirmentupdate();
	}
	@Test(priority=30)
	public void FatchFinanicaldetails() {
		Api_repoObject.financialupdate.Getfinancial();
	}
	@Test(priority=31)
	public void UpdateFinancialDetails() {
		Api_repoObject.financialupdate.BankDetailUpdate();
	}
	@Test(priority=32)
	public void GetArtifactsdetail() {
		Api_repoObject.artifactupdate.getartifacts();
	}
	@Test(priority=33)
	public void ArtifactsUpdate() {
		Api_repoObject.artifactupdate.UpdateArtifact();
	}
	@Test(priority=34)
	public void uploadprofilephoto() {
		Api_repoObject.artifactupdate.ImageUpload();
	}
	@Test(priority=35)
	public void MovetoInprogress() {
		Api_repoObject.appcreate.AppInprogress();
	}
}