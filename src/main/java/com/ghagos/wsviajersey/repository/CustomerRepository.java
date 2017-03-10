package com.ghagos.wsviajersey.repository;

import java.util.List;

import com.ghagos.wsviajersey.model.Customer;

public interface CustomerRepository {
	
	/**
	 * @see com.ghagos.wsviajersey.repository.CustomerRepositoryImpl#getCustomers(String)
	 * @param city
	 * @return
	 */
	public List<Customer> getCustomers(String city);
	
}