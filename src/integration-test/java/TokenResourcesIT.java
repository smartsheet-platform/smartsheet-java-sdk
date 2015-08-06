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
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.HttpClient;
import com.smartsheet.api.internal.oauth.OAuthFlowImpl;
import com.smartsheet.api.oauth.*;
import org.junit.Before;
import org.junit.Test;

import java.util.EnumSet;
import java.util.List;

public class TokenResourcesIT extends ITResourcesImpl{
    Smartsheet smartsheet;

    @Before
    public void setUp() throws Exception {
        smartsheet = createAuthentication();
    }

    @Test
    public void tokenResources() throws Exception{
        //TODO
        OAuthFlow oAuthFlow = new OAuthFlowBuilder().setClientId("skth38gccnv3og69uo").setRedirectURL("https://www.google.com").setClientSecret("vnzw980ehmap1o3cqd").build();

//        String url = oAuthFlow.newAuthorizationURL(EnumSet.allOf(AccessScope.class), "test");
//
//        String redirectURI = "https%3A%2F%2Fwww.google.com/?code=abcdefghikjlmn&expires_in=1234567&state=test&client_id=skth38gccnv3og69uo";
//
//        AuthorizationResult authorizationResult = oAuthFlow.extractAuthorizationResult(redirectURI);

        //Token token = oAuthFlow.obtainNewToken(authorizationResult);
        //Token refreshToken = oAuthFlow.refreshToken(token);

        //oAuthFlow.revokeAccessToken(token);

        //smartsheet.tokenResources().revokeAccessToken();

    }
}
