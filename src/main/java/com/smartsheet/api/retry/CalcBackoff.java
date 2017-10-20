package com.smartsheet.api.retry;
/*
 * #[license]
 * Smartsheet SDK for Java
 * %%
 * Copyright (C) 2017 Smartsheet
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

import com.smartsheet.api.models.Error;

/**
 * Interface for user provided backoff calculation callbacks
 */
public interface CalcBackoff {
    /**
     * Determines the backoff time after a failed API call. The default implementation uses
     * an exponential backoff calculation.
     *
     * @param previousAttempts
     * @param totalElapsedTimeMillis
     * @param error
     * @return a positive value indicates that API requests should continue to be made,
     *         a return value of -1 will cause the caller to drop out of the retry loop.
     */
    long calcBackoff(int previousAttempts, long totalElapsedTimeMillis, Error error);
}
