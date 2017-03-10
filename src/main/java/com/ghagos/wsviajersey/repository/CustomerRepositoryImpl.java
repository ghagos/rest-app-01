package com.ghagos.wsviajersey.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import com.ghagos.wsviajersey.model.Customer;
import com.ghagos.wsviajersey.utils.Utils;

public class CustomerRepositoryImpl implements CustomerRepository {

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
			Connection conn = Utils.getDbConn(dataSource);
			Statement stmt = conn.createStatement();
			if (stmt != null) {
				ResultSet rs = stmt.executeQuery(sqlQuery);
				populateCustomers(customers, rs);
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return customers;
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
