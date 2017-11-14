package com.smartsheet.api.internal;

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


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.smartsheet.api.AuthorizationException;
import com.smartsheet.api.InvalidRequestException;
import com.smartsheet.api.ResourceNotFoundException;
import com.smartsheet.api.ServiceUnavailableException;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.SmartsheetRestException;
import com.smartsheet.api.internal.http.HttpEntity;
import com.smartsheet.api.internal.http.HttpMethod;
import com.smartsheet.api.internal.http.HttpRequest;
import com.smartsheet.api.internal.http.HttpResponse;
import com.smartsheet.api.internal.json.JSONSerializerException;
import com.smartsheet.api.internal.util.StreamUtil;
import com.smartsheet.api.internal.util.Util;
import com.smartsheet.api.models.Attachment;
import com.smartsheet.api.models.CopyOrMoveRowDirective;
import com.smartsheet.api.models.CopyOrMoveRowResult;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.Result;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is the base class of the Smartsheet REST API resources.
 * 
 * Thread Safety: This class is thread safe because it is immutable and the underlying SmartsheetImpl is thread safe.
 */
public abstract class AbstractResources {
    /** this system property is used to control the number of characters logged from an API response in logs */
    public static final String PROPERTY_RESPONSE_LOG_CHARS = "Smartsheet.responseLogChars";

    private static final Logger log = LoggerFactory.getLogger(AbstractResources.class);

    /** The Constant BUFFER_SIZE. */
    private final static int BUFFER_SIZE = 4098;
    private Map<String, String> headers;


    /**
     * The Enum ErrorCode.
     */
    public enum ErrorCode {
        BAD_REQUEST(400, InvalidRequestException.class),
        NOT_AUTHORIZED(401, AuthorizationException.class),
        FORBIDDEN(403, AuthorizationException.class),
        NOT_FOUND(404, ResourceNotFoundException.class),
        METHOD_NOT_SUPPORTED(405, InvalidRequestException.class),
        INTERNAL_SERVER_ERROR(500, InvalidRequestException.class),
        SERVICE_UNAVAILABLE(503,ServiceUnavailableException.class);

        /** The error code. */
        int errorCode;

        /** The Exception class. */
        Class<? extends SmartsheetRestException> exceptionClass;

        /**
         * Instantiates a new error code.
         *
         * @param errorCode the error code
         * @param exceptionClass the Exception class
         */
        ErrorCode(int errorCode, Class<? extends SmartsheetRestException> exceptionClass) {
            this.errorCode = errorCode;
            this.exceptionClass = exceptionClass;
        }

        /**
         * Gets the error code.
         *
         * @param errorNumber the error number
         * @return the error code
         */
        public static ErrorCode getErrorCode(int errorNumber) {
            for (ErrorCode code : ErrorCode.values()) {
                if (code.errorCode == errorNumber) {
                    return code;
                }
            }

            return null;
        }

        /**
         * Gets the exception.
         *
         * @return the exception
         * @throws InstantiationException the instantiation exception
         * @throws IllegalAccessException the illegal access exception
         */
        public SmartsheetRestException getException() throws InstantiationException, IllegalAccessException {
            return exceptionClass.newInstance();
        }

        /**
         * Gets the exception.
         *
         * @param error the error
         * @return the exception
         * @throws SmartsheetException the smartsheet exception
         */
        public SmartsheetRestException getException(com.smartsheet.api.models.Error error) throws SmartsheetException  {

            try {
                return exceptionClass.getConstructor(com.smartsheet.api.models.Error.class).newInstance(error);
            } catch (IllegalArgumentException e) {
                throw new SmartsheetException(e);
            } catch (SecurityException e) {
                throw new SmartsheetException(e);
            } catch (InstantiationException e) {
                throw new SmartsheetException(e);
            } catch (IllegalAccessException e) {
                throw new SmartsheetException(e);
            } catch (InvocationTargetException e) {
                throw new SmartsheetException(e);
            } catch (NoSuchMethodException e) {
                throw new SmartsheetException(e);
            }
        }
    }

    /**
     * Represents the SmartsheetImpl.
     *
     * It will be initialized in constructor and will not change afterwards.
     */
    protected final SmartsheetImpl smartsheet;

    /**
     * Constructor.
     *
     * @param smartsheet the smartsheet
     */
    protected AbstractResources(SmartsheetImpl smartsheet) {
        Util.throwIfNull(smartsheet);

        this.smartsheet = smartsheet;
    }

    /**
     * Get a resource from Smartsheet REST API.
     *
     * Parameters: - path : the relative path of the resource - objectClass : the resource object class
     *
     * Returns: the resource (note that if there is no such resource, this method will throw ResourceNotFoundException
     * rather than returning null).
     *
     * Exceptions: -
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ResourceNotFoundException : if the resource can not be found
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param <T> the generic type
     * @param path the relative path of the resource.
     * @param objectClass the object class
     * @return the resource
     * @throws SmartsheetException the smartsheet exception
     */
    protected <T> T getResource(String path, Class<T> objectClass) throws SmartsheetException  {
        Util.throwIfNull(path, objectClass);

        if(path.isEmpty()) {
            com.smartsheet.api.models.Error error = new com.smartsheet.api.models.Error();
            error.setMessage("An empty path was provided.");
            throw new ResourceNotFoundException(error);
        }

        HttpRequest  request = createHttpRequest(smartsheet.getBaseURI().resolve(path), HttpMethod.GET);

        T obj = null;
        String content = null;
        try {
            HttpResponse response = this.smartsheet.getHttpClient().request(request);
            InputStream inputStream = response.getEntity().getContent();
            switch (response.getStatusCode()) {
                case 200:
                    try {
                        if (log.isInfoEnabled()) {
                            ByteArrayOutputStream contentCopyStream = new ByteArrayOutputStream();
                            inputStream = StreamUtil.cloneContent(inputStream, contentCopyStream);
                            content = StreamUtil.toUtf8StringOrHex(contentCopyStream, getResponseLogLength());
                        }
                        obj = this.smartsheet.getJsonSerializer().deserialize(objectClass, inputStream);
                    } catch (JsonParseException e) {
                        log.info("failure parsing '{}'", content, e);
                        throw new SmartsheetException(e);
                    } catch (JsonMappingException e) {
                        log.info("failure mapping '{}'", content, e);
                        throw new SmartsheetException(e);
                    } catch (IOException e) {
                        log.info("failure loading '{}'", content, e);
                        throw new SmartsheetException(e);
                    }
                    break;
                default:
                    handleError(response);
            }
        } catch (JSONSerializerException jsx) {
            log.info("failed to parse '{}'", content, jsx);
            throw jsx;
        } finally {
            smartsheet.getHttpClient().releaseConnection();
        }
        return obj;
    }

    /**
     * Create a resource using Smartsheet REST API.
     *
     * Exceptions:
     *   IllegalArgumentException : if any argument is null, or path is empty string
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param <T> the generic type of object to return/deserialize
     * @param <S> the generic type of object to serialize
     * @param path the relative path of the resource collections
     * @param objectClass the resource object class
     * @param object the object to create
     * @return the created resource
     * @throws SmartsheetException the smartsheet exception
     */
    protected <T, S> T createResource(String path, Class<T> objectClass, S object) throws SmartsheetException {
        Util.throwIfNull(path, object, objectClass);
        Util.throwIfEmpty(path);

        HttpRequest request = createHttpRequest(smartsheet.getBaseURI().resolve(path), HttpMethod.POST);

        ByteArrayOutputStream objectBytesStream = new ByteArrayOutputStream();
        this.smartsheet.getJsonSerializer().serialize(object, objectBytesStream);
        HttpEntity entity = new HttpEntity();
        entity.setContentType("application/json");
        entity.setContent(new ByteArrayInputStream(objectBytesStream.toByteArray()));
        entity.setContentLength(objectBytesStream.size());
        request.setEntity(entity);

        T obj = null;
        String content = null;
        try {
            HttpResponse response = this.smartsheet.getHttpClient().request(request);
            InputStream inputStream = response.getEntity().getContent();
            switch (response.getStatusCode()) {
                case 200:
                    // Can't be here as the stream has not ...???
                    try {
                        if (log.isInfoEnabled()) {
                            ByteArrayOutputStream contentCopyStream = new ByteArrayOutputStream();
                            inputStream = StreamUtil.cloneContent(inputStream, contentCopyStream);
                            content = StreamUtil.toUtf8StringOrHex(contentCopyStream, getResponseLogLength());
                        }
                        obj = this.smartsheet.getJsonSerializer().deserializeResult(objectClass, inputStream).getResult();
                    } catch (JsonParseException e) {
                        log.info("failure parsing '{}'", content, e);
                        throw new SmartsheetException(e);
                    } catch (JsonMappingException e) {
                        log.info("failure mapping '{}'", content, e);
                        throw new SmartsheetException(e);
                    } catch (IOException e) {
                        log.info("failure loading '{}'", content, e);
                        throw new SmartsheetException(e);
                    }
                    break;
                default:
                    handleError(response);
            }
        } finally {
            smartsheet.getHttpClient().releaseConnection();
        }

        return obj;
    }

    /**
     * Create a resource using Smartsheet REST API.
     *
     * Exceptions:
     *   IllegalArgumentException : if any argument is null, or path is empty string
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param <T> the generic type
     * @param path the relative path of the resource collections
     * @param objectClass the resource object class
     * @param object the object to create
     * @return the created resource
     * @throws SmartsheetException the smartsheet exception
     */
    protected <T> T createResourceWithAttachment(String path, Class<T> objectClass, T object, String partName,InputStream inputStream, String contentType, String attachmentName) throws SmartsheetException {
        Util.throwIfNull(path, object);
        Util.throwIfEmpty(path);

        HttpRequest request;
        final String boundary = "----" + System.currentTimeMillis() ;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost uploadFile = createHttpPost(this.getSmartsheet().getBaseURI().resolve(path));

        try {
            uploadFile.setHeader("Content-Type", "multipart/form-data; boundary=" + boundary);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setBoundary(boundary);
        builder.addTextBody(partName, this.getSmartsheet().getJsonSerializer().serialize(object), ContentType.APPLICATION_JSON);
        builder.addBinaryBody("file", inputStream, ContentType.create(contentType), attachmentName);
        org.apache.http.HttpEntity multipart = builder.build();

        uploadFile.setEntity(multipart);

        T obj = null;
        //implement switch case
        try {
            CloseableHttpResponse response = httpClient.execute(uploadFile);
            org.apache.http.HttpEntity responseEntity = response.getEntity();
            obj = this.getSmartsheet().getJsonSerializer().deserializeResult(objectClass,
                    responseEntity.getContent()).getResult();
        }
        catch (Exception e) {
            throw  new RuntimeException(e);
        }
        return obj;
    }

    /**
     * Update a resource using Smartsheet REST API.
     *
     * Exceptions:
     *   IllegalArgumentException : if any argument is null, or path is empty string
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ResourceNotFoundException : if the resource can not be found
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param <T> the generic type
     * @param path the relative path of the resource
     * @param objectClass the resource object class
     * @param object the object to create
     * @return the updated resource
     * @throws SmartsheetException the smartsheet exception
     */
    protected <T> T updateResource(String path, Class<T> objectClass, T object) throws SmartsheetException {
        Util.throwIfNull(path, object);
        Util.throwIfEmpty(path);

        HttpRequest request;
        request = createHttpRequest(smartsheet.getBaseURI().resolve(path), HttpMethod.PUT);

        ByteArrayOutputStream objectBytesStream = new ByteArrayOutputStream();
        this.smartsheet.getJsonSerializer().serialize(object, objectBytesStream);
        HttpEntity entity = new HttpEntity();
        entity.setContentType("application/json");
        entity.setContent(new ByteArrayInputStream(objectBytesStream.toByteArray()));
        entity.setContentLength(objectBytesStream.size());
        request.setEntity(entity);


        T obj = null;
        try {
            HttpResponse response = this.smartsheet.getHttpClient().request(request);
            switch (response.getStatusCode()) {
                case 200:
                    obj = this.smartsheet.getJsonSerializer().deserializeResult(objectClass,
                            response.getEntity().getContent()).getResult();
                    break;
                default:
                    handleError(response);
            }
        } finally {
            smartsheet.getHttpClient().releaseConnection();
        }

        return obj;
    }

    /**
     * List resources using Smartsheet REST API.
     *
     * Exceptions:
     *   IllegalArgumentException : if any argument is null, or path is empty string
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param <T> the generic type
     * @param path the relative path of the resource collections
     * @param objectClass the resource object class
     * @return the resources
     * @throws SmartsheetException if an error occurred during the operation
     */
    protected <T> List<T> listResources(String path, Class<T> objectClass) throws SmartsheetException {
        Util.throwIfNull(path, objectClass);
        Util.throwIfEmpty(path);

        HttpRequest request;
        request = createHttpRequest(smartsheet.getBaseURI().resolve(path), HttpMethod.GET);


        List<T> obj = null;
        try {
            HttpResponse response = this.smartsheet.getHttpClient().request(request);
            switch (response.getStatusCode()) {
                case 200:
                    obj = this.smartsheet.getJsonSerializer().deserializeList(objectClass,
                            response.getEntity().getContent());
                    break;
                default:
                    handleError(response);
            }
        } finally {
            smartsheet.getHttpClient().releaseConnection();
        }

        return obj;
    }

    /**
     * * List resources Wrapper (supports paging info) using Smartsheet REST API.
     *
     * Exceptions:
     *   IllegalArgumentException : if any argument is null, or path is empty string
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     * @param path
     * @param objectClass
     * @param <T>
     * @return
     * @throws SmartsheetException
     */
    protected <T> PagedResult<T> listResourcesWithWrapper(String path, Class<T> objectClass) throws SmartsheetException {
        Util.throwIfNull(path, objectClass);
        Util.throwIfEmpty(path);

        HttpRequest request;
        request = createHttpRequest(smartsheet.getBaseURI().resolve(path), HttpMethod.GET);


        PagedResult<T> obj = null;
        try {
            HttpResponse response = this.smartsheet.getHttpClient().request(request);
            switch (response.getStatusCode()) {
                case 200:
                    obj = this.smartsheet.getJsonSerializer().deserializeDataWrapper(objectClass,
                            response.getEntity().getContent());
                    break;
                default:
                    handleError(response);
            }
        } finally {
            smartsheet.getHttpClient().releaseConnection();
        }

        return obj;
    }

    /**
     * Delete a resource from Smartsheet REST API.
     *
     * Exceptions:
     *   IllegalArgumentException : if any argument is null, or path is empty string
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ResourceNotFoundException : if the resource can not be found
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param <T> the generic type
     * @param path the relative path of the resource
     * @param objectClass the resource object class
     * @throws SmartsheetException the smartsheet exception
     */
    protected <T> void deleteResource(String path, Class<T> objectClass) throws SmartsheetException {
        Util.throwIfNull(path, objectClass);
        Util.throwIfEmpty(path);

        HttpRequest request;
        request = createHttpRequest(smartsheet.getBaseURI().resolve(path), HttpMethod.DELETE);

        try {
            HttpResponse response = this.smartsheet.getHttpClient().request(request);
            switch (response.getStatusCode()) {
                case 200:
                    this.smartsheet.getJsonSerializer().deserializeResult(objectClass,
                            response.getEntity().getContent());
                    break;
                default:
                    handleError(response);
            }
        } finally {
            smartsheet.getHttpClient().releaseConnection();
        }
    }

    /**
     * Delete resources and return a list from Smartsheet REST API.
     *
     * Exceptions:
     *   IllegalArgumentException : if any argument is null, or path is empty string
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ResourceNotFoundException : if the resource can not be found
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param <T> the generic type
     * @param path the relative path of the resource
     * @param objectClass the resource object class
     * @return List of ids deleted
     * @throws SmartsheetException the smartsheet exception
     */
    protected <T> List<T> deleteListResources(String path, Class<T> objectClass) throws SmartsheetException {
        Util.throwIfNull(path, objectClass);
        Util.throwIfEmpty(path);

        Result<List<T>> obj = null;
        HttpRequest request;
        request = createHttpRequest(smartsheet.getBaseURI().resolve(path), HttpMethod.DELETE);
        try {
            HttpResponse response = this.smartsheet.getHttpClient().request(request);
            switch (response.getStatusCode()) {
                case 200:
                    obj = this.smartsheet.getJsonSerializer().deserializeListResult(objectClass,
                            response.getEntity().getContent());
                    break;
                default:
                    handleError(response);
            }
        } finally {
            smartsheet.getHttpClient().releaseConnection();
        }
        return obj.getResult();
    }

    /**
     * Post an object to Smartsheet REST API and receive a list of objects from response.
     *
     * Parameters: - path : the relative path of the resource collections - objectToPost : the object to post -
     * objectClassToReceive : the resource object class to receive
     *
     * Returns: the object list
     *
     * Exceptions:
     *   IllegalArgumentException : if any argument is null, or path is empty string
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param <T> the generic type
     * @param <S> the generic type
     * @param path the path
     * @param objectToPost the object to post
     * @param objectClassToReceive the object class to receive
     * @return the list
     * @throws SmartsheetException the smartsheet exception
     */
    protected <T, S> List<S> postAndReceiveList(String path, T objectToPost, Class<S> objectClassToReceive) throws SmartsheetException {
        Util.throwIfNull(path, objectToPost, objectClassToReceive);
        Util.throwIfEmpty(path);

        HttpRequest request = createHttpRequest(smartsheet.getBaseURI().resolve(path), HttpMethod.POST);

        ByteArrayOutputStream objectBytesStream = new ByteArrayOutputStream();
        this.smartsheet.getJsonSerializer().serialize(objectToPost, objectBytesStream);
        HttpEntity entity = new HttpEntity();
        entity.setContentType("application/json");
        entity.setContent(new ByteArrayInputStream(objectBytesStream.toByteArray()));
        entity.setContentLength(objectBytesStream.size());
        request.setEntity(entity);


        List<S> obj = null;
        try {
            HttpResponse response = this.smartsheet.getHttpClient().request(request);
            switch (response.getStatusCode()) {
                case 200:
                    obj = this.smartsheet.getJsonSerializer().deserializeListResult(objectClassToReceive,
                            response.getEntity().getContent()).getResult();
                    break;
                default:
                    handleError(response);
            }
        } finally {
            smartsheet.getHttpClient().releaseConnection();
        }

        return obj;
    }

    /**
     * Post an object to Smartsheet REST API and receive a CopyOrMoveRowResult object from response.
     *
     * Parameters: - path : the relative path of the resource collections - objectToPost : the object to post -
     *
     * Returns: the object
     *
     * Exceptions:
     *   IllegalArgumentException : if any argument is null, or path is empty string
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param path the path
     * @param objectToPost the object to post
     * @return the result object
     * @throws SmartsheetException the smartsheet exception
     */
    protected CopyOrMoveRowResult postAndReceiveRowObject(String path, CopyOrMoveRowDirective objectToPost) throws SmartsheetException {
        Util.throwIfNull(path, objectToPost);
        Util.throwIfEmpty(path);

        HttpRequest request = createHttpRequest(smartsheet.getBaseURI().resolve(path), HttpMethod.POST);

        ByteArrayOutputStream objectBytesStream = new ByteArrayOutputStream();
        this.smartsheet.getJsonSerializer().serialize(objectToPost, objectBytesStream);
        HttpEntity entity = new HttpEntity();
        entity.setContentType("application/json");
        entity.setContent(new ByteArrayInputStream(objectBytesStream.toByteArray()));
        entity.setContentLength(objectBytesStream.size());
        request.setEntity(entity);


        CopyOrMoveRowResult obj = null;
        try {
            HttpResponse response = this.smartsheet.getHttpClient().request(request);
            switch (response.getStatusCode()) {
                case 200:
                    obj = this.smartsheet.getJsonSerializer().deserializeCopyOrMoveRow(
                            response.getEntity().getContent());
                    break;
                default:
                    handleError(response);
            }
        } finally {
            smartsheet.getHttpClient().releaseConnection();
        }

        return obj;
    }

    /**
     * Put an object to Smartsheet REST API and receive a list of objects from response.
     *
     * Exceptions:
     *   IllegalArgumentException : if any argument is null, or path is empty string
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param <T> the generic type
     * @param <S> the generic type
     * @param path the relative path of the resource collections
     * @param objectToPut the object to put
     * @param objectClassToReceive the resource object class to receive
     * @return the object list
     * @throws SmartsheetException the smartsheet exception
     */
    protected <T, S> List<S> putAndReceiveList(String path, T objectToPut, Class<S> objectClassToReceive)
            throws SmartsheetException {
        Util.throwIfNull(path, objectToPut, objectClassToReceive);
        Util.throwIfEmpty(path);

        HttpRequest request = createHttpRequest(smartsheet.getBaseURI().resolve(path), HttpMethod.PUT);

        ByteArrayOutputStream objectBytesStream = new ByteArrayOutputStream();
        this.smartsheet.getJsonSerializer().serialize(objectToPut, objectBytesStream);
        HttpEntity entity = new HttpEntity();
        entity.setContentType("application/json");
        entity.setContent(new ByteArrayInputStream(objectBytesStream.toByteArray()));
        entity.setContentLength(objectBytesStream.size());
        request.setEntity(entity);


        List<S> obj = null;
        try {
            HttpResponse response = this.smartsheet.getHttpClient().request(request);
            switch (response.getStatusCode()) {
                case 200:
                    obj = this.smartsheet.getJsonSerializer().deserializeListResult(
                            objectClassToReceive, response.getEntity().getContent()).getResult();
                    break;
                default:
                    handleError(response);
            }
        } finally {
            smartsheet.getHttpClient().releaseConnection();
        }

        return obj;
    }


    /**
     * Create an HttpRequest.
     * <p>
     * Exceptions: Any exception shall be propagated since it's a private method.
     *
     * @param uri    the URI
     * @param method the HttpMethod
     * @return the http request
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    protected HttpRequest createHttpRequest(URI uri, HttpMethod method) {
        HttpRequest request = new HttpRequest();
        request.setUri(uri);
        request.setMethod(method);

        // Set authorization header
        request.setHeaders(createHeaders());

        return request;
    }

    protected HttpPost createHttpPost(URI uri) {
        HttpPost httpPost = new HttpPost(uri);
        Map<String, String> headers = createHeaders();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            httpPost.addHeader(entry.getKey(), entry.getValue());
        }
        return httpPost;
    }
    public Attachment attachFile(String url, InputStream inputStream, String contentType, long contentLength, String attachmentName)
            throws SmartsheetException {
        Util.throwIfNull(inputStream, contentType);
        HttpRequest request = createHttpRequest(this.getSmartsheet().getBaseURI().resolve(url), HttpMethod.POST);
        try {
            request.getHeaders().put("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(attachmentName, "UTF-8") + "\"");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        HttpEntity entity = new HttpEntity();
        entity.setContentType(contentType);
        entity.setContent(inputStream);
        entity.setContentLength(contentLength);
        request.setEntity(entity);


        Attachment attachment = null;
        try {
            HttpResponse response = this.getSmartsheet().getHttpClient().request(request);
            switch (response.getStatusCode()) {
                case 200:
                    attachment = this.getSmartsheet().getJsonSerializer().deserializeResult(Attachment.class,
                            response.getEntity().getContent()).getResult();
                    break;
                default:
                    handleError(response);
            }
        } finally {
            this.getSmartsheet().getHttpClient().releaseConnection();
        }

        return attachment;
    }

    /**
     * Create a multipart upload request.
     *
     * @param url the url
     * @param t the object to create
     * @param partName the name of the part
     * @param inputstream the file inputstream
     * @param contentType the type of the file to be attached
     * @return the http request
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    public <T> Attachment attachFile(String url, T t, String partName, InputStream inputstream, String contentType, String attachmentName)
            throws SmartsheetException {
        Util.throwIfNull(inputstream, contentType);
        Attachment attachment = null;
        final String boundary = "----" + System.currentTimeMillis() ;

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost uploadFile = createHttpPost(this.getSmartsheet().getBaseURI().resolve(url));

        try {
            uploadFile.setHeader("Content-Type", "multipart/form-data; boundary=" + boundary);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setBoundary(boundary);
        builder.addTextBody(partName, this.getSmartsheet().getJsonSerializer().serialize(t), ContentType.APPLICATION_JSON);
        builder.addBinaryBody("file", inputstream, ContentType.create(contentType), attachmentName);
        org.apache.http.HttpEntity multipart = builder.build();

        uploadFile.setEntity(multipart);

        try {
            CloseableHttpResponse response = httpClient.execute(uploadFile);
            org.apache.http.HttpEntity responseEntity = response.getEntity();
            attachment = this.getSmartsheet().getJsonSerializer().deserializeResult(Attachment.class,
                    responseEntity.getContent()).getResult();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return attachment;
    }


    /**
     * Handles an error HttpResponse (non-200) returned by Smartsheet REST API.
     *
     * Exceptions:
     *   SmartsheetRestException : the exception corresponding to the error
     *
     * @param response the HttpResponse
     * @throws SmartsheetException the smartsheet exception
     */
    protected void handleError(HttpResponse response) throws SmartsheetException {

        com.smartsheet.api.models.Error error;
        try {
            error = this.smartsheet.getJsonSerializer().deserialize(
                    com.smartsheet.api.models.Error.class, response.getEntity().getContent());
        } catch (JsonParseException e) {
            throw new SmartsheetException(e);
        } catch (JsonMappingException e) {
            throw new SmartsheetException(e);
        } catch (IOException e) {
            throw new SmartsheetException(e);
        }

        ErrorCode code = ErrorCode.getErrorCode(response.getStatusCode());

        if (code == null) {
            throw new SmartsheetRestException(error);
        }

        try {
            throw code.getException(error);
        } catch (IllegalArgumentException e) {
            throw new SmartsheetException(e);
        } catch (SecurityException e) {
            throw new SmartsheetException(e);
        }
    }

    /**
     * Gets the smartsheet.
     *
     * @return the smartsheet
     */
    public SmartsheetImpl getSmartsheet() {
        return smartsheet;
    }

    /**
     * Get a sheet as a file.
     *
     * Exceptions:
     *   - InvalidRequestException : if there is any problem with the REST API request
     *   - AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   - ResourceNotFoundException : if the resource can not be found
     *   - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   - SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   - SmartsheetException : if there is any other error occurred during the operation
     *
     * @param path the path
     * @param fileType the output file type
     * @param outputStream the OutputStream to which the file will be written
     * @return the report as file
     * @throws SmartsheetException the smartsheet exception
     */
    public void getResourceAsFile(String path, String fileType, OutputStream outputStream)
            throws SmartsheetException {
        Util.throwIfNull(outputStream, fileType);

        HttpRequest request;
        request = createHttpRequest(this.getSmartsheet().getBaseURI().resolve(path), HttpMethod.GET);
        request.getHeaders().put("Accept", fileType);

        try {
            HttpResponse response = getSmartsheet().getHttpClient().request(request);

            switch (response.getStatusCode()) {
                case 200:
                    try {
                        copyStream(response.getEntity().getContent(), outputStream);
                    } catch (IOException e) {
                        throw new SmartsheetException(e);
                    }
                    break;
                default:
                    handleError(response);
            }
        } finally {
            getSmartsheet().getHttpClient().releaseConnection();
        }
    }

    /*
     * Copy an input stream to an output stream.
     *
     * @param input The input stream to copy.
     *
     * @param output the output stream to write to.
     *
     * @throws IOException if there is trouble reading or writing to the streams.
     */
    /**
     * Copy stream.
     *
     * @param input the input
     * @param output the output
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static void copyStream(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[BUFFER_SIZE];
        int len;
        while ((len = input.read(buffer)) != -1) {
            output.write(buffer, 0, len);
        }
    }


    /**
     * @return a map of headers to be used when making requests.
     */
     Map<String,String> createHeaders() {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "Bearer " + smartsheet.getAccessToken());
        headers.put("Content-Type", "application/json");

        // Set assumed user
        if (smartsheet.getAssumedUser() != null) {
            try {
                headers.put("Assume-User", URLEncoder.encode(smartsheet.getAssumedUser(), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException ("Unsupported encode. You must support utf-8 for the Smartsheet Java SDK to work",e);
            }
        }
         if (smartsheet.getChangeAgent() != null) {
             try {
                 headers.put("Smartsheet-Change-Agent", URLEncoder.encode(smartsheet.getChangeAgent(), "utf-8"));
             } catch (UnsupportedEncodingException e) {
                 throw new RuntimeException ("Unsupported encode. You must support utf-8 for the Smartsheet Java SDK to work",e);
             }
         }
         if (smartsheet.getAPIScenario() != null && !smartsheet.getAPIScenario().isEmpty()) {
            headers.put("Api-Scenario", smartsheet.getAPIScenario());
         }
        return headers;
    }

    int getResponseLogLength() {
         // not cached to allow for it to be changed dynamically by client code
         return Integer.getInteger(PROPERTY_RESPONSE_LOG_CHARS, 1024);
    }
}
