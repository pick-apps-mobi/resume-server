package com.server.model;

import com.server.util.Tokens;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class BaseResponse {

	private int status;
	private boolean isSuccess;
	private String errorMessage;
	private String info = Tokens.DATA_FOUND.gettokens();




}
