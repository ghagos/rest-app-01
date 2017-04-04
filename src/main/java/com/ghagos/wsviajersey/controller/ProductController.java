package com.ghagos.wsviajersey.controller;

import java.util.List;

import javax.ws.rs.DefaultValue;
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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Contact;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition(
        info = @Info(
                description = "Exposes Northwind database as a service",
                version = "V1",
                title = "Northwind API",
                contact = @Contact(
                   name = "Getachew Hagos", 
                   email = "ghagos@gmail.com", 
                   url = "https://github.com/ghagos/rest-app-01"
                )
        ),
        consumes = {"application/json", "application/xml"},
        produces = {"application/json", "application/xml"},
        schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS}
)
@Path("products")
@Api(value = "Products")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Component
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;

	@GET
	@ApiOperation(value="List all products or discontinued products or products still in production.")
	public List<Product> getProducts(@DefaultValue("false") @QueryParam("discontinued") String discontinued) {
		return productRepository.getProducts(discontinued);
	}
	
	@GET
	@Path("/expensive")
	@ApiOperation(value="List top ten expensive products.")
	public List<ExpensiveProduct> getTenMostExpensive() {
		return productRepository.getTenMostExpensive();
	}
}
