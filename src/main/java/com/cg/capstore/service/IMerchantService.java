package com.cg.capstore.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.cg.capstore.entities.Coupon;
import com.cg.capstore.entities.Invitation;
import com.cg.capstore.entities.MerchantDetails;
import com.cg.capstore.entities.Order;
import com.cg.capstore.entities.Product;

public interface IMerchantService {
	
	List<MerchantDetails> getMerchants() throws Exception;

	MerchantDetails getMerchantInfo(String username);

	Set<Order> getMerchantOrders(String username);

	Order acceptMerchantOrder(long orderId, String status);


	void activateMerchant(String username) throws Exception;

	void deActivateMerchant(String username) throws Exception;

	MerchantDetails findMerchantByUsername(String username) throws Exception;


	Set<Product> getMerchantProducts(String username);

	Set<Invitation> getInvites(String username) throws Exception;
	
	public void activateProduct(int id,String username) throws Exception;

	void deActivateProduct(int id,String username) throws Exception;

	Product findProductById(int id) throws Exception;
	
	boolean checkCouponCode(String code) throws Exception;
	   boolean addCoupon(Coupon coupon,String username) throws Exception;
	   boolean updateStatus() throws Exception;
	   boolean checkStartDate(Timestamp date) throws Exception;
	   boolean checkEndDate(Timestamp startDate, Timestamp endDate) throws Exception;
	   boolean checkIsActive(String couponName) throws Exception;
	   Coupon getCouponByName(String couponName) throws Exception;
	   boolean updateCoupon(String couponCode, Timestamp start, Timestamp end) throws Exception;
	   boolean deleteCoupon(String couponName) throws Exception;
	   List<Coupon> listOfCoupons(String username) throws Exception;

}
