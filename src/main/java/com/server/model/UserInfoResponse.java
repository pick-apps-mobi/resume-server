package com.server.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserInfoResponse extends BaseResponse {
	private List<UserInfo> lst;
	private UserInfo objUserInfo;
	
	
}
