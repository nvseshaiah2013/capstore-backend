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
@Table(name="MERCHANTS")
public class MerchantDetails implements Serializable{
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
	
	@Column(name="IS_THIRD_PARTY")
	private boolean isThirdParty;
	
	@Column(name="IS_DELETED")
	private boolean isDeleted;
	
	@Column(name="GENDER")
	@Pattern(regexp="Male|Female|Other")
	private String gender;
	
	@Column(name="RATING")
	private int rating;
	
	@OneToMany
	private Set<Order> orders = new HashSet<Order>();
	
	@OneToMany(mappedBy="merchant")
	private Set<Address> addresses = new HashSet<Address>();

	
	@OneToMany(mappedBy="merchant")
	private Set<CommonFeedback> feedbacks = new HashSet<CommonFeedback>();
	
	@OneToMany(mappedBy="merchant")
	private Set<Product> products = new HashSet<Product>();
	
	@OneToMany(mappedBy="merchant")
	private Set<Invitation> invites = new HashSet<Invitation>();
	

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

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public boolean isThirdParty() {
		return isThirdParty;
	}

	public void setThirdParty(boolean isThirdParty) {
		this.isThirdParty = isThirdParty;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Set<Invitation> getInvites() {
		return invites;
	}

	public void setInvites(Set<Invitation> invites) {
		this.invites = invites;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	
	
}
