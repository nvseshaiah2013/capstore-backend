package com.cg.capstore.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="COUPONS")
public class Coupon implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="COUPON_CODE")
	private String couponCode;
	
	@Column(name="END_DATE")
	private Timestamp couponEndDate;
	
	@Column(name="START_DATE")
	private Timestamp couponStartDate;
	
	@Column(name="COUPON_AMOUNT")
	private int couponAmount;
	
	@Column(name="MIN_AMOUNT")
	private int minOrderAmount;
	
	@Column(name="INFO")
	private String couponDesc;
	
	public Coupon() {
		
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public Timestamp getCouponEndDate() {
		return couponEndDate;
	}

	public void setCouponEndDate(Timestamp couponEndDate) {
		this.couponEndDate = couponEndDate;
	}

	public Timestamp getCouponStartDate() {
		return couponStartDate;
	}

	public void setCouponStartDate(Timestamp couponStartDate) {
		this.couponStartDate = couponStartDate;
	}

	public int getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(int couponAmount) {
		this.couponAmount = couponAmount;
	}

	public int getMinOrderAmount() {
		return minOrderAmount;
	}

	public void setMinOrderAmount(int minOrderAmount) {
		this.minOrderAmount = minOrderAmount;
	}

	public String getCouponDesc() {
		return couponDesc;
	}

	public void setCouponDesc(String couponDesc) {
		this.couponDesc = couponDesc;
	}
	
	
}
