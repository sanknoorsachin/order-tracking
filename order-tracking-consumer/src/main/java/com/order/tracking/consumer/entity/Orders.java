package com.order.tracking.consumer.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="orders" )
@Entity
public class Orders  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;
	
	@Column(name ="order_id" )
	private String orderId;
	
	@Column(name = "installation_date")
	@Temporal(value=TemporalType.DATE)
	private Date installationDate;
	
	@Column(name = "installation_time")
	private String installationTime;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Customer customers;
	
	@OneToMany(targetEntity =Products.class ,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name ="order_id",referencedColumnName ="order_id")
	private List<Products> product;
	
	
	
	
	
	
	

}
