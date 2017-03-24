package com.ghagos.wsviajersey.repository;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.ghagos.wsviajersey.model.Order;
import com.ghagos.wsviajersey.utils.Utils;

public class OrderRepositoryImpl implements OrderRepository {

	@Resource(name = "jdbc/northwind")
	private DataSource dataSource;
	
	@Context
	private UriInfo uriInfo;
	
	private Logger logger = Logger.getLogger("logger");

	@Override
	public List<Order> getOrders(String country) {
		List<Order> orders = new ArrayList<>();

		String sqlQuery = "select * from Orders";
		if (country != null && country.length() != 0) {
			sqlQuery += " where ShipCountry like '%" + country + "%'";
		}
		logger.info(sqlQuery);

		try {
			QueryRunner run = Utils.getQueryRunner(dataSource);
			ResultSetHandler<List<Order>> h = new BeanListHandler<Order>(Order.class);
			orders = run.query(sqlQuery, h);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return orders;
	}

	@Override
	public List<Order> getOrderByCustomerId(String customerId) {
		List<Order> orders = new ArrayList<>();
		
		String sqlQuery = "select * from Orders where CustomerId = '" + customerId + "'";
		logger.info(sqlQuery);
		
		try {
			QueryRunner run = Utils.getQueryRunner(dataSource);
			ResultSetHandler<List<Order>> h = new BeanListHandler<Order>(Order.class);
			orders = run.query(sqlQuery, h);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orders;
	}
	
	@Override
	public Order getCustomerOrderById(String customerId, int orderId) {
		Order order = new Order();
		String sqlQuery = "select * from Orders where CustomerId = '" + customerId + "' AND OrderId = " + orderId;
		logger.info(sqlQuery);
		
		try {
			QueryRunner run = Utils.getQueryRunner(dataSource);
			ResultSetHandler<Order> h = new BeanHandler<>(Order.class);
			order = run.query(sqlQuery, h);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return order;
	}

	@Override
	public Response postOrder(Order order) {
		String sqlQuery = "INSERT INTO `Orders` "
				//"(`OrderID`,`CustomerID`,`EmployeeID`,`OrderDate`,`RequiredDate`,`ShippedDate`,
				//`ShipVia`,`Freight`,`ShipName`,`ShipAddress`,`ShipCity`,`ShipRegion`,`Ship PostalCode`,`ShipCountry`) "
				+ "VALUES ("
				+ order.getOrderId() + ","
				+ "'" + order.getCustomerId() + "',"
				+ order.getEmployeeId() + ","
				+ "'" + order.getOrderDate() + "',"
				+ "'" + order.getRequiredDate() + "',"
				+ "'" + order.getShippedDate() + "',"
				+ order.getShipVia() + ","
				+ order.getFreight() + ","
				+ "'" + order.getShipName() + "',"
				+ "'" + order.getShipAddress() + "',"
				+ "'" + order.getShipCity() + "',"
				+ "'" + order.getShipRegion() + "',"
				+ "'" + order.getShipPostalCode() + "',"
				+ "'" + order.getShipCountry() + "')";
		logger.info(sqlQuery);
		
		try {
			QueryRunner run = Utils.getQueryRunner(dataSource);
			ResultSetHandler<Order> h = new BeanHandler<>(Order.class);
			order = run.insert(sqlQuery, h);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		URI createdUri = uriInfo.getAbsolutePath();
		return Response.created(createdUri).entity(order).build();
		
	}

	@Override
	public Response deleteOrder(int orderId) {
		String sqlQuery = "delete from Orders where orderId = " + orderId;
		logger.info(sqlQuery);
		
		try {
			QueryRunner run = Utils.getQueryRunner(dataSource);
			run.update(sqlQuery);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Response.ok().build();
	}
}
