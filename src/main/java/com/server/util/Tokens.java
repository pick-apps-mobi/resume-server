
package com.server.util;


public enum Tokens
{  
	DATA_FOUND("DATA FOUND"),
	NO_DATA_FOUND("NO DATA FOUND"),
	ISSUE_WHEN_INVOKING_FUNCTION("ISSUE WHEN INVOKING FUNCTION");

	private Tokens(String tokens) {
		this.tokens = tokens;

	}
	private final String tokens;

	public String gettokens(){  
		return this.tokens;  
	}  
}