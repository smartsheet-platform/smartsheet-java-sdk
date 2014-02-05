package com.smartsheet.api.internal;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.models.SearchResult;
import com.smartsheet.api.models.SearchResultItem;

public class SearchResourcesImplTest  extends ResourcesImplBase  {

	private SearchResourcesImpl searchResources;

	@Before
	public void setUp() throws Exception {
		searchResources = new SearchResourcesImpl(new SmartsheetImpl("http://localhost:9090/1.1/", 
				"accessToken", new DefaultHttpClient(), serializer));
	}

	@Test
	public void testSearchResourcesImpl() {
	}

	@Test
	public void testSearch() throws IOException, SmartsheetException {
		server.setResponseBody(new File("src/test/resources/search.json"));
		
		SearchResult result = searchResources.search("brett");
		assertNotNull(result.getResults());
		List<SearchResultItem> results = result.getResults();
		assertNotNull(results);
		assertEquals(50, results.size());
		assertEquals(50, result.getTotalCount().intValue());
		assertEquals("Brett Task Sheet", results.get(0).getText());
		assertEquals("sheet", results.get(0).getObjectType());
		assertEquals(714377448974212L, results.get(0).getObjectId().longValue());
		assertEquals("Platform / Team", results.get(0).getContextData().get(0));
	}

	@Test
	public void testSearchSheet() throws IOException, SmartsheetException {
		server.setResponseBody(new File("src/test/resources/searchSheet.json"));
		
		SearchResult searchSheet = searchResources.searchSheet(1234L, "java");
		assertNotNull(searchSheet);
		List<SearchResultItem> results = searchSheet.getResults();
		assertEquals(100,results.size());
		assertEquals(130, searchSheet.getTotalCount().intValue());
		assertEquals("HomeResources.java", results.get(0).getText());
		assertEquals("row", results.get(0).getObjectType());
		assertEquals(7243572589160324L, results.get(0).getObjectId().longValue());
		assertEquals("Row 12", results.get(0).getContextData().get(0));
		assertEquals("sheet", results.get(0).getParentObjectType());
		assertEquals(2630121841551236L, results.get(0).getParentObjectId().longValue());
		assertEquals("SDK Code Checklist", results.get(0).getParentObjectName());
	}

}
