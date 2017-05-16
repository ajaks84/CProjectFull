package com.deshand.adc.dao.db.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang3.Validate;

/**
 * This class is put here for a one reason: you probably working with Postgres.
 */

public class SchemaAwareBasicDataSource extends BasicDataSource {

	private String schema;

	/**
	 * @return the schema
	 */
	public String getSchema() {
		return schema;
	}

	/**
	 * @param schema
	 *            the schema to set
	 */
	public void setSchema(final String schema) {
		Validate.notEmpty(schema, "Illegal schema name");
		this.schema = schema;
	}

	@Override
	public Connection getConnection() throws SQLException {
		return switchSchema(super.getConnection());
	}

	@Override
	public Connection getConnection(final String username, final String password) throws SQLException {
		return switchSchema(super.getConnection(username, password));
	}

	private Connection switchSchema(final Connection connection) throws SQLException {
		System.out.println("switching schema");
		final Statement stmt = connection.createStatement();
		try {
			stmt.execute("SET search_path TO " + schema);
		} finally {
			stmt.close();
		}

		return connection;
	}

}
