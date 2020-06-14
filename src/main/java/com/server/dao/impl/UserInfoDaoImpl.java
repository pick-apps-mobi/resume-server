package com.server.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.server.config.PostgreSQLConfigImpl;
import com.server.dao.UserInfoDao;
import com.server.model.ClientInfo;

@Repository
public class UserInfoDaoImpl extends PostgreSQLConfigImpl implements UserInfoDao {

	protected static final Logger log = LoggerFactory.getLogger("resumeLogger");

	@Override
	public List<ClientInfo> getClients() throws SQLException,DataAccessException {
		List<ClientInfo> sl= getPostgreSQLTemplate().queryForMappingList("select * from employees",ClientInfo.class);
		return sl;

	}

	@Override
	public void insertClients(ClientInfo objClientInfo)throws SQLException, DataAccessException {
//		getPostgreSQLTemplate().
		NamedParameterJdbcTemplate
	}

	@Override
	public ClientInfo getClientInfo(int id) throws SQLException, DataAccessException {
		ClientInfo obj= getPostgreSQLTemplate().queryForMappingObject("select * from employees where id='"+id+"'", ClientInfo.class);
		return obj;
	}

}
