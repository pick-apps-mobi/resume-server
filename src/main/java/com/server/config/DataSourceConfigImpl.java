package com.server.config;

import static com.server.util.AppConstants.JDBC_POSTGRESQL_CONNECTION_RETRY;
import static com.server.util.AppConstants.JDBC_POSTGRESQL_CONNECTION_RETRY_WAIT_IN_SECONDS;
import static com.server.util.AppConstants.JDBC_POSTGRESQL_DATABASE_NAME;
import static com.server.util.AppConstants.JDBC_POSTGRESQL_PASSWORD;
import static com.server.util.AppConstants.JDBC_POSTGRESQL_PORT;
import static com.server.util.AppConstants.JDBC_POSTGRESQL_USERNAME;
import static com.server.util.AppConstants.POSTGRESQL_DATATSOURCE_PROPERTIES;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.postgresql.ds.PGPoolingDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.server.dao.DataSourceConfig;

@Configuration
@PropertySource(value = {POSTGRESQL_DATATSOURCE_PROPERTIES})
public class DataSourceConfigImpl implements DataSourceConfig{

	@Resource
	private Environment env;
	
	protected static final Logger log = LoggerFactory.getLogger("resumeLogger");

	@Override
	@Bean(name = "postgresqlDataSource")
	public DataSource getDataSource() {
        PGPoolingDataSource ds = new PGPoolingDataSource();
        ds.setDatabaseName(env.getProperty(JDBC_POSTGRESQL_DATABASE_NAME));
        ds.setPortNumber(Integer.valueOf(env.getProperty(JDBC_POSTGRESQL_PORT)));
        ds.setUser(env.getProperty(JDBC_POSTGRESQL_USERNAME));
        ds.setPassword(env.getProperty(JDBC_POSTGRESQL_PASSWORD));
        /* the connection pool will have 10 to 20 connections */
        ds.setInitialConnections(10);
		/* ds.setMaxConnections(20); */
        /* use SSL connections without checking server certificate */
        ds.setSslfactory("org.postgresql.ssl.NonValidatingFactory");
        
        return new DataSourceWrapper(ds, Integer.valueOf(env.getProperty(JDBC_POSTGRESQL_CONNECTION_RETRY)), Integer.valueOf(env.getProperty(JDBC_POSTGRESQL_CONNECTION_RETRY_WAIT_IN_SECONDS)));

	}

	@Override
	@Bean(name = "postgresqlTransactionManager")
	public DataSourceTransactionManager getTransactionManager(@Qualifier("postgresqlDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Override
	@Bean(name = "postgresqlTemplate")
	public PostgreSQLTemplate getPostgreSQLTemplate(@Qualifier("postgresqlDataSource") DataSource dataSource) {
		return new PostgreSQLTemplate(dataSource);
	}
	
}
