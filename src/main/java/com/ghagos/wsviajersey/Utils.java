package com.ghagos.wsviajersey;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

public class Utils {

	private static String jndiname = "jdbc/northwind";

	public static Connection getDbConn(DataSource ds) {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/" + jndiname);
			return ds.getConnection();
		} catch (NamingException e) {
			throw new IllegalStateException(jndiname + " is missing in JNDI!", e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new IllegalStateException("SQLException", e);
			// e.printStackTrace();
		}
	}

	public static QueryRunner getQueryRunner(DataSource dataSource) throws NamingException {
		Context ctx = new InitialContext();
		dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/northwind");

		return new QueryRunner(dataSource);
	}

}
