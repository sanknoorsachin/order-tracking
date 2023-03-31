package com.order.tracking.consumer.service;

import org.springframework.stereotype.Service;

import com.order.tracking.consumer.entity.Orders;
import com.order.tracking.consumer.model.OrderDetails;
import com.order.tracking.consumer.repository.OrderRepository;

@Service
public class OrderService {
	
	private OrderRepository OrderRepository;
	
	
	public void updateOrderDetails(String orderId,OrderDetails orderDetails) {
		Orders order=OrderRepository.findByOrderId(orderId);
		if(order!=null) {		
			orderDetails.setInstallationDate(orderDetails.getInstallationDate());
			orderDetails.setTimeSlots(orderDetails.getTimeSlots());
			OrderRepository.save(order);
			
		}
		
		
	}

}
