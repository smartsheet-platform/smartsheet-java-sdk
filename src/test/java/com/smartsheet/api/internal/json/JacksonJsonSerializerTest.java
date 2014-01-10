package com.smartsheet.api.internal.json;

import static org.junit.Assert.*;

import java.util.List;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.smartsheet.api.models.User;

public class JacksonJsonSerializerTest {
	JacksonJsonSerializer jjs;
	
	@Before
	public void setUp() throws Exception {
		JacksonJsonSerializer.init();
		jjs = new JacksonJsonSerializer();
	}

	@Test
	public void testJacksonJsonSerializer() {
		fail("Not yet implemented");
	}

	@Test
	public void testInit() {
		fail("Not yet implemented");
	}

	@Test(expected=JsonGenerationException.class)
	public void testSerialize() {
		try{
			try{
				jjs.serialize(null, new ByteArrayOutputStream());
				fail("should throw exception");
			}catch(IllegalArgumentException ex){
				//Expected
			}
			
			try{
				jjs.serialize(new Object(), null);
				fail("should throw exception");
			}catch(IllegalArgumentException ex){
				//Expected
			}
			
			try{
				jjs.serialize(null, null);
				fail("should throw exception");
			}catch(IllegalArgumentException ex){
				//Expected
			}
		}catch(JSONSerializerException ex){
			fail("Shouldn't have thrown this exception: "+ex);
		}
		
		try{
			jjs.serialize(new Object(), new ByteArrayOutputStream());
			fail("Should throw a JSONMappingException");
		}catch(JSONSerializerException ex){
			// Expected
		}
		
		User user = new User();
		user.setEmail("test@test.com");
		try {
			jjs.serialize(user, new ByteArrayOutputStream());
		} catch (JSONSerializerException e) {
			fail("Shouldn't throw an exception");
		}
		
		// Test JsonGenerationException
		File tempFile = null;
		try {
			tempFile = File.createTempFile("json_test", ".tmp");
			FileOutputStream fos = new FileOutputStream(tempFile);
			fos.close();
			try {
				jjs.serialize(user, fos);
			} catch (JSONSerializerException e) {
				// Expected
				
			}
		} catch (IOException e1) {
			fail("Trouble creating a temp file");
		}
	}

	@Test
	public void testDeserialize() throws JSONSerializerException, JsonParseException, JsonMappingException, IOException {
		try{
			try {
				jjs.deserialize(null, null);
				fail("Exception should have been thrown.");
			} catch (IllegalArgumentException e) {
				// Expected
			}
			
			try {
				jjs.deserialize(User.class, null);
				fail("Exception should have been thrown.");
			} catch (IllegalArgumentException e) {
				// Expected
			}
			
			try {
				jjs.deserialize(null, new ByteArrayInputStream(new byte[10]));
				fail("Exception should have been thrown.");
			} catch (IllegalArgumentException e) {
				// Expected
			}
		}catch(Exception ex){
			fail("Exception should not be thrown: "+ex);
		}
		
		// Serialize User
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		User originalUser = new User();
		jjs.serialize(originalUser,b);//b has the user in json format in a byte array
		
		
		// Deserialize User from a byte array
		User user = jjs.deserialize(User.class, new ByteArrayInputStream(b.toByteArray()));
		
		// Check if the original user object and the serialized/deserialized user object are still the same.
		if(!user.equals(originalUser)){
			fail("User class was not correctly deserialized from a byte array");
		}
	}

	@Test
	public void testDeserializeList() throws JsonParseException, IOException, JSONSerializerException {
		// Test null pointer exceptions
		try {
			jjs.deserializeList(null, null);
			fail("Exception should have been thrown.");
		} catch (IllegalArgumentException e) {
			// expected
		}
		try {
			jjs.deserializeList(ArrayList.class, null);
			fail("Exception should have been thrown.");
		} catch (IllegalArgumentException e) {
			// expected
		}
		try {
			jjs.deserializeList(null, new ByteArrayInputStream(new byte[10]));
			fail("Exception should have been thrown.");
		} catch (IllegalArgumentException e) {
			// expected
		}
		
		// Serialize a User and fail since it is not an ArrayList
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		User originalUser = new User();
		jjs.serialize(originalUser,b);//b has the user in json format in a byte array
		try{
			jjs.deserializeList(ArrayList.class, new ByteArrayInputStream(b.toByteArray()));
			fail("Exception should have been thrown.");
		}catch(JSONSerializerException ex){
			//expected
		}
		
		// Test serializing/deserializing a simple ArrayList
		jjs = new JacksonJsonSerializer();
		List<String> originalList = new ArrayList<String>();
		originalList.add("something");
		b = new ByteArrayOutputStream();
		jjs.serialize(originalList, b);
		List<ArrayList> newList = jjs.deserializeList(ArrayList.class, new ByteArrayInputStream(b.toByteArray()));
		// Verify that the serialized/deserialized object is equal to the original object.
		if(!newList.equals(originalList)){
			fail("Types should be identical. Serialization/Deserialation might have failed.");
		}
		
		// Test JSONSerializerException
		
		// Test IOException
		try {
			FileInputStream fis = new FileInputStream(File.createTempFile("json_test", ".tmp"));
			fis.close();
			
			jjs.deserializeList(List.class, fis);
			fail("Should have thrown an IOException");
		} catch(JSONSerializerException ex) {
			//expected
		}
	}
	
	

	@Test
	public void testDeserializeResult() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeserializeListResult() {
		fail("Not yet implemented");
	}

}
