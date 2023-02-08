package com.adat.serviciowebrest.service;

import java.util.Optional;
import java.util.Set;
import com.adat.serviciowebrest.domain.Product;

public interface ProductService {
	 Set<Product> findAll();
	 Set<Product> findByCategory(String category);
	 Optional<Product> findById(long id);
	 Product addProduct(Product product);
	 Product modifyProduct(long id, Product newProduct);
	 void deleteProduct(long id);
	}