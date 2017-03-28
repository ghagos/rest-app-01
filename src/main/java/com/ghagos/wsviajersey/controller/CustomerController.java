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

import com.ghagos.wsviajersey.model.Customer;
import com.ghagos.wsviajersey.repository.CustomerRepository;
import com.ghagos.wsviajersey.repository.CustomerRepositoryImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("customers")
@Api(value = "Customers")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class CustomerController {
	CustomerRepository customerRepository = new CustomerRepositoryImpl();

	@GET
	@ApiOperation(value="List all customers. Or list customers for a country, given country as a query parameter.")
	public List<Customer> getCustomers(@QueryParam("country") String country) {
		return customerRepository.getCustomers(country);
	}
	
	@GET
	@Path("{id}")
	@ApiOperation(value="List a customer for a given customer id.")
	public List<Customer> getCustomerById(@PathParam("id") String id) {
		return customerRepository.getCustomerById(id);
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("{id}")
	@ApiOperation(value="Create customer record WIP.")
	public List<Customer> postCustomerById(@PathParam("id") String id) {
		return customerRepository.postCustomerById(id);
	}
	
	@DELETE
	@Path("{id}")
	@ApiOperation(value="Removes a customer given its customer id")
	public List<Customer> deleteCustomerById(@PathParam("id") String id) {
		return customerRepository.deleteCustomerById(id);
	}
}
