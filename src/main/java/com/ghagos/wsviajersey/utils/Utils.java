package com.ghagos.wsviajersey.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

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
			//e.printStackTrace();
		}
	}

}
