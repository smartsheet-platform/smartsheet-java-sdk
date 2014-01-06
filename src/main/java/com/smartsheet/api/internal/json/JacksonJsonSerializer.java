package com.smartsheet.api.internal.json;
import java.util.List;

import com.fasterxml.jackson.databind.*;
import com.smartsheet.api.models.*;
/**
 * This is the Jackson based JsonSerializer implementation.
 * 
 * Thread Safety:
 * This class is thread safe because it is immutable and the underlying Jackson ObjectMapper is thread safe as long as it is not re-configured.
*/
public class JacksonJsonSerializer implements JsonSerializer {
/**
 * Represents the ObjectMapper used to serialize/de-serialize JSON.
 * 
 * It will be initialized in a static initializer and will not change afterwards.
 * 
 * Because ObjectMapper is thread-safe as long as it's not reconfigured, a static final class-level ObjectMapper is used to achieve best performance.
*/
private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
/**
 * Constructor.
 * 
 * Parameters:
 * None
 * 
 * Exceptions:
 * None
 * 
 * Implementation:
 * Do nothing.
*/
public JacksonJsonSerializer() {
}
/**
 * This is the static initializer of this class.
 * 
 * Implementation:
 * OBJECT_MAPPER = new ObjectMapper();
 * OBJECT_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
 * OBJECT_MAPPER.setSerializationInclusion(Include.NON_NULL);
 * OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
 * OBJECT_MAPPER.addMixInAnnotations(IdentifiableModel.class, IdFieldExclusionMixin.class);
*/
public static void init() {

}
/**
 * Serialize an object to JSON.
 * 
 * Parameters:
 * - object : the object to serialize
 * - outputStream : the output stream to which the JSON will be written 
 * 
 * Returns:
 * None
 * 
 * Exceptions:
 * - IllegalArgumentException : if any argument is null
 * - JSONSerializerException : if there is any other error occurred during the operation
 * 
 * Implementation:
 * OBJECT_MAPPER.writeValue(outputStream, object);
 * @param outputStream 
 * @param object 
*/
public <T> void serialize(T object, java.io.OutputStream outputStream) {
}
/**
 * De-serialize an object from JSON.
 * 
 * Parameters:
 * - objectClass : the class of the object to de-serialize
 * - inputStream : the input stream from which the JSON will be read 
 * 
 * Returns:
 * the de-serialized object
 * 
 * Exceptions:
 * - IllegalArgumentException : if any argument is null
 * - JSONSerializerException : if there is any other error occurred during the operation
 * 
 * Implementation:
 * return OBJECT_MAPPER.readValue(inputStream, objectClass);
 * @param inputStream 
 * @param objectClass 
 * @return 
*/
public <T> T deserialize(Class<T> objectClass, java.io.InputStream inputStream) {
    return null;
}
/**
 * De-serialize an object list from JSON.
 * 
 * Parameters:
 * - objectClass : the class of the object (of the list) to de-serialize
 * - inputStream : the input stream from which the JSON will be read 
 * 
 * Returns:
 * the de-serialized list
 * 
 * Exceptions:
 * - IllegalArgumentException : if any argument is null
 * - JSONSerializerException : if there is any other error occurred during the operation
 * 
 * Implementation:
 * return OBJECT_MAPPER.readValue(inputStream, OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, objectClass));
 * @param inputStream 
 * @param objectClass 
 * @return 
*/
public <T> List<T> deserializeList(Class<T> objectClass, java.io.InputStream inputStream) {
    return null;
}
/**
 * De-serialize a Result<T> object from JSON.
 * 
 * Parameters:
 * - objectClass : the class of the object (of the Result) to de-serialize
 * - inputStream : the input stream from which the JSON will be read 
 * 
 * Returns:
 * the de-serialized result
 * 
 * Exceptions:
 * - IllegalArgumentException : if any argument is null
 * - JSONSerializerException : if there is any other error occurred during the operation
 * 
 * Implementation:
 * return OBJECT_MAPPER.readValue(inputStream, OBJECT_MAPPER.getTypeFactory().constructParametricType(Result.class, objectClass));
 * @param inputStream 
 * @param objectClass 
 * @return 
*/
public <T> Result<T> deserializeResult(Class<T> objectClass, java.io.InputStream inputStream) {
    return null;
}
/**
 * De-serialize a Result<List<T>> object from JSON.
 * 
 * Parameters:
 * - objectClass : the class of the object (of the Result) to de-serialize
 * - inputStream : the input stream from which the JSON will be read 
 * 
 * Returns:
 * the de-serialized result
 * 
 * Exceptions:
 * - IllegalArgumentException : if any argument is null
 * - JSONSerializerException : if there is any other error occurred during the operation
 * 
 * Implementation:
 * return OBJECT_MAPPER.readValue(inputStream, OBJECT_MAPPER.getTypeFactory().constructParametricType(Result.class, OBJECT_MAPPER.getTypeFactory().constructParametricType(List.class, objectClass)));
 * @param inputStream 
 * @param objectClass 
 * @return 
*/
public <T> Result<List<T>> deserializeListResult(Class<T> objectClass, java.io.InputStream inputStream) {
    return null;
}
}

