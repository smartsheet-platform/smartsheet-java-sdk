package com.smartsheet.api.internal.http;

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


import com.smartsheet.api.Trace;
import com.smartsheet.api.internal.util.StreamUtil;
import org.apache.commons.codec.binary.Hex;
import org.apache.http.Header;
import org.apache.http.client.methods.HttpRequestBase;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * a POJO from which is generated JSON from HTTP request/response pairs
 */
public class RequestAndResponseData {
    public static abstract class HttpPayloadData {
        Map<String, String> headers = new TreeMap<String, String>();
        String body;

        public String getBody() {
            return body;
        }

        public Map<String, String> getHeaders() {
            return headers;
        }

        static abstract class Builder<Type extends HttpPayloadData> {
            public Builder addHeader(String key, String val) {
                getDataObject().headers.put(key, val);
                return this;
            }

            public Builder setBody(String body) {
                getDataObject().body = body;
                return this;
            }

            public abstract Type build();

            public abstract void reset();

            protected abstract Type getDataObject();
        }
    }

    public static class RequestData extends HttpPayloadData {
        private String command;

        public String getCommand() {
            return command;
        }

        public static class Builder extends HttpPayloadData.Builder<RequestData> {
            private RequestData dataObject;

            @Override
            public void reset() {
                dataObject = null;
            }

            @Override
            protected RequestData getDataObject() {
                if (dataObject == null) {
                    dataObject = new RequestData();
                }
                return dataObject;
            }

            public HttpPayloadData.Builder withCommand(String command) {
                getDataObject().command = command;
                return this;
            }

            public RequestData build() {
                try {
                    return dataObject;  // if nothing was added then nothing was built (i.e., this can be null)
                } finally {
                    reset();
                }
            }
        }
    }

    public static class ResponseData extends HttpPayloadData {
        private String status;

        public String getStatus() {
            return status;
        }

        public static class Builder extends HttpPayloadData.Builder<ResponseData> {
            private ResponseData dataObject;

            @Override
            public void reset() {
                dataObject = null;
            }

            @Override
            protected ResponseData getDataObject() {
                if (dataObject == null) {
                    dataObject = new ResponseData();
                }
                return dataObject;
            }

            public HttpPayloadData.Builder withStatus(String status) {
                getDataObject().status = status;
                return this;
            }

            public ResponseData build() {
                try {
                    return dataObject;  // if nothing was added then nothing was built (i.e., this can be null)
                } finally {
                    reset();
                }
            }
        }
    }

    private static int TRUNCATE_LENGTH = Integer.getInteger("Smartsheet.trace.truncateLen", 1024);

    public final RequestData request;
    public final ResponseData response;

    private RequestAndResponseData(RequestData requestData, ResponseData responseData) {
        request = requestData;
        response = responseData;
    }


    /**
     * factory method for creating a RequestAndResponseData object from request and response data with the specifid trace fields
     */
    public static RequestAndResponseData of(HttpRequestBase request, HttpEntity requestEntity,
                                            HttpResponse response, HttpEntity responseEntity,
                                            Set<Trace> traces)
            throws IOException {
        RequestData.Builder requestBuilder = new RequestData.Builder();
        ResponseData.Builder responseBuilder = new ResponseData.Builder();

        if (request != null) {
            requestBuilder.withCommand(request.getMethod() + " " + request.getURI());
            if (traces.contains(Trace.RequestHeaders) && request.getAllHeaders() != null) {
                for (Header header : request.getAllHeaders()) {
                    String headerName = header.getName();
                    String headerValue = header.getValue();
                    if ("Authorization".equals(headerName) && headerValue.length() > 0) {
                        headerValue = headerValue.substring(0, Math.min(10, headerValue.length() - 1)) + "****";
                    }
                    requestBuilder.addHeader(headerName, headerValue);
                }
            }
            if (requestEntity != null) {
                if (traces.contains(Trace.RequestBody)) {
                    requestBuilder.setBody(getContentAsText(requestEntity));
                } else if (traces.contains(Trace.RequestBodySummary)) {
                    requestBuilder.setBody(truncateAsNeeded(getContentAsText(requestEntity), TRUNCATE_LENGTH));
                }
            }
        }
        if (response != null) {
            responseBuilder.withStatus(response.getStatusText());
            if (traces.contains(Trace.ResponseHeaders) && response.getHeaders() != null) {
                for (Map.Entry<String, String> header : response.getHeaders().entrySet()) {
                    responseBuilder.addHeader(header.getKey(), header.getValue());
                }
            }
            if (responseEntity != null) {
                if (traces.contains(Trace.ResponseBody)) {
                    responseBuilder.setBody(getContentAsText(responseEntity));
                } else if (traces.contains(Trace.ResponseBodySummary)) {
                    responseBuilder.setBody(truncateAsNeeded(getContentAsText(responseEntity), TRUNCATE_LENGTH));
                }
            }
        }
        return new RequestAndResponseData(requestBuilder.build(), responseBuilder.build());
    }

    public static String getContentAsText(HttpEntity entity) throws IOException {
        if (entity == null) {
            return "";
        }
        InputStream inputStream = entity.getContent();
        if (inputStream == null) {
            return "";
        }
        final boolean markSupported = inputStream.markSupported();
        if (markSupported) {
            inputStream.mark(10 * 1024 * 1024);  // 10MB read buffer; beyond that it probably won't matter
        }

        byte[] contentBytes = StreamUtil.readBytesFromStream(inputStream);
        String contentAsText;
        try {
            contentAsText = new String(contentBytes, "UTF-8");
        } catch (UnsupportedEncodingException badEncodingOrNotText) {
            contentAsText = new String(Hex.encodeHex(contentBytes));
        }

        // since we've consumed the stream we have to reset it (note, this will have real perf impact if the stream
        // was to a large file or something else we'd rather not hold entirely in RAM if we can help it)
        if (markSupported) {
            inputStream.reset();
        } else {
            // we can't reset the stream so rebuild the stream around the bytes we read
            entity.setContent(new ByteArrayInputStream(contentBytes));
        }
        return contentAsText;
    }

    public static String truncateAsNeeded(String string, int truncateLen) {
        if (truncateLen == -1) {
            return string;
        }
        truncateLen = Math.min(string.length(), truncateLen);
        String suffix = truncateLen < string.length() ? "..." : "";
        return string.substring(0, truncateLen) + suffix;
    }
}
