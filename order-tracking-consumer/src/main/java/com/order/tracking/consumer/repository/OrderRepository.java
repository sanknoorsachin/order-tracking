package com.order.tracking.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.tracking.consumer.entity.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {
	
	Orders findByOrderId(String orderId);

}
