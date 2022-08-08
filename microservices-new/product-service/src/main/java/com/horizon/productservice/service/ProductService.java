package com.horizon.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.horizon.productservice.dto.ProductRequest;
import com.horizon.productservice.dto.ProductResponse;
import com.horizon.productservice.module.Product;
import com.horizon.productservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
	
	
	private final ProductRepository productRepository;
	

	public void createProduct(ProductRequest request) {
		Product product = Product.builder()
				.name(request.getName())
				.mota(request.getMota())
				.price(request.getPrice())
				.build();
		
		productRepository.save(product);
		log.info("Product {} is saved", product.getId());
	}


	public List<Object> getAllProducts() {
		List<Product> products = productRepository.findAll();
		
		return products.stream().map(this::mapToProductResponse).toList();
		
	}


	private Object mapToProductResponse(Product product) {
		return ProductResponse.builder()
				.id(product.getId())
				.name(product.getName())
				.mota(product.getMota())
				.price(product.getPrice())
				.build();
	}
}
