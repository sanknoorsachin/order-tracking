package com.order.tracking.consumer.config;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.order.tracking.consumer.receiver.JMSReceiver;

@Configuration
public class RabbitMQConfig {

	@Value("${rabbitmq.exchange.name}")
    private String orderExchangeName;
    
    @Value("${rabbitmq.order.tracking.queue.name}")
    private String orderTrackingQueueName;
     
    @Value("${rabbitmq.order.tracking.routing.key}")
    private String orderTrackingRoutingKey;

    @Bean
    public Queue queue() {
        return  new Queue(orderTrackingQueueName);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(orderExchangeName);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(orderTrackingRoutingKey);
    }

    @Bean
    public MessageConverter messageConverter() {
        return  new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return  template;
    }
    
    @Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter)
	{
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		container.setQueueNames(orderTrackingQueueName);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(JMSReceiver jmsReceiver)
	{
		return new MessageListenerAdapter(jmsReceiver, "receiveMessage");
	}


}