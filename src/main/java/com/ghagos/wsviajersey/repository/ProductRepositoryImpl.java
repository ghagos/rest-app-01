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
import javax.sql.DataSource;

import com.ghagos.wsviajersey.model.ExpensiveProduct;
import com.ghagos.wsviajersey.model.Product;
import com.ghagos.wsviajersey.utils.Utils;

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
			Connection conn = Utils.getDbConn(dataSource);
			if (conn != null) {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sqlQuery);
				populateProducts(products, rs);
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	private void populateProducts(List<Product> products, ResultSet rs) throws SQLException {
		while (rs.next()) {
			Product product = new Product();
			product.setProductID(rs.getInt("ProductID"));
			product.setProductName(rs.getString("ProductName"));
			product.setSupplierID(rs.getInt("SupplierID"));
			product.setCategoryID(rs.getInt("CategoryID"));
			product.setQuantityPerUnit(rs.getString("QuantityPerUnit"));
			product.setUnitPrice(rs.getBigDecimal("UnitPrice"));
			product.setUnitsInStock(rs.getShort("UnitsInStock"));
			product.setUnitsOnOrder(rs.getShort("UnitsOnOrder"));
			product.setReorderLevel(rs.getShort("ReorderLevel"));
			product.setDiscontinued(rs.getBoolean("Discontinued"));

			products.add(product);
			product = null;
		}
	}

}
