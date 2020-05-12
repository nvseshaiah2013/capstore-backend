package com.cg.capstore.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

@Entity
@Table(name="CART")
public class Cart  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    
	@ManyToOne
	@JoinColumn(name="USERNAME")
	private CustomerDetails customer; 
    
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	private Product product;
	
	@Column(name="QUANT")
	private int quantity;
	
	@Column(name="MIN_ORD_AMT")
	private int minOrderAmount;
	
	
	public Cart() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CustomerDetails getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDetails customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getMinOrderAmount() {
		return minOrderAmount;
	}

	public void setMinOrderAmount(int minOrderAmount) {
		this.minOrderAmount = minOrderAmount;
	}
	
	

}
