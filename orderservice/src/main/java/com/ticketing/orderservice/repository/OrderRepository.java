package com.ticketing.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticketing.orderservice.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
