package com.order.tracking.consumer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="address" )
@Entity
public class Address {
	
	@Id
	@GeneratedValue
	private int  id;
	@Column(name = "street_name")
	private String streetName;
	@Column(name = "house_number")
	private String houseNumber;
	@Column(name = "area")
	private String areaCode;
	@Column(name = "city")
	private String city;
	

}
