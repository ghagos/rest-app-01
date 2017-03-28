package com.ghagos.wsviajersey.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.sql.DataSource;

import com.ghagos.wsviajersey.Utils;
import com.ghagos.wsviajersey.model.Customer;

public class CustomerRepositoryImpl implements CustomerRepository {

	@Resource(name = "jdbc/northwind")
	private DataSource dataSource;
	
	private Logger logger = Logger.getLogger("logger");
	
	/**
	 * Get all customers or customers for a given city
	 */
	@Override
	public List<Customer> getCustomers(String country) {
		List<Customer> customers = new ArrayList<>();
		String sqlQuery = "select * from Customers";
		if (country != null) {
			sqlQuery += " where country like '%" + country + "%'";
		}
		logger.info(sqlQuery);
		
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

	@Override
	public List<Customer> getCustomerById(String id) {
		List<Customer> customer = new ArrayList<>();
		String sqlQuery = "select * from Customers where CustomerId = '" + id + "'";
		logger.info(sqlQuery);
		
		try {
			Connection conn = Utils.getDbConn(dataSource);
			Statement stmt = conn.createStatement();
			if (stmt != null) {
				ResultSet rs = stmt.executeQuery(sqlQuery);
				populateCustomers(customer, rs);
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return customer;
	}

	@Override
	public List<Customer> postCustomerById(String id) {
		String sqlQuery = "INSERT INTO `northwind`.`Customers` (`CustomerID`,`CompanyName`,`ContactName`,`"
				+ "ContactTitle`,`Address`,`City`,`Region`,`PostalCode`,`Country`,`Phone`,`Fax`)VALUES  "
				+ "('" + id + "',"
				+ "'Personal Company', 'Getachew Hagos', 'Sales Representative', '1323 E Tuodr St',"
				+ "'Los Angeles', NULL, '91724', 'USA', '626-497-2212', '626-497-2212'  )";
		
		logger.info(sqlQuery);
		int response = 0;
		try {
			Connection conn = Utils.getDbConn(dataSource);
			Statement stmt = conn.createStatement();
			if (stmt != null) {
				response = stmt.executeUpdate(sqlQuery);
				conn.close();
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (response != 0) {
			return getCustomerById(id);
		}
		
		return null;
	}

	@Override
	public List<Customer> deleteCustomerById(String id) {
		List<Customer> customer = getCustomerById(id);
		String sqlQuery = "delete from Customers where CustomerId = '" + id + "'";
		logger.info(sqlQuery);
		
		try {
			Connection conn = Utils.getDbConn(dataSource);
			Statement stmt = conn.createStatement();
			if (stmt != null) {
				stmt.executeUpdate(sqlQuery);
				conn.close();
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}

}
