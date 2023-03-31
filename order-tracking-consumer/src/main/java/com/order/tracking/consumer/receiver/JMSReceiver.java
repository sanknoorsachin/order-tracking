package com.order.tracking.consumer.receiver;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

@Component
public class JMSReceiver implements ChannelAwareMessageListener {

	@Override
	public void onMessage(Message message, com.rabbitmq.client.Channel channel) throws Exception {

		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}

}