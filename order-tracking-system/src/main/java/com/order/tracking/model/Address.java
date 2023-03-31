package com.order.tracking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	
	private String streetName;
	private String houseNumber;
	private String areaCode;
	private String city;
	

}
