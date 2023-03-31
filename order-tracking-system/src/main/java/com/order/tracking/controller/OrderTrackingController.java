package com.order.tracking.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.order.tracking.exception.OrderTrackingException;
import com.order.tracking.model.AuthRequest;
import com.order.tracking.model.OrderDetails;
import com.order.tracking.service.OrderTrackingService;
import com.order.tracking.util.JwtUtil;

@RestController
public class OrderTrackingController {

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private OrderTrackingService orderTrackingService;

	/**
	 * Endpoint to authenticate using bearer token
	 * 
	 * @param authRequest
	 * @return
	 * @throws OrderTrackingException
	 */
	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws OrderTrackingException {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		} catch (Exception ex) {
			throw new OrderTrackingException("invalid username/password", HttpStatus.UNAUTHORIZED);
		}
		return jwtUtil.generateToken(authRequest.getUserName());
	}

	/**Endpoint to submit order to rabbitmq queue
	 * 
	 * @param orderDetails
	 * @return
	 * @throws OrderTrackingException
	 */
	@PostMapping("/createorder")
	public ResponseEntity<String> createOrderDetails(@RequestBody @Valid OrderDetails orderDetails)
			throws OrderTrackingException {
		orderTrackingService.publishOrderToQueue(orderDetails);

		return new ResponseEntity<>(HttpStatus.OK);

	}

	
	
}
