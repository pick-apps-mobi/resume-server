package com.server.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.server.config.PostgreSQLConfigImpl;
import com.server.dao.UserInfoDao;
import com.server.model.UserInfo;

@Repository
public class UserInfoDaoImpl extends PostgreSQLConfigImpl implements UserInfoDao {

	protected static final Logger log = LoggerFactory.getLogger("resumeLogger");

	@Override
	public List<UserInfo> getUserInfoAll() throws SQLException,DataAccessException {
		List<UserInfo> sl= getPostgreSQLTemplate().queryForMappingList("select * from employees",UserInfo.class);
		return sl;

	}

	@Override
	public void insertUserInfo(UserInfo objUserInfo)throws SQLException, DataAccessException {
//		getPostgreSQLTemplate().
//		NamedParameterJdbcTemplate
	}

	@Override
	public UserInfo getUserInfo(int id) throws SQLException, DataAccessException {
		UserInfo obj= getPostgreSQLTemplate().queryForMappingObject("select * from employees where id='"+id+"'", UserInfo.class);
		return obj;
	}

}
