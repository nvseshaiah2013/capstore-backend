package com.cg.capstore.service;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cg.capstore.dao.ICouponDao;
import com.cg.capstore.entities.Coupon;

@Transactional
@Service("CouponServiceImpl")
public class CouponServiceImpl implements ICouponService{
 
	@Autowired
	private ICouponDao couponDao;
	
	private Logger logger = Logger.getLogger(getClass());
	
	
	@Override
	public boolean checkCouponCode(String code) throws Exception {
		 if(!couponDao.checkCouponCode(code)) {
			 logger.error("Coupon code already exist.");
			 throw new RuntimeException("Coupon Code already Exist.");
		 }
		 logger.info("valid coupon code.");
		 return true;
		 
	}

	@Override
	public boolean addCoupon(Coupon coupon) throws Exception {
	    if(checkStartDate(coupon.getCouponStartDate()) && checkEndDate(coupon.getCouponStartDate(),coupon.getCouponEndDate())) {
	    	coupon.setActive(true);
	    	logger.info("Coupon Code valid and generated.");
	    	return couponDao.addCoupon(coupon);
	    }
	    else{
	    	logger.error("Start date or end date is not valid.");
	    	return false;
	    }
		
	}

	@Override
	public boolean checkStartDate(Timestamp date) throws Exception {
		if(couponDao.checkStartDate(date)) {
			logger.info("Start date is valid");
			return true;
		}
		else {
			logger.error("Start date is not valid.");
			throw new Exception("Start date must be greater then current time...");
		}
	}

	@Override
	public boolean checkEndDate(Timestamp startDate, Timestamp endDate) throws Exception {
		if(couponDao.checkEndDate(startDate, endDate)) {
			logger.error("Endt Date is valid...");
			return true;
		}
		else {
			logger.info("End Date is valid...");
			throw new Exception("End Date must be greater then start date..");
		}
	}

	@Override
	public boolean updateStatus() throws Exception {
		logger.info("Coupon status updated.");
		return couponDao.updateStatus();
	}

	@Override
	public boolean checkIsActive(String couponName) throws Exception {
		logger.info("getting Coupon status");
		return couponDao.checkIsActive(couponName);
	}

	@Override
	public Coupon getCouponByName(String couponName) throws Exception {
		logger.info("coupon by name.");
		return couponDao.getCouponByName(couponName);
	}

	@Override
	public boolean updateCoupon(String couponCode, Timestamp start, Timestamp end) throws Exception {
		logger.info("coupon updated.");
		return couponDao.updateCoupon(couponCode, start, end);
	}

	@Override
	public boolean deleteCoupon(String couponName) throws Exception {
		logger.info("coupon deleted.");
		return couponDao.deleteCoupon(couponName);
	}

	@Override
	public List<Object> listOfCoupons() throws Exception {
		logger.info("Getting list of coupon");
		return couponDao.listOfCoupons();
	}
	

}
