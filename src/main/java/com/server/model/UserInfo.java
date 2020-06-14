package com.server.model;

import java.sql.SQLException;

import com.server.config.ResultSetMapperConfig;
import com.server.dao.ResultSetRowMapper;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class UserInfo implements ResultSetRowMapper<UserInfo> {

	private int id;
	private String first_name;
    private String last_name;
    private String email_address;
	
    
    
    @Override
	public UserInfo mapRow(ResultSetMapperConfig<UserInfo> objMapper) throws SQLException {
    	UserInfo objUserInfo = new UserInfo();
    	objUserInfo.setEmail_address(objMapper.getString("email_address"));
    	objUserInfo.setFirst_name(objMapper.getString("first_name"));
    	objUserInfo.setLast_name(objMapper.getString("last_name"));
    	objUserInfo.setId(objMapper.getInteger("id"));
		return objUserInfo;
	}


	
}
