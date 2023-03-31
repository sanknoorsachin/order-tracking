package com.order.tracking.consumer.listener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.mail.MessagingException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.tracking.consumer.entity.Address;
import com.order.tracking.consumer.entity.Customer;
import com.order.tracking.consumer.entity.Orders;
import com.order.tracking.consumer.entity.Products;
import com.order.tracking.consumer.model.RabbitMQMessage;
import com.order.tracking.consumer.repository.OrderRepository;
import com.order.tracking.consumer.service.EmailSenderService;



@Component
public class MessageListener {

	@Value("${order.email.to}")
    private String toMailAddress;
	
	@Autowired
	private EmailSenderService emailSenderService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	/**
	 * Method to read message from the queue and send and email and save the record in database
	 * 
	 * @param message
	 * @throws JsonProcessingException
	 * @throws MessagingException
	 */
	@RabbitListener(queues = "order_tracking")
	public void messageListener(RabbitMQMessage message) throws JsonProcessingException, MessagingException {
		ObjectMapper mapper = new ObjectMapper();
		String data = mapper.writeValueAsString(message);
		String subject="Order Processing for orderId"+'-'+message.getOrderId();
		triggerMailAndSaveRecord(data,subject,message);
		
	}

	private void triggerMailAndSaveRecord (String mailBody,String subject,RabbitMQMessage message) throws MessagingException {
		emailSenderService.sendEmail(toMailAddress, mailBody, subject);
		saveRecord(message);

	}
	
	private void saveRecord(RabbitMQMessage message) {
		Orders order= new Orders();
		
		order.setOrderId(message.getOrderId());
		Date date = getDate(message);
		order.setInstallationDate(date);
		
		order.setInstallationTime(message.getOrderDetails().getTimeSlots());
		
		Customer cust = new Customer();
		cust.setFirstName(message.getOrderDetails().getCustomerDetails().getFirstName());
		cust.setLastName(message.getOrderDetails().getCustomerDetails().getLastName());
		cust.setAge(message.getOrderDetails().getCustomerDetails().getAge());
		cust.setOccupation(message.getOrderDetails().getCustomerDetails().getOccupation());
		
		Address address = new Address();
		address.setAreaCode(message.getOrderDetails().getCustomerDetails().getAddress().getAreaCode());
		address.setCity(message.getOrderDetails().getCustomerDetails().getAddress().getCity());
		address.setHouseNumber(message.getOrderDetails().getCustomerDetails().getAddress().getHouseNumber());
		address.setStreetName(message.getOrderDetails().getCustomerDetails().getAddress().getStreetName());
		cust.setAddress(address);
		order.setCustomers(cust);
			
		List<Products> products  = new ArrayList<>();
		Products product = new Products();
		
		message.getOrderDetails().getProducts().stream().forEach(a->{
			product.setProductId(a.getProductId());
			product.setProductName(a.getProductName());
			product.setPlan(a.getPlan());
			products.add(product);
			
		});
		
		order.setProduct(products);
		
		orderRepository.save(order);
	}

	private Date getDate(RabbitMQMessage message){
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

		String dateInString = message.getOrderDetails().getInstallationDate();
		Date date = null;
		try {
			date = formatter.parse(dateInString);
		} catch (ParseException e) {
		
		}
		return date;
	
	}
	
}
