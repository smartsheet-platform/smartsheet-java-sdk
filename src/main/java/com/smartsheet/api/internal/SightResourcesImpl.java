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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.smartsheet.api.AuthorizationException;
import com.smartsheet.api.InvalidRequestException;
import com.smartsheet.api.ResourceNotFoundException;
import com.smartsheet.api.ServiceUnavailableException;
import com.smartsheet.api.ShareResources;
import com.smartsheet.api.SightResources;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.util.QueryUtil;
import com.smartsheet.api.internal.util.Util;
import com.smartsheet.api.models.ContainerDestination;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;
import com.smartsheet.api.models.Sight;

public class SightResourcesImpl extends AbstractResources implements SightResources {
	
	private ShareResources shares;
	
	/**
	 * Constructor.
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null
	 *
	 * @param smartsheet the smartsheet
	 */
	public SightResourcesImpl(SmartsheetImpl smartsheet) {
		super(smartsheet);
		this.shares = new ShareResourcesImpl(smartsheet, "sights");
	}
	/**
	 * <p>Gets the list of all Sights where the User has access.</p>
	 * 
	 * <p>It mirrors to the following Smartsheet REST API method: GET /sights</p>
	 *
	 * @param modifiedSince
	 * @return IndexResult object containing an array of Sight objects limited to the following attributes:
	 * 	id, name, accessLevel, permalink, createdAt, modifiedAt.
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */
	public PagedResult<Sight> listSights(PaginationParameters paging, Date modifiedSince) throws SmartsheetException {
		String path = "sights";
		
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		if (paging != null) {
			parameters = paging.toHashMap();
		}
		if (modifiedSince != null) {
			String isoDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(modifiedSince);
			parameters.put("modifiedSince", isoDate);
		}
		path += QueryUtil.generateUrl(null, parameters);
		return this.listResourcesWithWrapper(path, Sight.class);
	}

	/**
	 * <p>Get a specified Sight.</p>
	 * 
	 * <p>It mirrors to the following Smartsheet REST API method: GET /sights/{sightId}</p>
	 *
	 * @param sightId the Id of the Sight
	 * @return the Sight resource.
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */
	public Sight getSight(long sightId) throws SmartsheetException {
		return this.getResource("sights/" + sightId, Sight.class);
	}
	
	/**
	 * <p>Get a specified Sight.</p>
	 * 
	 * <p>It mirrors to the following Smartsheet REST API method: PUT /sights/{sightId}</p>
	 *
	 * @param sight - the Sight to update
	 * @return the updated Sight resource.
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */
	public Sight updateSight(Sight sight) throws SmartsheetException {
		Util.throwIfNull(sight);
		return this.updateResource("sights/" + sight.getId(), Sight.class, sight);
	}
	
	/**
	 * <p>Delete a specified Sight.</p>
	 * 
	 * <p>It mirrors to the following Smartsheet REST API method: DELETE /sights/{sightId}</p>
	 *
	 * @param sightId the Id of the Sight
	 *
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */
	public void deleteSight(long sightId) throws SmartsheetException {
		this.deleteResource("sights/" + sightId, Sight.class);
	}
	
	/**
	 * <p>Creates s copy of the specified Sight.</p>
	 * 
	 * <p>It mirrors to the following Smartsheet REST API method: POST /sights/{sightId}/copy</p>
	 *
	 * @param sightId the Id of the Sight
	 * @param destination the destination to copy to
	 * @return the newly created Sight resource.
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */	
	public Sight copySight(long sightId, ContainerDestination destination) throws SmartsheetException {
		return this.createResource("sights/" + sightId + "/copy", Sight.class, destination);		
	}
	
	/**
	 * <p>Creates s copy of the specified Sight.</p>
	 * 
	 * <p>It mirrors to the following Smartsheet REST API method: POST /sights/{sightId}/move</p>
	 *
	 * @param sightId the Id of the Sight
	 * @param destination the destination to copy to
	 * @return the newly created Sight resource.
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */	
	public Sight moveSight(long sightId, ContainerDestination destination) throws SmartsheetException {
		return this.createResource("sights/" + sightId + "/move", Sight.class, destination);		
	}
	
	/**
	 * <p>Return the ShareResources object that provides access to share resources associated with
	 * Sight resources.</p>
	 *
	 * @return the associated share resources
	 */	
	public ShareResources shareResources() {
		return this.shares;
	}
	
}
