/*
 * #[license]
 * Smartsheet SDK for Java
 * %%
 * Copyright (C) 2014 Smartsheet
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

package com.smartsheet.api.internal;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.smartsheet.api.AuthorizationException;
import com.smartsheet.api.ImageUrlResources;
import com.smartsheet.api.InvalidRequestException;
import com.smartsheet.api.ResourceNotFoundException;
import com.smartsheet.api.ServiceUnavailableException;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.HttpEntity;
import com.smartsheet.api.internal.http.HttpMethod;
import com.smartsheet.api.internal.http.HttpRequest;
import com.smartsheet.api.internal.http.HttpResponse;
import com.smartsheet.api.internal.json.JSONSerializerException;
import com.smartsheet.api.internal.util.QueryUtil;
import com.smartsheet.api.internal.util.Util;
import com.smartsheet.api.models.CopyOrMoveRowResult;
import com.smartsheet.api.models.Home;
import com.smartsheet.api.models.ImageUrl;
import com.smartsheet.api.models.ImageUrlMap;

public class ImageUrlResourcesImpl extends AbstractResources implements ImageUrlResources {

    /**
     * Constructor.
     *
     * Exceptions: - IllegalArgumentException : if any argument is null
     *
     * @param smartsheet the smartsheet
     */
    public ImageUrlResourcesImpl(SmartsheetImpl smartsheet) {
        super(smartsheet);
    }

    /**
     * Gets URLS that can be used to retieve the specified cell images.
     *
     * It mirrors to the following Smartsheet REST API method: POST /imageurls
     *
     * @param requestUrls array of requested Images ans sizes.
     * @return the ImageUrlMap object (note that if there is no such resource, this method will throw
     * ResourceNotFoundException rather than returning null).
     * @throws JSONSerializerException
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public ImageUrlMap getImageUrls(List<ImageUrl> requestUrls) throws SmartsheetException
    {
        Util.throwIfNull(requestUrls);

        HttpRequest request;
        request = createHttpRequest(smartsheet.getBaseURI().resolve("imageurls"), HttpMethod.POST);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        this.smartsheet.getJsonSerializer().serialize(requestUrls, baos);
        HttpEntity entity = new HttpEntity();
        entity.setContentType("application/json");
        entity.setContent(new ByteArrayInputStream(baos.toByteArray()));
        entity.setContentLength(baos.size());
        request.setEntity(entity);

        HttpResponse response = this.smartsheet.getHttpClient().request(request);

        ImageUrlMap obj = null;

        switch (response.getStatusCode()) {
            case 200:
                try {
                    obj = this.smartsheet.getJsonSerializer().deserialize(ImageUrlMap.class,
                            response.getEntity().getContent());
                } catch (JsonParseException e) {
                    throw new SmartsheetException(e);
                } catch (JsonMappingException e) {
                    throw new SmartsheetException(e);
                } catch (IOException e) {
                    throw new SmartsheetException(e);
                }
                break;
            default:
                handleError(response);
        }
        smartsheet.getHttpClient().releaseConnection();
        return obj;
    }
}
