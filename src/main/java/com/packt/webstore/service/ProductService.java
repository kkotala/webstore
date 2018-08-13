package com.packt.webstore.service;

import java.util.List;

import com.packt.webstore.domain.Product;

public interface ProductService {
	void add(Product product);
	List<Product> findAll();
	void remove(String productId);
	void update(Product product);
	void save(Product product);
	Product findOne(String productId);
	Product getProductById(String productId);
	List<Product> getProductsByCategory(String category);
	List<Product> getProductsByManufacturer(String manufacturer);
}
