package com.packt.webstore.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.packt.webstore.domain.Product;
import com.packt.webstore.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void add(Product product) {
		productRepository.save(product);
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public void remove(String productId) {
		productRepository.deleteById(productId);
	}

	@Override
	public void update(Product product) {
		productRepository.save(product);
	}

	@Override
	public void save(Product product) {
		productRepository.save(product);
	}

	@Override
	public Product findOne(String productId) {
		return productRepository.getOne(productId);
	}

	public Product getProductById(String productId) {
		Product productById = null;
		for (Product product : productRepository.findAll()) {
			if (product != null && product.getProductId() != null && product.getProductId().equals(productId)) {
				productById = product;
				break;
			}
		}
		if (productById == null) {
			throw new IllegalArgumentException("Brak produktu o wskazanym id:" + productId);
		}
		return productById;
	}

	public List<Product> getProductsByCategory(String category) {
		List<Product> productsByCategory = new ArrayList<Product>();
		for (Product product : productRepository.findAll()) {
			if (category.equalsIgnoreCase(product.getCategory())) {
				productsByCategory.add(product);
			}
		}
		return productsByCategory;
	}

	public List<Product> getProductsByManufacturer(String manufacturer) {
		List<Product> productsByManufacturer = new ArrayList<Product>();
		for (Product product : productRepository.findAll()) {
			if (manufacturer.equalsIgnoreCase(product.getManufacturer())) {
				productsByManufacturer.add(product);
			}
		}
		return productsByManufacturer;
	}

	public List<Product> getProductsByPriceFilter(String lowPrice, String highPrice) {
		List<Product> productsByPriceFilter = new ArrayList<Product>();
		BigDecimal low = new BigDecimal(lowPrice);
		BigDecimal high = new BigDecimal(highPrice);	
		for (Product product : productRepository.findAll()) {
			if (product.getUnitPrice().compareTo(low) > 0) {
				if (product.getUnitPrice().compareTo(high) < 0) {
					productsByPriceFilter.add(product);
				}
			}
		}
		return productsByPriceFilter;
	}

	
}


