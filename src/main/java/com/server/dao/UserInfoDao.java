package com.server.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.server.model.ClientInfo;

public interface UserInfoDao {

	public List<ClientInfo> getClients()throws SQLException, DataAccessException;
	public ClientInfo getClientInfo(int id)throws SQLException, DataAccessException;
	public void insertClients(ClientInfo objClientInfo)throws SQLException, DataAccessException;
	
	
	
}
