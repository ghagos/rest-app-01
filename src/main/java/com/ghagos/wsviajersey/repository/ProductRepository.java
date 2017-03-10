package com.ghagos.wsviajersey.repository;

import java.util.List;

import com.ghagos.wsviajersey.model.Product;

public interface ProductRepository {

	/**
	 * @see com.ghagos.wsviajersey.repository.ProductRepositoryImpl#getProducts(java.lang.String)
	 * @param discountinued
	 * @return
	 */
	List<Product> getProducts(String discountinued);

}