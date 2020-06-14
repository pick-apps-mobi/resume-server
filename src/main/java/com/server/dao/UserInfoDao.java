package com.server.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.server.model.UserInfo;

public interface UserInfoDao {

	public List<UserInfo> getUserInfoAll()throws SQLException, DataAccessException;
	public UserInfo getUserInfo(int id)throws SQLException, DataAccessException;
	public void insertUserInfo(UserInfo objUserInfo)throws SQLException, DataAccessException;
	
	
	
}
