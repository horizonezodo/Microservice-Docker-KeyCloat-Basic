package com.horizon.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.horizon.productservice.module.Product;

public interface ProductRepository extends MongoRepository<Product, String>{

}
