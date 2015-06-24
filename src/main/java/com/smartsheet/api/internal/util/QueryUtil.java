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

import com.smartsheet.api.models.ObjectInclusion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.EnumSet;

public class QueryUtil {

    public QueryUtil() {}

    /**
     * Returns a query string for pagination parameters.
     *
     * @param includeAll include all items
     * @param pageSize the page size
     * @param page the page
     * @return the query string
     */
    public static String handlePaginationQueryParameters(boolean includeAll, Integer pageSize, Integer page) {
        HashMap<String, String> parameters = new HashMap<String, String>();

        parameters.put("includeAll", Boolean.toString(includeAll));

        if (includeAll) {
            return generateQueryString(parameters);
        } else {
            if (pageSize != null) {
                parameters.put("pageSize", pageSize.toString());
            }
            if (page != null) {
                parameters.put("page", page.toString());
            }
            return generateQueryString(parameters);
        }
    }

    /**
     * Returns a comma separate list of items as a string.
     *
     * @param list the list of items
     * @return comma separated string
     */
    public static <T> String generateCommaSeparatedListFromList(List<T> list) {
        String result = "";

        if (list == null) {
            return result;
        } else {
            int index = 0;
            for (T item : list) {
                result += item.toString();
                if (index != list.size() - 1) {
                    result += ",";
                }
                index++;
            }
            return result;
        }
    }

    /**
     * Returns a comma separated list of items as a string
     * @param e the EnumSet
     * @return comma separated string
     */
    public static String generateCommaSeparatedListFromEnumSet(EnumSet e) {
        String result = "";

        if (e == null) {
            return result;
        } else {
            int index = 0;
            for (Object obj : e) {
                result += ((Enum)obj).name().toLowerCase();
                if (index != e.size() - 1) {
                    result += ",";
                }
                index++;
            }
            return result;
        }
    }

    /**
     * Returns a query string.
     *
     * @param parameters the map of query string keys and values
     * @return the query string
     */
    public static String generateQueryString(HashMap<String, String> parameters) {
        String result = "";

        if (parameters == null) {
            return result;
        } else {
            int parametersIndex = 0;
            for(Map.Entry<String, String> entry : parameters.entrySet()) {
                if (parametersIndex == 0) {
                    result += "?";
                } else {
                    result += "&";
                }

                result += entry.getKey() + "=" + entry.getValue();
                parametersIndex++;
            }
            return result;
        }
    }

    public  static String generateReportQueryString(EnumSet<ObjectInclusion> includes, boolean includeAll, Integer pageSize, Integer page){
        String query = handlePaginationQueryParameters(includeAll,pageSize,page);

        if (includes == null)
            return  query;
        else
         query += generateReportIncludesQueryString(includes);
        return query;
    }

    public static String generateReportIncludesQueryString(EnumSet e) {
        String result = "";
            int index = 0;
            for (Object obj : e) {
                result += ((Enum)obj).name().toLowerCase();
                if (index != e.size() - 1) {
                    result += ",";
                }
                index++;
            }
            return "&include=" + result;
        }
    }



