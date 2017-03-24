package com.ghagos.wsviajersey.repository;

import java.util.List;

import javax.ws.rs.core.Response;

import com.ghagos.wsviajersey.model.Order;

public interface OrderRepository {

	/**
	 * Get orders
	 * @see com.ghagos.wsviajersey.repository.OrderRepositoryImpl#getOrders(java.lang.String)
	 */
	List<Order> getOrders(String country);

	
	/**
	 * Returns orders for a given customer.
	 * @see com.ghagos.wsviajersey.repository.OrderRepositoryImpl#getOrderById(java.lang.String)
	 */
	List<Order> getOrderByCustomerId(String customerId);


	/**
	 * Returns orders for a given customer and given order id
	 * @see com.ghagos.wsviajersey.repository.OrderRepositoryImpl#getCustomerOrderById(String, int)
	 */
	Order getCustomerOrderById(String customerId, int orderId);

	
	/**
	 * Creates an order and stores it to database
	 * @see com.ghagos.wsviajersey.repository.OrderRepositoryImpl#postOrder(Order)
	 * @return a response object.
	 */
	Response postOrder(Order order);


	/**
	 * Deletes an order
	 * @param orderId
	 * @return
	 */
	Response deleteOrder(int orderId);

}