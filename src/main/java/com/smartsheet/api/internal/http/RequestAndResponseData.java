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
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * a struct for creating pretty-formatted output from HTTP request/response pairs
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
                    return getDataObject(); // FIXME - here make the parts immutable
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
                    return getDataObject(); // FIXME - here make the parts immutable
                } finally {
                    reset();
                }
            }
        }
    }

    public final RequestData request;
    public final ResponseData response;

    private RequestAndResponseData(RequestData requestData, ResponseData responseData) {
        request = requestData;
        response = responseData;
    }


    /**
     * factory method for creating a RequestAndResponseData object from request and response data with the specifid trace fields
     */
    public static RequestAndResponseData of(HttpRequestBase request, HttpEntity reqEntity,
                                            HttpResponse response, HttpEntity respEntity, Set<Trace> traces)
            throws IOException {
        RequestData.Builder requestBuilder = new RequestData.Builder();
        ResponseData.Builder responseBuilder = new ResponseData.Builder();

        requestBuilder.withCommand(request.getMethod() + " " + request.getURI());
        if (traces.contains(Trace.RequestHeaders) && request.getAllHeaders() != null) {
            for (Header header : request.getAllHeaders()) {
                requestBuilder.addHeader(header.getName(), header.getValue());
            }
        }
        if (traces.contains(Trace.RequestBody)) {
            requestBuilder.setBody(getContentAsText(reqEntity));
        } else if (traces.contains(Trace.RequestBodySummary)) {
            requestBuilder.setBody(truncateAsNeeded(getContentAsText(reqEntity), getTruncateLength()));
        }

        responseBuilder.withStatus(response.getStatusText());
        if (traces.contains(Trace.ResponseHeaders) && response.getHeaders() != null) {
            for (Map.Entry<String,String> header : response.getHeaders().entrySet()) {
                responseBuilder.addHeader(header.getKey(), header.getValue());
            }
        }
        if (traces.contains(Trace.ResponseBody)) {
            responseBuilder.setBody(getContentAsText(respEntity));
        } else if (traces.contains(Trace.ResponseBodySummary)) {
            responseBuilder.setBody(truncateAsNeeded(getContentAsText(respEntity), getTruncateLength()));
        }
        return new RequestAndResponseData(requestBuilder.build(), responseBuilder.build());
    }

    static String toString(Header[] headers) {
        StringBuilder headerBuf = new StringBuilder();
        for (Header header : headers) {
            headerBuf.append(',').append(header.getName()).append(':');
            if ("Authorization".equals(header.getName())) {
                headerBuf.append("Bearer ***");
            } else {
                headerBuf.append(Arrays.toString(header.getElements()));
            }
        }
        return "[" + headerBuf.substring(1) + "]";
    }

    public static String getContentAsText(HttpEntity entity) throws IOException {
        if (entity == null) {
            return "";
        }
        InputStream inputStream = entity.getContent();
        if (inputStream.markSupported()) {
            inputStream.mark(0);
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
        if (inputStream.markSupported()) {
            inputStream.reset();
        } else {
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

    static int getTruncateLength() {
        return Integer.getInteger("Smartsheet.traceTruncateTo", 1024);
    }
}
