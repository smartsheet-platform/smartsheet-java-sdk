/*
 * #[license]
 * Smartsheet Java SDK
 * %%
 * Copyright (C) 2014 - 2015 Smartsheet
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
import com.smartsheet.api.oauth.OAuthFlow;
import com.smartsheet.api.oauth.OAuthFlowBuilder;
import org.junit.Before;
import org.junit.Test;

public class TokenResourcesIT extends ITResourcesImpl{
    Smartsheet smartsheet;

    @Before
    public void setUp() throws Exception {
        smartsheet = createAuthentication();
    }

    @Test
    public void tokenResources() throws Exception{
        //TODO
        OAuthFlow oAuthFlow = new OAuthFlowBuilder().setClientId("YOUR_CLIENT_ID").setRedirectURL("https://www.google.com").setClientSecret("YOUR_CLIENT_SECRET").build();

        //String url = oAuthFlow.newAuthorizationURL(EnumSet.allOf(AccessScope.class), "key=IntegrationTest");

        // Take the user to the following URL
        //System.out.println(url);

        // After the user accepts or declines the authorization they are taken to the redirect URL. The URL of the page
        // the user is taken to can be used to generate an authorization Result object.
        //String redirectURI = "https://www.google.com/?code=116f3n7c3k1gqixe&expires_in=599237&state=key%3DIntegrationTest";

        //AuthorizationResult authorizationResult = oAuthFlow.extractAuthorizationResult(redirectURI);

        // Get the token from the authorization result
        //Token token = oAuthFlow.obtainNewToken(authorizationResult);

        // Refresh token
        //Token refreshToken = oAuthFlow.refreshToken(token);

        //Revoke access token
        //oAuthFlow.revokeAccessToken(token);

    }
}
