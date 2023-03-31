package com.order.tracking.consumer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RabbitMQMessage {
	
	private String orderId;
    private String orderDate;
    private OrderDetails orderDetails;

}
