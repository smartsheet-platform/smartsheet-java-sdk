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


import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.internal.http.HttpRequest;
import org.apache.http.client.methods.HttpRequestBase;

public class TestHttpClient extends DefaultHttpClient {

    private String apiScenario;

    public TestHttpClient(String apiScenario) {
        this.apiScenario = apiScenario;
    }

    @Override
    public HttpRequestBase createApacheRequest(HttpRequest smartsheetRequest) {
        HttpRequestBase apacheHttpRequest = super.createApacheRequest(smartsheetRequest);
        apacheHttpRequest.addHeader("Api-Scenario",apiScenario);
        return apacheHttpRequest;
    }
}
