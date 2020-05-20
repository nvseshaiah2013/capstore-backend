package com.cg.capstore.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.capstore.dao.IMerchantDao;
import com.cg.capstore.entities.Coupon;
import com.cg.capstore.entities.Invitation;
import com.cg.capstore.entities.MerchantDetails;
import com.cg.capstore.entities.Order;
import com.cg.capstore.entities.Product;

@Service
@Transactional
public class MerchantServiceImpl implements IMerchantService {

	@Autowired
	private IMerchantDao merchantDao;

	@Override
	public List<MerchantDetails> getMerchants() throws Exception {
		return merchantDao.getMerchants();
	}

	@Override
	public MerchantDetails getMerchantInfo(String username) {
		return merchantDao.getMerchantInfo(username);
	}
	
	@Override
	public Set<Order> getMerchantOrders(String username){
		return merchantDao.getMerchantOrders(username);
	}
	
	@Override
	public Order acceptMerchantOrder(long orderId, String status) {
		return merchantDao.acceptMerchantOrder(orderId, status);
	}

	
	@Override
	public void activateMerchant(String username ) throws Exception {
		MerchantDetails merchant = findMerchantByUsername(username);
		this.merchantDao.activateMerchant(merchant);
	}
	
	@Override
	public void deActivateMerchant(String username ) throws Exception {
		MerchantDetails merchant = findMerchantByUsername(username);
		this.merchantDao.deActivateMerchant(merchant);
	}
	
	@Override
	public MerchantDetails findMerchantByUsername(String username ) throws Exception {
		MerchantDetails merchant = this.merchantDao.findMerchantByUsername(username);
		if(merchant == null) {
			throw new Exception("Merchant with " + username  + " Not Found");
		}
		return merchant;
	}

	
	@Override
	public Set<Product> getMerchantProducts(String username){
		return merchantDao.getMerchantProducts(username);
	}
	
	@Override
	public Set<Invitation> getInvites(String username) throws Exception {
		MerchantDetails merchant = findMerchantByUsername(username);
		return merchant.getInvites();
	}
	
	@Override
	public void activateProduct(int id) throws Exception {
		Product product = findProductById(id);
		merchantDao.activateProduct(product);
	}
	
	@Override
	public void deActivateProduct(int id) throws Exception {
		Product product = findProductById(id);
		merchantDao.inActivateProduct(product);
	}
	
	@Override
	public Product findProductById(int id) throws Exception {
		Product product = this.merchantDao.findProductById(id);
		if(product == null) 
			throw new Exception("Product with " + id + " not found");
		return product;
	}
	
	@Override
	public boolean checkCouponCode(String code) throws Exception {
		 if(!merchantDao.checkCouponCode(code)) {
			 throw new RuntimeException("Coupon Code already Exist.");
		 }
		 return true;
		 
	}

	@Override
	public boolean addCoupon(Coupon coupon,String username) throws Exception {
	    if(checkStartDate(coupon.getCouponStartDate()) && checkEndDate(coupon.getCouponStartDate(),coupon.getCouponEndDate())) {
	    	coupon.setActive(true);
	    	return merchantDao.addCoupon(coupon,username);
	    }
	    else{
	    	return false;
	    }
		
	}

	@Override
	public boolean checkStartDate(Timestamp date) throws Exception {
		if(merchantDao.checkStartDate(date)) {
			return true;
		}
		else {
			throw new Exception("Start date must be greater then current time...");
		}
	}

	@Override
	public boolean checkEndDate(Timestamp startDate, Timestamp endDate) throws Exception {
		if(merchantDao.checkEndDate(startDate, endDate))
			return true;
		else {
			throw new Exception("End Date must be greater then start date..");
		}
	}

	@Override
	public boolean updateStatus() throws Exception {
		return merchantDao.updateStatus();
	}

	@Override
	public boolean checkIsActive(String couponName) throws Exception {
		return merchantDao.checkIsActive(couponName);
	}

	@Override
	public Coupon getCouponByName(String couponName) throws Exception {
		return merchantDao.getCouponByName(couponName);
	}

	@Override
	public boolean updateCoupon(String couponCode, Timestamp start, Timestamp end) throws Exception {
		
		return merchantDao.updateCoupon(couponCode, start, end);
	}

	@Override
	public boolean deleteCoupon(String couponName) throws Exception {
		return merchantDao.deleteCoupon(couponName);
	}

	@Override
	public List<Coupon> listOfCoupons(String username) throws Exception {
		return merchantDao.listOfCoupons(username);
	}

}
