package com.smartsheet.api.internal.util;

/*
 * #[license]
 * Smartsheet Java SDK
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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class QueryUtil {

    public QueryUtil() {}

    /**
     * Returns a comma seperated list of items as a string
     * @param list the collecion
     * @param <T> the type
     * @return comma separated string
     */
    public static <T> String generateCommaSeparatedList(Collection<T> list) {
        if (list == null || list.size() == 0) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (Object obj : list) {
            result.append(',').append(obj.toString());
        }
        return result.length() == 0 ? "" : result.substring(1);
    }

    public static String generateUrl(String baseUrl, Map<String, Object> parameters) {
        if (baseUrl == null) {
            baseUrl = "";
        }
        return baseUrl + generateQueryString(parameters);
    }

    /**
     * Returns a query string.
     *
     * @param parameters the map of query string keys and values
     * @return the query string
     */
    protected static String generateQueryString(Map<String, Object> parameters) {
        if (parameters == null || parameters.size() == 0) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        try {
            for(Map.Entry<String, Object> entry : parameters.entrySet()) {
                // Check to see if the key/value isn't null or empty string
                if (entry.getKey() != null && (entry.getValue() != null && !entry.getValue().toString().equals(""))) {
                    result.append('&').append(URLEncoder.encode(entry.getKey(), "utf-8")).append("=")
                            .append(URLEncoder.encode(entry.getValue().toString(), "utf-8"));
                }
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        return result.length() == 0 ? "" : "?" + result.substring(1);
    }
}
