package com.server.config;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

@Repository("postgreSQLConfig")
public class PostgreSQLConfigImpl implements PostgreSQLConfig {
	
	private static PostgreSQLTemplate postgreSQLTemplate;

	public static PostgreSQLTemplate getPostgreSQLTemplate() {
		return postgreSQLTemplate;
	}


	@Override
	@Resource(name ="postgresqlTemplate")
	public PostgreSQLTemplate setPostgreSQLTemplate(final PostgreSQLTemplate postgreSQLTemplate) {
		return PostgreSQLConfigImpl.postgreSQLTemplate = postgreSQLTemplate;
	}

}
