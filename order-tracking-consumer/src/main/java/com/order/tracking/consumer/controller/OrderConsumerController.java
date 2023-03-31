package com.order.tracking.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.order.tracking.consumer.util.JwtUtil;

import com.order.tracking.consumer.exception.OrderConsumerException;
import com.order.tracking.consumer.model.AuthRequest;
import com.order.tracking.consumer.model.OrderDetails;
import com.order.tracking.consumer.service.OrderService;

@RestController
public class OrderConsumerController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private OrderService orderService;

	/**Endpoint to authenticate  using bearer token
	 * 
	 * @param authRequest
	 * @return
	 * @throws OrderConsumerException
	 */
	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws OrderConsumerException {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		} catch (Exception ex) {
			throw new OrderConsumerException("invalid username/password", HttpStatus.UNAUTHORIZED);
		}
		return jwtUtil.generateToken(authRequest.getUserName());
	}

	/**Endpoint to update the order details
	 * 
	 * @param orderId
	 * @param orderDetails
	 * @return
	 */
	@PutMapping("/updateorder/{orderId}")
	public ResponseEntity<String> updateOrderDetails(@PathVariable String orderId,@RequestBody  OrderDetails orderDetails ){
		orderService.updateOrderDetails(orderId, orderDetails);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
		

	}
}
