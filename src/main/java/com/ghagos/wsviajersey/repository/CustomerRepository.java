package com.ghagos.wsviajersey.repository;

import java.util.List;

import javax.ws.rs.core.Response;

import com.ghagos.wsviajersey.model.Customer;

public interface CustomerRepository {
	
	/**
	 * @see com.ghagos.wsviajersey.repository.CustomerRepositoryImpl#getCustomers(String)
	 * @param city
	 * @return list of all customers, or list of customers for a given city
	 */
	public List<Customer> getCustomers(String country);

	
	/**
	 * @see com.ghagos.wsviajersey.repository.CustomerRepositoryImpl#getCustomerById(String)
	 * @param id
	 * @return a customer for a given CustomerId.
	 */
	public Customer getCustomerById(String id);

	/**
	 * @see com.ghagos.wsviajersey.repository.CustomerRepositoryImpl#postCustomer(String)
	 * @param CustomerId
	 * @return a customer just posted to system.
	 */
	public Response postCustomer(String id);


	/**
	 * @see com.ghagos.wsviajersey.repository.CustomerRepositoryImpl#deleteCustomer(String)
	 * @param CustomerId
	 * @return a customer just deleted from the system.
	 */
	public Customer deleteCustomer(String id);
	
}