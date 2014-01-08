package com.smartsheet.api.oauth;

/**
 * Represents the access scope.
 */
public enum AccessScope {
	READ_SHEETS("READ_SHEETS"), WRITE_SHEETS("WRITE_SHEETS"), SHARE_SHEETS("SHARE_SHEETS"), DELETE_SHEETS(
			"DELETE_SHEETS"), CREATE_SHEETS("CREATE_SHEETS"), ADMIN_USERS("ADMIN_USERS"), ADMIN_SHEETS("ADMIN_SHEETS"), ADMIN_WORKSPACES(
			"ADMIN_WORKSPACES");

	String scope;

	AccessScope(String scope) {
		this.scope = scope;
	}
}
