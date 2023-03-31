package com.order.tracking;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.order.tracking.entity.User;
import com.order.tracking.repository.UserRepository;



@SpringBootApplication
public class OrderTrackingSystemApplication {
	  @Autowired
	    private UserRepository repository;

	   
	public static void main(String[] args) {
		SpringApplication.run(OrderTrackingSystemApplication.class, args);
	}
	
	 @PostConstruct
	    public void initUsers() {
	        List<User> users = Stream.of(
	                new User(101, "admin", "password", "admin@gmail.com")
	        ).collect(Collectors.toList());
	        repository.saveAll(users);
	    }


}
