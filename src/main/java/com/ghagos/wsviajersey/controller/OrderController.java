package com.ghagos.wsviajersey.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ghagos.wsviajersey.model.Order;
import com.ghagos.wsviajersey.repository.OrderRepository;
import com.ghagos.wsviajersey.repository.OrderRepositoryImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("orders")
@Api(value = "Orders")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class OrderController {

	OrderRepository orderRepository = new OrderRepositoryImpl();
	
	@GET
	@ApiOperation(value="Return all orders. Or given country, returns orders shipped to that country")
	public List<Order> getOrders(@QueryParam("shipCountry") String country) {
		return orderRepository.getOrders(country);
	}
	
	@GET
	@Path("{customerId}")
	@ApiOperation(value="Return orders for a given customer")
	public List<Order> getOrderByCustomerId(@PathParam("customerId") String customerId) {
		return orderRepository.getOrderByCustomerId(customerId);
	}
	
	@GET
	@Path("{customerId}/{orderId}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value="Return an order for a given customer and order id")
	public Order getOrderById(@PathParam("customerId") String customerId, @PathParam("orderId") int orderId) {
		return orderRepository.getCustomerOrderById(customerId, orderId);
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value="Creats an order and stores it to database")
	public Response postOrder(Order order) {
		return orderRepository.postOrder(order);
	}
	
	@DELETE
	@Path("{orderId}")
	@ApiOperation(value="Deletes an order given order id")
	public Response deleteOrder(@PathParam("orderId") int orderId) {
		return orderRepository.deleteOrder(orderId);
	}
}
