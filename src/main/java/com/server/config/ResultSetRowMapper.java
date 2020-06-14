
package com.server.config;

import java.sql.SQLException;

public interface ResultSetRowMapper<T> {
	public T mapRow(ResultSetMapperConfig<T> objMapper) throws SQLException;
}
