package com.smartsheet.api.sdk_test;

import com.smartsheet.api.Smartsheet;
import com.smartsheet.api.SmartsheetBuilder;
import org.junit.Assert;

public class HelperFunctions {
	public static Smartsheet SetupClient(String apiScenario){
		Smartsheet ss = new SmartsheetBuilder()
			.setBaseURI("http://localhost:8082/")
			.setAccessToken("aaaaaaaaaaaaaaaaaaaaaaaaaa")
			.setAPIScenario(apiScenario)
			.build();

		return ss;
	}
	public static void ExceptionMessage(String message, Throwable cause){
		Assert.fail(String.format("Exception: %s Detail: %s", message, cause));
	}
}