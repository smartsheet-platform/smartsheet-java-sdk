package com.smartsheet.api.sdk_test;

/*
 * #[license]
 * Smartsheet Java SDK
 * %%
 * Copyright (C) 2014 - 2017 Smartsheet
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * %[license]
 */


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