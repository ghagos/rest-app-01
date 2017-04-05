package com.ghagos.wsviajersey.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ghagos.wsviajersey.model.Order;
import com.ghagos.wsviajersey.repository.OrderRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("orders")
@Api(value = "Orders")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Component
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;
	
	@Context
	private UriInfo uriInfo;
	
	@GET
	@ApiOperation(value="Return all orders. Or given country, returns orders shipped to that country")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Order> getOrders(@QueryParam("shipCountry") String country) {
		return orderRepository.getOrders(country);
	}
	
	@GET
	@Path("{customerId}")
	@ApiOperation(value="Return orders for a given customer")
	@Produces(MediaType.APPLICATION_XML)
	public List<Order> getOrderByCustomerId(@PathParam("customerId") String customerId) {
		return orderRepository.getOrderByCustomerId(customerId);
	}
	
	@GET
	@Path("{customerId}/{orderId}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value="Return an order for a given customer and order id")
	public Response getOrderById(@PathParam("customerId") String customerId, @PathParam("orderId") int orderId) {
		Order order = orderRepository.getCustomerOrderById(customerId, orderId);
		CacheControl cc = new CacheControl();
		cc.setMaxAge(300); // cache for 5 minutes.
		cc.setPrivate(true);
		
		ResponseBuilder builder = Response.ok(order);
		builder.cacheControl(cc);
		
		return builder.build();
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value="Creats an order and stores it to database")
	public Response postOrder(Order order) {
		return orderRepository.postOrder(order, uriInfo);
	}
	
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value="Updates order")
	public Response putOrder(Order order) {
		return orderRepository.putOrder(order, uriInfo);
	}
	
	@DELETE
	@Path("{orderId}")
	@ApiOperation(value="Deletes an order given order id")
	public Response deleteOrder(@PathParam("orderId") int orderId) {
		return orderRepository.deleteOrder(orderId);
	}
}
