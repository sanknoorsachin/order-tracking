package com.order.tracking.consumer.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetails {
	
	private CustomerDetails customerDetails;
	private List<Products> products;
	private String installationDate;
	private String timeSlots;
	
  
}
