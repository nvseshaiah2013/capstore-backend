package com.cg.capstore.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
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
	
	private Logger logger = Logger.getLogger(getClass());

	@Override
	public List<MerchantDetails> getMerchants() throws Exception {
		logger.info("List of Merchants returned");
		return merchantDao.getMerchants();
	}

	@Override
	public MerchantDetails getMerchantInfo(String username) {
		logger.info("Details of Merchants returned");
		return merchantDao.getMerchantInfo(username);
	}
	
	@Override
	public Set<Order> getMerchantOrders(String username){
		logger.info("No of orders of Merchants returned");
		return merchantDao.getMerchantOrders(username);
	}
	
	@Override
	public Order acceptMerchantOrder(long orderId, String status) {
		logger.info("Merchant accepted the order");
		return merchantDao.acceptMerchantOrder(orderId, status);
	}

	
	@Override
	public void activateMerchant(String username ) throws Exception {
		logger.info("Merchant is activated");
		MerchantDetails merchant = findMerchantByUsername(username);
		this.merchantDao.activateMerchant(merchant);
	}
	
	@Override
	public void deActivateMerchant(String username ) throws Exception {
		logger.info("Merchnat is deactivated");
		MerchantDetails merchant = findMerchantByUsername(username);
		this.merchantDao.deActivateMerchant(merchant);
	}
	
	@Override
	public MerchantDetails findMerchantByUsername(String username ) throws Exception {
		logger.info("Details of merchant of particular id returned");
		MerchantDetails merchant = this.merchantDao.findMerchantByUsername(username);
		if(merchant == null) {
			throw new Exception("Merchant with " + username  + " Not Found");
		}
		return merchant;
	}

	
	@Override
	public Set<Product> getMerchantProducts(String username){
		logger.info("List of Product returned");
		return merchantDao.getMerchantProducts(username);
	}
	
	@Override
	public Set<Invitation> getInvites(String username) throws Exception {
		logger.info("Invite for merchant received");
		MerchantDetails merchant = findMerchantByUsername(username);
		return merchant.getInvites();
	}
	
	@Override
	public void activateProduct(int id,String username) throws Exception {
		logger.info("Product is enabled");
		Product product = findProductById(id);
		if(product.getMerchant().getUsername().equals(username))
		merchantDao.activateProduct(product);
		else
			throw new Exception("The product does not belong to this " + username );
	}
	
	@Override
	public void deActivateProduct(int id,String username) throws Exception {
		logger.info("Product is disabled");
		Product product = findProductById(id);
		if(product.getMerchant().getUsername().equals(username))
		merchantDao.inActivateProduct(product);
		else
			throw new Exception("The product does not belong to this " + username);
	}
	
	@Override
	public Product findProductById(int id) throws Exception {
		logger.info("Product with particular id is found");
		Product product = this.merchantDao.findProductById(id);
		if(product == null) 
			throw new Exception("Product with " + id + " not found");
		return product;
	}
	
	@Override
	public boolean checkCouponCode(String code) throws Exception {
		 if(!merchantDao.checkCouponCode(code)) {
			 logger.error("Coupon Code already Exist..");
			 throw new RuntimeException("Coupon Code already Exist..");
		 }
		 return true;
	}

	@Override
	public boolean addCoupon(Coupon coupon,String username) throws Exception {
	    if(checkStartDate(coupon.getCouponStartDate()) && checkEndDate(coupon.getCouponStartDate(),coupon.getCouponEndDate())) {
	    	coupon.setActive(true);
	    	logger.info("New coupon added!!");
	    	return merchantDao.addCoupon(coupon,username);
	    }
	    else{
	    	logger.error("Error adding new coupon");
	    	return false;
	    }
	}

	@Override
	public boolean checkStartDate(Timestamp date) throws Exception {
		if(merchantDao.checkStartDate(date)) {
			return true;
		}
		else {
			logger.error("Invalid start date..!!");
			throw new Exception("Start date must be greater then current time...");
		}
	}

	@Override
	public boolean checkEndDate(Timestamp startDate, Timestamp endDate) throws Exception {
		if(merchantDao.checkEndDate(startDate, endDate)) {
			return true;
		}
		else {
			logger.error("Invalid end date..!!");
			throw new Exception("End Date must be greater then start date..");
		}
	}

	@Override
	public boolean updateStatus() throws Exception {
		boolean result=merchantDao.updateStatus();
		if(result==false) {
		   logger.error("Error updating status..!!");
		   return result;
		}
		logger.info("Coupon status updated..!!");
		return result;
	}

	@Override
	public boolean checkIsActive(String couponName) throws Exception {
		return merchantDao.checkIsActive(couponName);
	}

	@Override
	public Coupon getCouponByName(String couponName) throws Exception {
		logger.info("Fetched coupon by name..!!");
		return merchantDao.getCouponByName(couponName);
	}

	@Override
	public boolean updateCoupon(String couponCode, Timestamp start, Timestamp end) throws Exception {
		boolean result=merchantDao.updateCoupon(couponCode, start, end);
		if(result==false) {
			logger.error("Error updating coupon..!!");
			return result;
		}
		logger.info("Coupon updated...!!");
		return result;
	}

	@Override
	public boolean deleteCoupon(String couponName) throws Exception {
		boolean result=merchantDao.deleteCoupon(couponName);
		if(result==false) {
			logger.error("Error deleting coupon..!!");
			return result;
		}
		logger.info("Deleted coupon..!!");
		return result;
	}

	@Override
	public List<Coupon> listOfCoupons(String username) throws Exception {
		logger.info("Fetched all coupons..!!");
		return merchantDao.listOfCoupons(username);
	}
}
