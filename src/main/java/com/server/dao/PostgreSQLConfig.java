package com.server.dao;

import com.server.config.PostgreSQLTemplate;

public interface PostgreSQLConfig {

//	public DataSource setDataSource(final DataSource dataSource);
//	public DataSourceTransactionManager setTransactionManager(final DataSourceTransactionManager dataSourceTransactionManager);
	public PostgreSQLTemplate setPostgreSQLTemplate(final PostgreSQLTemplate jdbcTemplate);
}
