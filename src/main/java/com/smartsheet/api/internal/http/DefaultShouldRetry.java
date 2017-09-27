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

import com.smartsheet.api.internal.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.smartsheet.api.internal.json.JacksonJsonSerializer;
import com.smartsheet.api.internal.json.JsonSerializer;
import com.smartsheet.api.retry.ShouldRetry;
import com.smartsheet.api.models.Error;
import com.smartsheet.api.retry.CalcBackoff;

import java.io.IOException;

/**
 * Implements the default handler for request failures.
 */
public class DefaultShouldRetry implements ShouldRetry {

    private JsonSerializer jsonSerializer;

    private CalcBackoff calcBackoff = null;

    /** logger for general errors, warnings, etc */
    private static final Logger logger = LoggerFactory.getLogger(DefaultCalcBackoff.class);

    /** Constructor */
    public DefaultShouldRetry(JsonSerializer jsonSerializer) {
        this.jsonSerializer = jsonSerializer == null ? new JacksonJsonSerializer() : jsonSerializer;
    }
    /** Set the backoff calculation callback for use during the retry loop. The default uses exponential backoff */
    public void setCalcBackoff(CalcBackoff calcBackoff) {
        this.calcBackoff = calcBackoff;
    }

    /**
     * Called by the DefaultHttpClient when an API request fails to determine if can retry the request.
     * Calls calcBackoff to determine the time to wait in between retries.
     * @param previousAttempts
     * @param totalElapsedTime
     * @param response the failed HttpResponse
     * @return true if this request can be retried
     * @throws IOException
     * @throws InterruptedException
     */
    public boolean shouldRetry(int previousAttempts, long totalElapsedTime, HttpResponse response) throws IOException, InterruptedException {
        Util.throwIfNull(calcBackoff);

        Error error;
        error = jsonSerializer.deserialize(Error.class, response.getEntity().getContent());
        switch(error.getErrorCode()) {
            case 4001: /** Smartsheet.com is currently offline for system maintenance. Please check back again shortly. */
            case 4002: /** Server timeout exceeded. Request has failed */
            case 4003: /** Rate limit exceeded. */
            case 4004: /** An unexpected error has occurred. Please retry your request.
                        * If you encounter this error repeatedly, please contact api@smartsheet.com for assistance. */
                break;
            default:
                return false;
        }

        long backoff = calcBackoff.calcBackoff(previousAttempts, totalElapsedTime, error);
        if(backoff < 0)
            return false;

        logger.info("HttpError StatusCode=" + response.getStatusCode() + ": Retrying in " + backoff + " milliseconds");
        Thread.sleep(backoff);
        return true;
    }
}
