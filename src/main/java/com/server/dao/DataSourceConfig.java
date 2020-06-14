package com.server.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.server.config.PostgreSQLTemplate;

public interface DataSourceConfig { 

	public DataSource getDataSource();
	public DataSourceTransactionManager getTransactionManager(final DataSource dataSource);
	public PostgreSQLTemplate getPostgreSQLTemplate(final DataSource dataSource);
}
