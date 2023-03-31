package com.order.tracking.consumer.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="products" )
@Entity
public class Products  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "product_id")
	private String productId;
	@Column(name = "product_name")
	private String productName;
	@Column(name = "plan")
	private String plan;
	
	
	
	 

}
