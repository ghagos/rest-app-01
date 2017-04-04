package com.ghagos.wsviajersey.repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.ghagos.wsviajersey.Utils;
import com.ghagos.wsviajersey.model.ExpensiveProduct;
import com.ghagos.wsviajersey.model.Product;

public class ProductRepositoryImpl implements ProductRepository {

	@Resource(name = "jdbc/northwind")
	private DataSource dataSource;
	
	private Logger logger = Logger.getLogger("logger");

	@Override
	public List<Product> getProducts(String discountinued) {
		List<Product> products = new ArrayList<>();

		String sqlQuery = "select * from Products";
		if (discountinued != null) {
			if (discountinued.equalsIgnoreCase("yes") || discountinued.equalsIgnoreCase("true")) {
				sqlQuery += " where Discontinued = 1";
			}

			if (discountinued.equalsIgnoreCase("no") || discountinued.equalsIgnoreCase("false")) {
				sqlQuery += " where Discontinued = 0";
			}
		}
		logger.info(sqlQuery);
		
		try {
			QueryRunner run = Utils.getQueryRunner(dataSource);
			ResultSetHandler<List<Product>> h = new BeanListHandler<>(Product.class);
			products = run.query(sqlQuery, h);
		} catch (NamingException | SQLException e1) {
			e1.printStackTrace();
		}
		
		return products;
	}
	
	@Override
	public List<ExpensiveProduct> getTenMostExpensive() {
		List<ExpensiveProduct>  products = new ArrayList<>();
		
		String sqlQuery = "SELECT DISTINCT  ProductName AS prodName, UnitPrice "
				+ "FROM Products AS a " + "WHERE 10 >= (SELECT COUNT(DISTINCT UnitPrice) FROM Products AS b "
				+ "WHERE b.UnitPrice >= a.UnitPrice) ORDER BY UnitPrice DESC;";
		
		logger.info(sqlQuery);
		
		try {
			Connection conn = Utils.getDbConn(dataSource);
			if (conn != null) {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sqlQuery);
				while (rs.next()) {
					String key = rs.getString("prodName");
					BigDecimal value = rs.getBigDecimal("UnitPrice");
					Map<String, BigDecimal> map = new HashMap<>();
					map.put(key, value);
					products.add(new ExpensiveProduct(map));
				}
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return products;
	}
}
