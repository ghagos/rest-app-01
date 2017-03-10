package com.ghagos.wsviajersey.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.ghagos.wsviajersey.model.Customer;
import com.ghagos.wsviajersey.repository.CustomerRepository;
import com.ghagos.wsviajersey.repository.CustomerRepositoryImpl;

import io.swagger.annotations.Api;

@Path("customers")
@Api(value = "customers", description = "List all Northwind customers")
public class CustomerController {
	CustomerRepository customerRepository = new CustomerRepositoryImpl();

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Customer> getCustomers(@QueryParam("city") String city) {
		return customerRepository.getCustomers(city);
	}
}
