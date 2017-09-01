package com.smartsheet.api;

/*
 * #[license]
 * Smartsheet Java SDK
 * %%
 * Copyright (C) 2014 - 2017 Smartsheet
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


import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * this enum is used to control what from the HTTP request and response is logged
 */
public enum Trace {
    RequestHeaders,
    RequestBody,
    RequestBodySummary,
    ResponseHeaders,
    ResponseBody,
    ResponseBodySummary,
    Request {
        @Override
        public boolean addReplacements(Set<Trace> traces) {
            traces.add(RequestHeaders);
            traces.add(RequestBodySummary);
            return true;
        }
    },
    Response {
        @Override
        public boolean addReplacements(Set<Trace> traces) {
            traces.add(ResponseHeaders);
            traces.add(ResponseBodySummary);
            return true;
        }
    },
    ;
    // this makes it possible to add other (or additional) Traces for specific types
    public boolean addReplacements(Set<Trace> traces) { return false; }

    public static Set<Trace> parse(String traces) {
        if (traces == null || traces.trim().isEmpty()) {
            return Collections.emptySet();
        }
        String[] parts = traces.split("[, ]+");
        Set<Trace> results = new HashSet<Trace>(parts.length + parts.length / 2); // pad for load-factor
        for (String part : parts) {
            try {
                Trace trace = valueOf(part);
                if (!trace.addReplacements(results)) {
                    results.add(trace);
                }
            } catch (IllegalArgumentException iax) {
                LoggerFactory.getLogger(Trace.class).warn("invalid trace in parse() - '{}'", part);
            }
        }
        return results.size() > 0 ? results : Collections.<Trace>emptySet();
    }
}
