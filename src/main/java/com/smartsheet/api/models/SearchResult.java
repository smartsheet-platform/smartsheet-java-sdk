package com.smartsheet.api.models;

import java.util.List;

/**
 * Represents SearchResult object.
 */
public class SearchResult {
	/**
	 * Represents total count.
	 */
	private Integer totalCount;

	/**
	 * Represents the search result items.
	 */
	private List<SearchResultItem> results;
}
