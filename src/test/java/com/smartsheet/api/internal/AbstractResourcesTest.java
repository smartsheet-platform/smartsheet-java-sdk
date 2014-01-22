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

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.naming.AuthenticationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.smartsheet.api.AuthorizationException;
import com.smartsheet.api.HttpTestServer;
import com.smartsheet.api.Smartsheet;
import com.smartsheet.api.SmartsheetRestException;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.internal.http.HttpClientException;
import com.smartsheet.api.internal.json.JSONSerializerException;
import com.smartsheet.api.internal.json.JacksonJsonSerializer;
import com.smartsheet.api.models.Folder;
//FIXME: don't test the abstract class. Instead test the class that inherits.
public class AbstractResourcesTest {

	TestAbstractResources resourcesLocal;
	TestAbstractResources resourcesRemote;
	HttpTestServer server;
	
	@Before
	public void setUp() throws Exception {
//		resourcesLocal = new TestAbstractResources(new SmartsheetImpl("http://localhost:9090/1.1/","accessToken",
//				new DefaultHttpClient(), new JacksonJsonSerializer()));
//		
//		resourcesRemote = new TestAbstractResources(new SmartsheetImpl("https://api.smartsheet.com/1.1/","accessToken",
//				new DefaultHttpClient(), new JacksonJsonSerializer()));
//		
//		server = new HttpTestServer();
//		server.setPort(9090);
//		server.start();
	}
	
	@After
	public void tearDown() throws Exception {
		//server.stop();
	}

	@Test
	public void testAbstractResources() {
//		try{
//			new TestAbstractResources(null);
//			fail("Exception should have been thrown.");
//		}catch(IllegalArgumentException ex){
//			// Expected
//		}
	}

	@Test
	public void testGetResource() throws JsonParseException, JsonMappingException, HttpClientException,
		SmartsheetRestException, IllegalArgumentException, SecurityException, IOException, 
		InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		
//		// Set a fake response
//		server.setMockResponseData(new File("src/test/resources/getFolder.json"));
//		
//		// Send the request for a folder
//		Folder folder = resourcesLocal.getResource("folder/", Folder.class);
//		
//		// Verify results
//		assertEquals("Personal",folder.getName());
//		assertEquals((Long)1138268709382020L,(Long)folder.getId());
//		assertEquals(2, folder.getSheets().size());
//		assertEquals(0, folder.getFolders().size());
	}

	@Test
	public void testCreateResource() {
//		resourcesLocal.createResource("folder/", Folder.class, new Folder());
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateResource() {
		fail("Not yet implemented");
	}

	@Test
	public void testListResources() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteResource() {
		fail("Not yet implemented");
	}

	@Test
	public void testPostAndReceiveList() {
		fail("Not yet implemented");
	}

	@Test
	public void testPutAndReceiveList() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateHttpRequest() {
		fail("Not yet implemented");
	}

	@Test
	public void testHandleError() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSmartsheet() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetSmartsheet() {
		fail("Not yet implemented");
	}

}

class TestAbstractResources extends AbstractResources {

	protected TestAbstractResources(SmartsheetImpl smartsheet) {
		super(smartsheet);
	}
	
}
