package com.server.config;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;

public interface DataSourceConfig { 

	public DataSource getDataSource();
	public DataSourceTransactionManager getTransactionManager(final DataSource dataSource);
	public PostgreSQLTemplate getPostgreSQLTemplate(final DataSource dataSource);
}
