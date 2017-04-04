package com.ghagos.wsviajersey.controller;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ghagos.wsviajersey.model.Customer;
import com.ghagos.wsviajersey.repository.CustomerRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("customers")
@Api(value = "Customers")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Component
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	@GET
	@ApiOperation(value = "List all customers. Or list customers for a country, given country as a query parameter.")
	public List<Customer> getCustomers(@QueryParam("country") String country) {
		return customerRepository.getCustomers(country);
	}

	@GET
	@Path("{id}")
	@ApiOperation(value = "List a customer for a given customer id.")
	public Response getCustomerById(@PathParam("id") String id) {
		Customer customer = customerRepository.getCustomerById(id);
		ResponseBuilder builder = Response.ok(customer);
		return builder.build();
	}

	@DELETE
	@Path("{id}")
	@ApiOperation(value = "Removes a customer given its customer id")
	public Response deleteCustomerById(@PathParam("id") String id) {
		Customer customer = customerRepository.deleteCustomer(id);
		ResponseBuilder builder = Response.ok(customer);

		return builder.build();

	}
}
