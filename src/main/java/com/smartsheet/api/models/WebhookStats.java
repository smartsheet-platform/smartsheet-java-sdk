package com.smartsheet.api.models;

import java.util.Date;

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
public class WebhookStats {

	/**
	 * The number of retries the webhook had performed as of the last callback
	 */
	private Integer lastCallbackAttemptRetryCount;
	
	/**
	 * When this webhook last made a callback attempt.
	 */
	private Date lastCallbackAttempt;
	
	/**
	 * When this webhook last make a successful callback.
	 */
	private Date lastSuccessfulCallback;
	
	/**
	 * Get the number of retries the webhook had performed as of the last callback attempt.
	 * 
	 * @return lastCallbackAttemptRetryCount
	 */
	public Integer getLastCallbackAttemptRetryCount() {
		return lastCallbackAttemptRetryCount;
	}
	
	/**
	 * Set the number of retries the webhook had performed as of the last callback attempt.
	 * 
	 * @param lastCallbackAttemptRetryCount
	 */
	public void setLastCallbackAttemptRetryCount(Integer lastCallbackAttemptRetryCount) {
		this.lastCallbackAttemptRetryCount = lastCallbackAttemptRetryCount;
	}
	
	/**
	 * Get the timestamp from the last callback attempt.
	 * 
	 * @return lastCallbackAttempt
	 */
	public Date getLastCallbackAttempt() {
		return lastCallbackAttempt;
	}
	
	/**
	 * Set the timestamp from the last callback attempt.
	 * 
	 * @param lastCallbackAttempt
	 */
	public void setLastCallbackAttempt(Date lastCallbackAttempt) {
		this.lastCallbackAttempt = lastCallbackAttempt;
	}
	
	/**
	 * Get the timestamp from the last successful callback.
	 * 
	 * @return lastSuccessfulCallback
	 */
	public Date getLastSuccessfulCallback() {
		return lastSuccessfulCallback;
	}
	
	/**
	 * Set the timestamp from the last successful callback.
	 * 
	 * @param lastSuccessfulCallback
	 */
	public void setLastSuccessfulCallback(Date lastSuccessfulCallback) {
		this.lastSuccessfulCallback = lastSuccessfulCallback;
	}

}
