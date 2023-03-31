package com.order.tracking.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.order.tracking.OrderTrackingConstants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetails {
	
	@Valid
	private CustomerDetails customerDetails;
	@NotEmpty(message=OrderTrackingConstants.PRODUCT_MISSING_MESSAGE)
	private List<Products> products;
	@NotNull(message = OrderTrackingConstants.INSTALLATION_DATE_MISSING_MESSAGE)
	private String installationDate;
	@NotNull(message = OrderTrackingConstants.INSTALLATION_TIME_MISSING_MESSAGE)
	private String timeSlots;
	
  
}
