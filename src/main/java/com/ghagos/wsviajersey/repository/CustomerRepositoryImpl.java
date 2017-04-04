package com.ghagos.wsviajersey.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.core.Response;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

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
			QueryRunner run = Utils.getQueryRunner(dataSource);
			ResultSetHandler<List<Customer>> h = new BeanListHandler<Customer>(Customer.class);
			customers = run.query(sqlQuery, h);
		} catch (NamingException | SQLException e1) {
			e1.printStackTrace();
		}

		return customers;
	}

	/**
	 * Gets a customer entity given its id.
	 */
	@Override
	public Customer getCustomerById(String id) {
		String sqlQuery = "select * from Customers where CustomerId = '" + id + "'";
		logger.info(sqlQuery);

		Customer customer = new Customer();
		try {
			QueryRunner run = Utils.getQueryRunner(dataSource);
			ResultSetHandler<Customer> h = new BeanHandler<>(Customer.class);
			customer = run.query(sqlQuery, h);

		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customer;
	}

	@Override
	public Response postCustomer(String id) {
		//@TODO
		return null;
	}

	@Override
	public Customer deleteCustomer(String id) {
		Customer customer = getCustomerById(id);
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
