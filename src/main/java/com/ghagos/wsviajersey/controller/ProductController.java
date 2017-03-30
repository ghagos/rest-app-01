package com.ghagos.wsviajersey.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ghagos.wsviajersey.model.ExpensiveProduct;
import com.ghagos.wsviajersey.model.Product;
import com.ghagos.wsviajersey.repository.ProductRepository;

import io.swagger.annotations.Api;

@Path("products")
@Api(value = "Products")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Component
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;

	@GET
	public List<Product> getProducts(@QueryParam("discontinued") String discontinued) {
		return productRepository.getProducts(discontinued);
	}
	
	@GET
	@Path("/expensive")
	public List<ExpensiveProduct> getTenMostExpensive() {
		return productRepository.getTenMostExpensive();
	}
}
