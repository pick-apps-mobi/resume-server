package com.server.config;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;


import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataSourceWrapper implements DataSource {

	private DataSource dataSource;
	private int connRetryCount;
	private int connRetryWaitTime;
	
	protected static final Logger log = LoggerFactory.getLogger("resumeLogger");
	private Long oneSec = (long) 1000.0;

	
	public DataSourceWrapper(final DataSource dataSource,final int connRetryCount,final int connRetryWaitTime) {
		super();
		this.dataSource = dataSource;
		this.connRetryCount = connRetryCount;
		this.connRetryWaitTime = connRetryWaitTime;
	}
	
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return this.dataSource.getLogWriter();
	}
	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		this.dataSource.setLogWriter(out);
	}

	@Override
	public int getLoginTimeout() throws SQLException {		
		return this.dataSource.getLoginTimeout();
	}
	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		this.dataSource.setLoginTimeout(seconds);
		
	}

	@Override
	public java.util.logging.Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return this.dataSource.getParentLogger();
	}
	@Override
	public boolean isWrapperFor(Class<?> wrapper) throws SQLException {
		return this.dataSource.isWrapperFor(wrapper);
	}

	@Override
	public <T> T unwrap(Class<T> wrapper) throws SQLException {
		return this.dataSource.unwrap(wrapper);
	}

	@Override
	public Connection getConnection() throws SQLException {
		int passes = 0;
		while(passes < connRetryCount) {
			try {
				return this.dataSource.getConnection();
			}catch (final SQLException e) {
				log.error("Exception trying to get Coonnection: {}",e);
			}
			passes++;
			if(passes < connRetryCount) {
				log.error("Failed to get Datasource or Connection - sleeping for : {} ", connRetryWaitTime);
				try {
					Thread.sleep(connRetryWaitTime * oneSec);
				}catch (final InterruptedException ie) {
					//If this thread was interrupted by another thread
					log.warn("Datasource sleep timer was interrupted");
					Thread.currentThread().interrupt();
				}
			}
		}
		log.info("getConnection ran out of tries to get a Datasource and Connection - Returning null ");
		return null;
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		return this.getConnection();
	}

}
