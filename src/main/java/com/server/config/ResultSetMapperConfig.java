
package com.server.config;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ResultSetMapperConfig<T extends Object>  {

	private static volatile ResultSetMapperConfig<Object> _instance  = null;
	protected Set<String> availableColumns;
	protected ResultSet resultSet;
	private final String prefix = "";
	
	private String field;
	private int value;


	public String getfield() {
		return field;
	}
	
	public void setfield(String field) {
		this.field = field;
	}
	public int getvalue() {
		return value;
	}
	public void setvalue(int value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "where "+ getfield() +" = " + getvalue();
	}
	
	private ResultSetMapperConfig() {

	}

	public static ResultSetMapperConfig<Object> getInstance() { 
		if(_instance == null){
			synchronized(ResultSetMapperConfig.class) {
				if (_instance == null) {
					_instance = new ResultSetMapperConfig<Object>();
				}
			} 
		} 
		return (ResultSetMapperConfig<Object>) _instance;
	}

	protected void init(ResultSet resultSet) throws SQLException {
		this.resultSet = resultSet;
		availableColumns = new HashSet<String>();
		ResultSetMetaData meta = this.resultSet.getMetaData();
		for (int i = 1, n = meta.getColumnCount() + 1; i < n; i++){
			availableColumns.add(meta.getColumnName(i).toLowerCase());
		}
	}

	public boolean column(String sName) {
		String name = sName;
		return (availableColumns == null?false:availableColumns.contains(name.toLowerCase()));
	}

	public long getLong(String sName) throws SQLException {
		if (column(prefix + sName))
			return resultSet.getLong(prefix + sName);
		else
			return 0l;
	}

	public String getString(String sName) throws SQLException {
		if (column(prefix + sName))
			return resultSet.getString(prefix + sName);
		else
			return null;
	}

	public int getInteger(String sName) throws SQLException {
		if (column(prefix + sName))
			return resultSet.getInt(prefix + sName);
		else
			return 0;
	}

	public double getDouble(String sName) throws SQLException {
		if (column(prefix + sName))
			return resultSet.getDouble(prefix + sName);
		else
			return 0.0d;
	}

	public float getFloat(String sName) throws SQLException {
		if (column(prefix + sName))
			return resultSet.getFloat(prefix + sName);
		else
			return 0.0f;
	}

	public boolean getBoolean(String sName) throws SQLException {
		if (column(prefix + sName))
			return resultSet.getBoolean(prefix + sName);
		else
			return false;
	}

	public short getShort(String sName) throws SQLException {
		if (column(prefix + sName))
			return resultSet.getShort(prefix + sName);
		else
			return 0;
	}

	public byte getByte(String sName) throws SQLException {
		if (column(prefix + sName))
			return resultSet.getByte(prefix + sName);
		else
			return 0;
	}
	public Date getDate(String sName) throws SQLException {
		if (column(prefix + sName))
			return resultSet.getDate(prefix + sName);
		else
			return null;
	}

	public byte[] getByteArray(String sName) throws SQLException {
		if (column(prefix + sName))
			return resultSet.getBytes(prefix + sName);
		else
			return null;
	}


}
