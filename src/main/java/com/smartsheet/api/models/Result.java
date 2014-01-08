package com.smartsheet.api.models;

/**
 * Represents Result object.
 */
public class Result<T> {
	/**
	 * Represents the result code.
	 */
	private Integer resultCode;

	/**
	 * Represents the message.
	 */
	private String message;

	/**
	 * Represents the result.
	 */
	private T result;

	/**
	 * Represents the version.
	 */
	private Integer version;

	public Integer getResultCode() {
		return resultCode;
	}

	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	
}
