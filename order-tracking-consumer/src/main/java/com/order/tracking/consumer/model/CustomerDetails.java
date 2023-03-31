package com.order.tracking.consumer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetails {

	private String firstName;
	private String lastName;
	private String age;
	private String occupation;
	private Address address;

}
