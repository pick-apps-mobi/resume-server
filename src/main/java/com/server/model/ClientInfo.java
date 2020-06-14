package com.server.model;

import java.sql.SQLException;

import com.server.config.ResultSetMapperConfig;
import com.server.config.ResultSetRowMapper;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class ClientInfo implements ResultSetRowMapper<ClientInfo> {

	private int id;
	private String first_name;
    private String last_name;
    private String email_address;
	
    
    
    @Override
	public ClientInfo mapRow(ResultSetMapperConfig<ClientInfo> objMapper) throws SQLException {
    	ClientInfo objClientInfo = new ClientInfo();
    	objClientInfo.setEmail_address(objMapper.getString("email_address"));
    	objClientInfo.setFirst_name(objMapper.getString("first_name"));
    	objClientInfo.setLast_name(objMapper.getString("last_name"));
    	objClientInfo.setId(objMapper.getInteger("id"));
		return objClientInfo;
	}


	
}
