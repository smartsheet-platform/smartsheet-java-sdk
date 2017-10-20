package com.smartsheet.api.internal.http;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Random;
import com.smartsheet.api.models.Error;
import com.smartsheet.api.retry.CalcBackoff;

/**
 * Default implementation of the backoff calculator class which uses exponential backoff
 */
public class DefaultCalcBackoff implements CalcBackoff {

    /** maximum total elapsed retry time. Will be set in the constructor */
    private long maxRetryTimeMillis;

    /** logger for general errors, warnings, etc */
    private static final Logger logger = LoggerFactory.getLogger(DefaultCalcBackoff.class);

    public DefaultCalcBackoff(long maxRetryTimeMillis) {
        this.maxRetryTimeMillis = maxRetryTimeMillis;
    }

    /**
     * The backoff calculation routine. Uses exponential backoff. If the maximum elapsed time
     * has expired, this calculation returns -1 causing the caller to fall out of the retry loop.
     * @param previousAttempts
     * @param totalElapsedTimeMillis
     * @param error
     * @return -1 to fall out of retry loop, positive number indicates backoff time
     */
    public long calcBackoff(int previousAttempts, long totalElapsedTimeMillis, Error error) {

        long backoffMillis = (long)(Math.pow(2, previousAttempts) * 1000) + new Random().nextInt(1000);

        if(totalElapsedTimeMillis + backoffMillis > maxRetryTimeMillis) {
            logger.info("Elapsed time " + totalElapsedTimeMillis + " + backoff time " + backoffMillis +
                    " exceeds max retry time " + maxRetryTimeMillis + ", exiting retry loop");
            return -1;
        }
        return backoffMillis;
    }
}

