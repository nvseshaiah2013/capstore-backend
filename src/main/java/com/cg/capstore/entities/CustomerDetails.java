package com.cg.capstore.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="CUSTOMERS")
public class CustomerDetails implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="PHONE_NO")
	private String phoneNo;
	
	@Column(name="ALT_PHONE_NO")
	private String alternatePhoneNo;
	
	@Column(name="ALT_EMAIL")
	private String alternateEmail;
	
	@Column(name="GENDER")
	@Pattern(regexp="Male|Female|Other")
	private String gender;

	@OneToMany(mappedBy="customer")
	private Set<Order> orders = new HashSet<Order>();
	
	@OneToMany(mappedBy="customer")
	private Set<Address> addresses =new HashSet<Address>();
	
	@OneToMany(mappedBy="customer")
	private Set<CommonFeedback> feedbacks =new HashSet<CommonFeedback>();
	
	@OneToMany(mappedBy="customer")
	private Set<ProductFeedback> productFeedbacks = new HashSet<ProductFeedback>();
	
	@OneToMany(mappedBy="customer")
	private Set<Cart> carts = new HashSet<Cart>();
	
	@OneToMany(mappedBy="customer")
	private Set<Wishlist> wishlists = new HashSet<Wishlist>();
	
	
	public CustomerDetails() {
		
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAlternatePhoneNo() {
		return alternatePhoneNo;
	}

	public void setAlternatePhoneNo(String alternatePhoneNo) {
		this.alternatePhoneNo = alternatePhoneNo;
	}

	public String getAlternateEmail() {
		return alternateEmail;
	}

	public void setAlternateEmail(String alternateEmail) {
		this.alternateEmail = alternateEmail;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	public Set<CommonFeedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(Set<CommonFeedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public Set<ProductFeedback> getProductFeedbacks() {
		return productFeedbacks;
	}

	public void setProductFeedbacks(Set<ProductFeedback> productFeedbacks) {
		this.productFeedbacks = productFeedbacks;
	}

	public Set<Cart> getCarts() {
		return carts;
	}

	public void setCarts(Set<Cart> carts) {
		this.carts = carts;
	}

	public Set<Wishlist> getWishlists() {
		return wishlists;
	}

	public void setWishlists(Set<Wishlist> wishlists) {
		this.wishlists = wishlists;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
}
