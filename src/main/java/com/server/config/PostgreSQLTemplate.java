package com.server.config;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlProvider;
import org.springframework.jdbc.core.StatementCallback;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

public class PostgreSQLTemplate extends JdbcTemplate{

	private static final ResultSetMapperConfig<?> objAbastract = ResultSetMapperConfig.getInstance();

	public PostgreSQLTemplate(DataSource dataSource) {
		super(dataSource);
	}

	@Nullable
	public <T> T queryForMappingObject(final String sql, final Class<?> entityClass) throws DataAccessException {
		Assert.notNull(sql, "SQL must not be null");
		Assert.notNull(entityClass, "Class must not be null");
		if (logger.isDebugEnabled()) {
			logger.debug("Executing SQL query [" + sql + "]");
		}
		
		class QueryStatementCallback implements StatementCallback<T>, SqlProvider {
			@SuppressWarnings("unchecked")
			@Override
			@Nullable
			public T doInStatement(Statement stmt) throws SQLException {
				ResultSet rs = null;
				Object obj  = null;
				try {
					Method lMethod = entityClass.getDeclaredMethod("mapRow", new Class<?>[] { ResultSetMapperConfig.class});
					rs = stmt.executeQuery(sql);
					objAbastract.init(rs);
					while (objAbastract.resultSet.next()) {
						obj =  ReflectionUtils.invokeMethod(lMethod, entityClass.newInstance(),objAbastract);
					}
				} catch (SecurityException |InstantiationException | IllegalAccessException |NoSuchMethodException  e) {
					logger.error("Exception while invoking or finding  maprow method inside Mapping class "+entityClass.getName()+"" ,  e);
					throw new SQLException(e);
				}
				finally {
					JdbcUtils.closeResultSet(rs);
					JdbcUtils.closeResultSet(objAbastract.resultSet);
				}
				return (T) obj;
			}
			@Override
			public String getSql() {
				return sql;
			}
		}

		return execute(new QueryStatementCallback());
	}
	
	@Nullable
	public <T> T queryForMappingList(final String sql, final Class<?> entityClass) throws DataAccessException {
		Assert.notNull(sql, "SQL must not be null");
		Assert.notNull(entityClass, "Class must not be null");
		if (logger.isDebugEnabled()) {
			logger.debug("Executing SQL query [" + sql + "]");
		}
		
		class QueryStatementCallback implements StatementCallback<T>, SqlProvider {
			@SuppressWarnings("unchecked")
			@Override
			@Nullable
			public T doInStatement(Statement stmt) throws SQLException {
				ResultSet rs = null;
				List<Object> obj  = new ArrayList<>();
				try {
					Method lMethod = entityClass.getDeclaredMethod("mapRow", new Class<?>[] { ResultSetMapperConfig.class});
					rs = stmt.executeQuery(sql);
					objAbastract.init(rs);
					while (objAbastract.resultSet.next()) {
						obj.add(ReflectionUtils.invokeMethod(lMethod, entityClass.newInstance(),objAbastract));
					}
				} catch (SecurityException |InstantiationException | IllegalAccessException |NoSuchMethodException  e) {
					logger.error("Exception while invoking or finding  maprow method inside Mapping class "+entityClass.getName()+"" ,  e);
					throw new SQLException(e);
				}
				finally {
					JdbcUtils.closeResultSet(rs);
					JdbcUtils.closeResultSet(objAbastract.resultSet);
				}
				return (T) obj;
			}
			@Override
			public String getSql() {
				return sql;
			}
		}

		return execute(new QueryStatementCallback());
	}

}
