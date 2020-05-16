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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ADDRESSES")
public class Address implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ADDRESS_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int addressId;
	
	@Column(name="ADDRESS_LINE_ONE")
	private String addressLineOne;
	
	@Column(name="ADDRESS_LINE_TWO")
	private String addressLineTwo;
	
	@Column(name="ADDRESS_DISTRICT")
	private String district;
	
	@Column(name="ADDRESS_STATE")
	private String state;
	
	@Column(name="ADDRESS_LANDMARK")
	private String landmark;
	
	@Column(name="IS_DELETED")
	private boolean isDeleted;
	
	@ManyToOne
	@JoinColumn(name="USERNAME")
	private User user;
	
	
	public Address() {
		
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getAddressLineOne() {
		return addressLineOne;
	}

	public void setAddressLineOne(String addressLineOne) {
		this.addressLineOne = addressLineOne;
	}

	public String getAddressLineTwo() {
		return addressLineTwo;
	}

	public void setAddressLineTwo(String addressLineTwo) {
		this.addressLineTwo = addressLineTwo;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	@JsonIgnore
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	
	
}
