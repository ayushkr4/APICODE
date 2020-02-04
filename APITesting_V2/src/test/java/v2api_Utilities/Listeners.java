package v2api_Utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class Listeners extends TestListenerAdapter
    {
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	public void onStart(ITestContext testContext)
	{
	htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/API_RunReport.html");  //specify location of report
		
	htmlReporter.config().setDocumentTitle("Automation Run Report"); // Title of report
	htmlReporter.config().setReportName("Alpha API Test"); //name of the  report
	htmlReporter.config().setTheme(Theme.DARK);
	htmlReporter.config().setTimeStampFormat("HH:MM:SS");
	
	extent=new ExtentReports();
	extent.attachReporter(htmlReporter);
	extent.setSystemInfo("Host name", "uat-loc-api.incred.com");
	extent.setSystemInfo("Environemnt", "UAT");
	
	}

	
	public void onTestSuccess(ITestResult result) 
	{
		
		test=extent.createTest(result.getName()); //create new entery in the report
		test.log(Status.PASS, "Test Case "+result.getName()+" is PASSED");
		
	}
	
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getName());  //create new entery in the report
		
		test.log(Status.FAIL,"Test Case "+result.getName()+" is FAILED");   //to add name in extent report
		test.log(Status.FAIL,"Test Case FAILED IS "+result.getThrowable());   //to add error/exceptions in extent report
		
	}
	
	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getName());  //create new entery in the report
		test.log(Status.SKIP,"Test Case "+result.getName()+" is SKIPPED");   //to add name in extent report

	}
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
}
