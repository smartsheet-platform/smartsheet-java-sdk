package com.smartsheet.api;
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

import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;
import com.smartsheet.api.models.Webhook;
import com.smartsheet.api.models.WebhookSharedSecret;

public interface WebhookResources {
    /**
     * <p>Gets the list of all Webhooks that the user owns (if a user generated token was used to make the request)
     * or the list of all Webhooks associated with the third-party app (if a third-party app made the request). Items
     * in the response are ordered by API Client name, then Webhook name, then creation date.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: GET /webhooks</p>
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
    PagedResult<Webhook> listWebhooks(PaginationParameters paging) throws SmartsheetException;

    /**
     * <p>Gets the Webhook specified in the URL.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: GET /webhooks/{webhookId}</p>
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
    Webhook getWebhook(long webhookId) throws SmartsheetException;

    /**
     * <p>Creates a new Webhook.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: POST /webhooks</p>
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
    Webhook createWebhook(Webhook webhook) throws SmartsheetException;

    /**
     * <p>Updates the webhooks specified in the URL.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: PUT /webhooks/{webhookId}</p>
     *
     * @param webhook the webhook to update
     * @return the updated webhook resource.
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    Webhook updateWebhook(Webhook webhook) throws SmartsheetException;

    /**
     * <p>Delete a webhook.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: DELETE /webhooks/{webhookId}</p>
     *
     * @param webhookId the webhook Id
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    void deleteWebhook(long webhookId) throws SmartsheetException;

    /**
     * <p>Resets the shared secret for the specified Webhook. For more information about how a shared secret is used,
     *  see Authenticating Callbacks.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: POST /webhooks/{webhookId}/resetsharedsecret</p>
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
    WebhookSharedSecret resetSharedSecret(long webhookId) throws SmartsheetException;
}
