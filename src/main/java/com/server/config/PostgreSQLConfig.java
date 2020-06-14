package com.server.config;

public interface PostgreSQLConfig {

//	public DataSource setDataSource(final DataSource dataSource);
//	public DataSourceTransactionManager setTransactionManager(final DataSourceTransactionManager dataSourceTransactionManager);
	public PostgreSQLTemplate setPostgreSQLTemplate(final PostgreSQLTemplate jdbcTemplate);
}
