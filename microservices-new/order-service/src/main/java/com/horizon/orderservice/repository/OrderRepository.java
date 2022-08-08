package com.horizon.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.horizon.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
