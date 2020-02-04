package v2api_Utilities;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import v2api_Repo.Api_repoObject;

import org.hamcrest.core.Is;
import org.testng.Assert;
import org.testng.annotations.Ignore;

import com.aventstack.extentreports.Status;

import v2api_base.Api_base;

public class Validatable extends Api_base {

	public static void  success(Response response, int code, String message) {
		assertEquals(response.getStatusCode(), code);
		if (message==null) {
			String validatablemessage = response.jsonPath().getString("message");
			assertTrue(validatablemessage.equalsIgnoreCase("Request Processed Successfully"));
		} else
			assertThat(response.jsonPath().get("message").toString(), Is.is(message));
	}
}
