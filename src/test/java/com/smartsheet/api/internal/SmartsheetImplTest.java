package com.smartsheet.api.internal;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.smartsheet.api.internal.http.DefaultHttpClient;

public class SmartsheetImplTest extends ResourcesImplBase {

	private SmartsheetImpl smartsheet;

	@Before
	public void setUp() throws Exception {
		smartsheet = new SmartsheetImpl("http://localhost:9090/1.1/", "accessToken", 
				new DefaultHttpClient(), serializer);
	}

	@Test
	public void testFinalize() {
		
	}

	@Test
	public void testSmartsheetImpl() {
		
	}

	@Test
	public void testGetHttpClient() {
		
	}

	@Test
	public void testGetJsonSerializer() {
		
	}

	@Test
	public void testGetBaseURI() {
		
	}

	@Test
	public void testGetAssumedUser() {
		
	}

	@Test
	public void testGetAccessToken() {
		
	}

	@Test
	public void testHome() {
		assertNotNull(smartsheet.home());
	}

	@Test
	public void testWorkspaces() {
		assertNotNull(smartsheet.workspaces());
	}

	@Test
	public void testFolders() {
		assertNotNull(smartsheet.folders());
	}

	@Test
	public void testTemplates() {
		assertNotNull(smartsheet.templates());
	}

	@Test
	public void testSheets() {
		assertNotNull(smartsheet.sheets());
	}

	@Test
	public void testColumns() {
		assertNotNull(smartsheet.columns());
	}

	@Test
	public void testRows() {
		assertNotNull(smartsheet.rows());
	}

	@Test
	public void testAttachments() {
		assertNotNull(smartsheet.attachments());
	}

	@Test
	public void testDiscussions() {
		assertNotNull(smartsheet.discussions());
	}

	@Test
	public void testComments() {
		assertNotNull(smartsheet.comments());
	}

	@Test
	public void testUsers() {
		assertNotNull(smartsheet.users());
	}

	@Test
	public void testSearch() {
		assertNotNull(smartsheet.search());
	}

	@Test
	public void testSetAssumedUser() {
		smartsheet.setAssumedUser("user");
	}

	@Test
	public void testSetAccessToken() {
		smartsheet.setAccessToken("1234");
	}

}
