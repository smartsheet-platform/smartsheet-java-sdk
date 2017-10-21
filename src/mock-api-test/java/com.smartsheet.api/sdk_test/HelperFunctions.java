package com.smartsheet.api.sdk_test;

import com.smartsheet.api.Smartsheet;
import com.smartsheet.api.SmartsheetBuilder;
import com.smartsheet.api.oauth.Token;

public class HelperFunctions {
	public static Smartsheet SetupClient(String apiScenario){
		Smartsheet ss = new SmartsheetBuilder()
			.setBaseURI("http://localhost:8082/")
			.setAccessToken("aaaaaaaaaaaaaaaaaaaaaaaaaa")
			.setAPIScenario(apiScenario)
			.build();

		return ss;
	}
}