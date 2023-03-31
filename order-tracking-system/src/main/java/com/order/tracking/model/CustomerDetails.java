package com.order.tracking.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.order.tracking.OrderTrackingConstants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetails {
	
	@NotNull(message = OrderTrackingConstants.FIRST_NAME_MISSING_MESSAGE)
	private String firstName;
	@NotNull(message = OrderTrackingConstants.LAST_NAME_MISSING_MESSAGE)
	private String lastName;
	private String age;
	private String occupation;
	@NotNull(message = OrderTrackingConstants.ADDRESS_MISSING_MESSAGE)
	private Address address;
	
	

}
