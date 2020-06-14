
package com.server.dao;

import java.sql.SQLException;

import com.server.config.ResultSetMapperConfig;

public interface ResultSetRowMapper<T> {
	public T mapRow(ResultSetMapperConfig<T> objMapper) throws SQLException;
}
