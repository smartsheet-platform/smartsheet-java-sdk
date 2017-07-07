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
import java.util.Set;

public class QueryUtil {

    public QueryUtil() {}

    /**
     * Returns a comma seperated list of items as a string
     * @param list the collecion
     * @param <T> the type
     * @return comma separated string
     */
    public static <T> String generateCommaSeparatedList(Collection<T> list) {

        StringBuilder result = new StringBuilder();

        if (list == null) {
            return result.toString();
        } else {
            int index = 0;
            for (Object obj : list) {
                result.append(obj.toString());

                if (index != list.size() - 1) {
                    result.append(",");
                }
                index++;
            }
            return result.toString();
        }
    }

    public static String generateUrl(String baseUrl, Map<String, Object> parameters) {
        if (baseUrl == null) {
            baseUrl = "";
        }

        return new StringBuilder().append(baseUrl).append(generateQueryString(parameters)).toString();
    }

    /**
     * Returns a query string.
     *
     * @param parameters the map of query string keys and values
     * @return the query string
     */
    protected static String generateQueryString(Map<String, Object> parameters) {
        StringBuilder result = new StringBuilder();

        if (parameters == null) {
            return result.toString();
        } else {
            try {
                int parametersIndex = 0;
                for(Map.Entry<String, Object> entry : parameters.entrySet()) {
                    // Check to see if the key/value isn't null or empty string
                    if (entry.getKey() != null && (entry.getValue() != null && !entry.getValue().toString().equals(""))) {
                        if (parametersIndex == 0) {
                            result.append("?");
                        } else {
                            result.append("&");
                        }

                        result.append(URLEncoder.encode(entry.getKey(), "utf-8"));
                        result.append("=");
                        result.append(URLEncoder.encode(entry.getValue().toString(), "utf-8"));

                        parametersIndex++;
                    }
                }
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }

            return result.toString();
        }
    }
}
