package com.smartsheet.api.internal.json;

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
import com.smartsheet.api.models.Folder;
import com.smartsheet.api.models.Result;
import com.smartsheet.api.models.User;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class JacksonJsonSerializerTest {
    JacksonJsonSerializer jjs;

    @Before
    public void setUp() throws Exception {
        jjs = new JacksonJsonSerializer();
    }

    @Test
    public void testJacksonJsonSerializer() {}

    @Test
    public void testInit() {}

    @Test
    public void testSerialize() {
        try{
            // Illegal Argument due to null
            try{
                jjs.serialize(null, new ByteArrayOutputStream());
                fail("should throw exception");
            }catch(IllegalArgumentException ex){
                //Expected
            }

            // Illegal Argument due to null
            try{
                jjs.serialize(new Object(), null);
                fail("should throw exception");
            }catch(IllegalArgumentException ex){
                //Expected
            }

            // Illegal Argument due to null
            try{
                jjs.serialize(null, null);
                fail("should throw exception");
            }catch(IllegalArgumentException ex){
                //Expected
            }


        }catch(JSONSerializerException ex){
            fail("Shouldn't have thrown this exception: "+ex);
        }

        // Mapping Exception. Can't serialize to an object and can't create an empty bean serializer
        try{
            jjs.serialize(new Object(), new ByteArrayOutputStream());
            fail("Should throw a JSONMappingException");
        }catch(JSONSerializerException ex){
            // Expected
        }

        // Test successful serialization
        User user = new User();
        user.setEmail("test@test.com");
        try {
            jjs.serialize(user, new ByteArrayOutputStream());
        } catch (JSONSerializerException e) {
            fail("Shouldn't throw an exception");
        }

        // Test id field is ignored.
        User user1 = new User();
        user1.setId(123L);
        user1.setEmail("test@test.com");
        try{
            assertFalse("The id field should not be serialized. Instead the id is used in the url and not sent as part of the body.", jjs.serialize(user1).contains("id"));
        }catch(JSONSerializerException e){
            fail("Shouldn't throw an exception");
        }

        // Test IOException
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
            // Illegal argument due to null
            try {
                jjs.deserialize(null, null);
                fail("Exception should have been thrown.");
            } catch (IllegalArgumentException e) {
                // Expected
            }

            // Illegal argument due to null
            try {
                jjs.deserialize(User.class, null);
                fail("Exception should have been thrown.");
            } catch (IllegalArgumentException e) {
                // Expected
            }

            // ILlegal argument due to null
            try {
                jjs.deserialize(null, new ByteArrayInputStream(new byte[10]));
                fail("Exception should have been thrown.");
            } catch (IllegalArgumentException e) {
                // Expected
            }
        }catch(Exception ex){
            fail("Exception should not be thrown: "+ex);
        }


        // Test Successful deserialize of a serialized user back to a User Object

        // Serialize User
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        User originalUser = new User();
        originalUser.setFirstName("Test");
        originalUser.setId(123L);
        jjs.serialize(originalUser,b);

        // Deserialize User from a byte array
        User user = jjs.deserialize(User.class, new ByteArrayInputStream(b.toByteArray()));

        assertEquals(originalUser.getFirstName(), user.getFirstName());
        assertNotEquals("The id was not deserialized into the User object.", originalUser.getId(), user.getId());
    }

    @Test
    public void testDeserializeMap() throws JSONSerializerException, FileNotFoundException, IOException {
        // Test null pointer exceptions
        try {
            jjs.deserializeMap(null);
            fail("Exception should have been thrown.");
        } catch (IllegalArgumentException e) {
            // expected
        }

        // Parse Exception / invalid json
        try {
            String str = "test";
            jjs.deserializeMap(new ByteArrayInputStream(str.getBytes()));
            fail("Exception should have been thrown.");
        } catch (JSONSerializerException e) {
            // expected
        }

        // Mapping Exception. Can't deserialize a JSON array to a Map object as the key would be an int
        String str = "[\"test\",\"test1\"]";
        try{
            jjs.deserializeMap(new ByteArrayInputStream(str.getBytes()));
            fail("Exception should have been thrown.");
        }catch(JSONSerializerException ex){
            //expected
        }

        // IO Exception
        try {
            FileInputStream fis = new FileInputStream(File.createTempFile("json_test", ".tmp"));
            fis.close();

            jjs.deserializeMap(fis);
            fail("Should have thrown an IOException");
        } catch(JSONSerializerException ex) {
            //expected
        }

        // Valid deserialize
        str = "{'key':'value'},{'key':'value'}".replace("'", "\"");
        jjs.deserializeMap(new ByteArrayInputStream(str.getBytes()));
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

        // Test JsonParseException. Can't convert an invalid json array to a list.
        try{
            jjs.deserializeList(List.class, new ByteArrayInputStream("[broken jason".getBytes()));
            fail("Should have thrown a JsonParseException");
        }catch(JSONSerializerException e){
            // Expected
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
        originalList.add("something-else");
        b = new ByteArrayOutputStream();
        jjs.serialize(originalList, b);
        List<String> newList = jjs.deserializeList(String.class, new ByteArrayInputStream(b.toByteArray()));
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
        try{
            try {
                jjs.deserializeResult(null, null);
                fail("Exception should have been thrown.");
            } catch (IllegalArgumentException e) {
                // Expected
            }

            try {
                jjs.deserializeResult(User.class, null);
                fail("Exception should have been thrown.");
            } catch (IllegalArgumentException e) {
                // Expected
            }

            try {
                jjs.deserializeResult(null, new ByteArrayInputStream(new byte[10]));
                fail("Exception should have been thrown.");
            } catch (IllegalArgumentException e) {
                // Expected
            }
        }catch(Exception ex){
            fail("Exception should not be thrown: "+ex);
        }

        Result<Folder> result = new Result<Folder>();
        result.setMessage("Test Result");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Test successful deserialization
        try {
            jjs.serialize(result, outputStream);
            jjs.deserializeResult(Result.class, new ByteArrayInputStream(outputStream.toByteArray()));
        } catch (JSONSerializerException ex) {
            fail("Exception should not be thrown: "+ex);
        }

        // Test JSONMappingException - Test Mapping a list back to one object
        try{
            outputStream = new ByteArrayOutputStream();
            ArrayList<User> users = new ArrayList<User>();
            jjs.serialize(users, outputStream);
            jjs.deserializeResult(Result.class, new ByteArrayInputStream(outputStream.toByteArray()));
            fail("Exception should have been thrown");
        } catch (JSONSerializerException ex) {
            // Expected
        }

        // Test IOException
        try {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(File.createTempFile("json_test", ".tmp"));
                fis.close();
            } catch (Exception ex) {
                fail("Issue running a test where a temp file is being created."+ex);
            }

            jjs.deserializeResult(Result.class, fis);
            fail("Should have thrown an IOException");
        } catch(JSONSerializerException ex) {
            //expected
        }

        // Test JsonParseException
        try {
            jjs.deserializeResult(Result.class, new ByteArrayInputStream("{oops it's broken".getBytes()));
            fail("Should have thrown a JsonParseException");
        } catch (JSONSerializerException e) {
            // Expected
        }
    }

    @Test
    public void testDeserializeListResult() {
        try {
            try {
                jjs.deserializeListResult(null, null);
                fail("Exception should have been thrown.");
            } catch (IllegalArgumentException e) {
                // Expected
            }

            try {
                jjs.deserializeListResult(User.class, null);
                fail("Exception should have been thrown.");
            } catch (IllegalArgumentException e) {
                // Expected
            }

            try {
                jjs.deserializeListResult(null, new ByteArrayInputStream(new byte[10]));
                fail("Exception should have been thrown.");
            } catch (IllegalArgumentException e) {
                // Expected
            }
        }catch(Exception ex){
            fail("Exception should not be thrown: "+ex);
        }

        Result<ArrayList<Object>> result = new Result<ArrayList<Object>>();
        result.setMessage("Test Message");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Test successful deserialization
        try {
            jjs.serialize(result, outputStream);
            jjs.deserializeListResult(Result.class, new ByteArrayInputStream(outputStream.toByteArray()));
        } catch (JSONSerializerException ex) {
            fail("Exception should not be thrown: "+ex);
        }

        // Test IOException
        try {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(File.createTempFile("json_test", ".tmp"));
                fis.close();
            } catch (Exception ex) {
                fail("Issue running a test where a temp file is being created."+ex);
            }

            jjs.deserializeListResult(Result.class, fis);
            fail("Should have thrown an IOException");
        } catch(JSONSerializerException ex) {
            //expected
        }

        // Test JSONMappingException - Test Mapping a list back to one object
        try{
            outputStream = new ByteArrayOutputStream();
            ArrayList<User> users = new ArrayList<User>();
            jjs.serialize(users, outputStream);
            jjs.deserializeListResult(Result.class, new ByteArrayInputStream(outputStream.toByteArray()));
            fail("Exception should have been thrown");
        } catch (JSONSerializerException ex) {
            // Expected
        }


        // Test JsonParseException
        try {
            jjs.deserializeListResult(Result.class, new ByteArrayInputStream("{bad json".getBytes()));
            fail("Should have thrown a JsonParseException");
        } catch (JSONSerializerException e) {
            // Expected
        }
    }

}
