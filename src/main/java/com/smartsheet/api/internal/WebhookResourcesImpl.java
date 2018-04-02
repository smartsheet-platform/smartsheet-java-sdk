package com.smartsheet.api.internal;
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

import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.smartsheet.api.AuthorizationException;
import com.smartsheet.api.InvalidRequestException;
import com.smartsheet.api.ResourceNotFoundException;
import com.smartsheet.api.ServiceUnavailableException;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.WebhookResources;
import com.smartsheet.api.internal.http.HttpMethod;
import com.smartsheet.api.internal.http.HttpRequest;
import com.smartsheet.api.internal.http.HttpResponse;
import com.smartsheet.api.internal.util.QueryUtil;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;
import com.smartsheet.api.models.Webhook;
import com.smartsheet.api.models.WebhookSharedSecret;

public class WebhookResourcesImpl extends AbstractResources implements WebhookResources {

    public WebhookResourcesImpl(SmartsheetImpl smartsheet) {
        super(smartsheet);
    }

    /**
     * Gets the list of all Webhooks that the user owns (if a user generated token was used to make the request)
     * or the list of all Webhooks associated with the third-party app (if a third-party app made the request). Items
     * in the response are ordered by API Client name, then Webhook name, then creation date.
     *
     * It mirrors to the following Smartsheet REST API method: GET /webhooks
     *
     * @param paging the object containing the pagination parameters
     * @return IndexResult object containing an array of Webhook objects.
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public PagedResult<Webhook> listWebhooks(PaginationParameters paging) throws SmartsheetException {
        String path = "webhooks";

        HashMap<String, Object> parameters = new HashMap<String, Object>();
        if (paging != null) {
            parameters = paging.toHashMap();
        }

        path += QueryUtil.generateUrl(null, parameters);
        return this.listResourcesWithWrapper(path, Webhook.class);
    }

    /**
     * Gets the Webhook specified in the URL.
     *
     * It mirrors to the following Smartsheet REST API method: GET /webhooks/{webhookId}
     *
     * @param webhookId the Id of the webhook
     * @return the webhook resource.
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public Webhook getWebhook(long webhookId) throws SmartsheetException {
        return this.getResource("webhooks/" + webhookId, Webhook.class);
    }

    /**
     * Creates a new Webhook.
     *
     * It mirrors to the following Smartsheet REST API method: POST /webhooks
     *
     * @param webhook the webhook to be created
     * @return the webhook resource.
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public Webhook createWebhook(Webhook webhook) throws SmartsheetException {
        return this.createResource("webhooks", Webhook.class, webhook);
    }

    /**
     * Updates the webhooks specified in the URL.
     *
     * It mirrors to the following Smartsheet REST API method: PUT /webhooks/{webhookId}
     *
     * @param webhook the webhook to update
     * @return the updated webhook resource.
     * @throws SmartsheetException
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     */
    public Webhook updateWebhook(Webhook webhook) throws SmartsheetException {
        return this.updateResource("webhooks/" + webhook.getId(), Webhook.class, webhook);
    }

    /**
     * Delete a webhook.
     *
     * It mirrors to the following Smartsheet REST API method: DELETE /webhooks/{webhookId}
     *
     * @param webhookId the webhook Id
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public void deleteWebhook(long webhookId) throws SmartsheetException {
        this.deleteResource("webhooks/" + webhookId, Webhook.class);
    }

    /**
     * Resets the shared secret for the specified Webhook. For more information about how a shared secret is used,
     *  see Authenticating Callbacks.
     *
     * It mirrors to the following Smartsheet REST API method: POST /webhooks/{webhookId}/resetsharedsecret
     *
     * @param webhookId the webhook Id
     * @return the Webhook shared secret
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public WebhookSharedSecret resetSharedSecret(long webhookId) throws SmartsheetException {
        HttpRequest request = createHttpRequest(this.getSmartsheet().getBaseURI().resolve("webhooks/" +
                webhookId + "/resetsharedsecret"), HttpMethod.POST);

        HttpResponse response = getSmartsheet().getHttpClient().request(request);

        WebhookSharedSecret secret = null;
        switch (response.getStatusCode()) {
        case 200:
            try {
                secret = this.smartsheet.getJsonSerializer().deserialize(WebhookSharedSecret.class,
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

        getSmartsheet().getHttpClient().releaseConnection();
        return secret;
    }
}
