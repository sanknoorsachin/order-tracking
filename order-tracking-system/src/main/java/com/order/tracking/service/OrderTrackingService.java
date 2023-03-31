package com.order.tracking.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.order.tracking.exception.OrderTrackingException;
import com.order.tracking.model.OrderDetails;
import com.order.tracking.model.RabbitMQMessage;

@Service
public class OrderTrackingService {
	@Autowired
	private RabbitTemplate template;

	@Value("${rabbitmq.exchange.name}")
	private String orderExchangeName;

	@Value("${rabbitmq.order.tracking.routing.key}")
	private String orderTrackingRoutingKey;


	/**Method to publish order to the queue
	 * 
	 * @param orderDetails
	 * @throws OrderTrackingException
	 */
	public void publishOrderToQueue(OrderDetails orderDetails) throws OrderTrackingException {
		RabbitMQMessage message = new RabbitMQMessage();
		message.setOrderId(UUID.randomUUID().toString());
		message.setOrderDate(LocalDate.now().toString());
		message.setOrderDetails(orderDetails);
		try {
			template.convertAndSend(orderExchangeName, orderTrackingRoutingKey, message);
		} catch (Exception ex) {
			throw new OrderTrackingException("Excpetion while publishing order details to queue",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
