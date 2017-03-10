package com.ghagos.wsviajersey.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.ghagos.wsviajersey.model.Product;
import com.ghagos.wsviajersey.repository.ProductRepository;
import com.ghagos.wsviajersey.repository.ProductRepositoryImpl;

import io.swagger.annotations.Api;

@Path("products")
@Api(value = "products", description = "List of Northwind Products")
public class ProductController {
	
	ProductRepository productRepository = new ProductRepositoryImpl();

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Product> getProducts(@QueryParam("discontinued") String discontinued) {
		return productRepository.getProducts(discontinued);
	}
}