package com.ghagos.wsviajersey.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ghagos.wsviajersey.model.Customer;

public class CustomerRepositoryImpl implements CustomerRepository {

	private String jndiname = "jdbc/northwind";

	@Resource(name = "jdbc/northwind")
	private DataSource dataSource;
	
	/**
	 * Get all customers or customers for a given city
	 */
	@Override
	public List<Customer> getCustomers(String city) {
		List<Customer> customers = new ArrayList<>();
		String sqlQuery = "select * from Customers";
		if (city != null) {
			sqlQuery += " where city like '%" + city + "%'";
		}
		try {
			Connection conn = getDbConn();
			Statement stmt = conn.createStatement();
			if (stmt != null) {
				System.out.println(sqlQuery);
				ResultSet rs = stmt.executeQuery(sqlQuery);
				populateCustomers(customers, rs);
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return customers;
	}
	
	private Connection getDbConn() {
		try {
			Context ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:comp/env/" + jndiname);
			return dataSource.getConnection();
		} catch (NamingException e) {
			throw new IllegalStateException(jndiname + " is missing in JNDI!", e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private void populateCustomers(List<Customer> customers, ResultSet rs) throws SQLException {
		while (rs.next()) {
			Customer customer = new Customer();
			customer.setCustomerId(rs.getString("CustomerId"));
			customer.setCompanyName(rs.getString("CompanyName"));
			customer.setContactName(rs.getString("ContactName"));
			customer.setContactTitle(rs.getString("ContactTitle"));
			customer.setAddress(rs.getString("Address"));
			customer.setCity(rs.getString("City"));
			customer.setRegion(rs.getString("Region"));
			customer.setPostalCode(rs.getString("PostalCode"));
			customer.setCountry(rs.getString("Country"));
			customer.setPhone(rs.getString("Phone"));
			customer.setFax(rs.getString("Fax"));
			customers.add(customer);
			customer = null;
		}
	}

}
