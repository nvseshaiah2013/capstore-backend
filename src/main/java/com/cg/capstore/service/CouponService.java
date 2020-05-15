package com.cg.capstore.service;

import java.sql.Timestamp;
import java.util.List;

import com.cg.capstore.entities.Coupon;

public interface CouponService {
	   boolean checkCouponCode(String code) throws Exception;
	   boolean addCoupon(Coupon coupon) throws Exception;
	   boolean updateStatus() throws Exception;
	   boolean checkStartDate(Timestamp date) throws Exception;
	   boolean checkEndDate(Timestamp startDate, Timestamp endDate) throws Exception;
	   boolean checkIsActive(String couponName) throws Exception;
	   Coupon getCouponByName(String couponName) throws Exception;
	   boolean updateCoupon(Coupon coupon) throws Exception;
	   boolean deleteCoupon(String couponName) throws Exception;
	   List<Object> listOfCoupons() throws Exception;
}
