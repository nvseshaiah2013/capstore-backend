package com.cg.capstore.service;

import java.sql.Timestamp;
import java.util.List;

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
	
	
	@Override
	public boolean checkCouponCode(String code) throws Exception {
		 if(!couponDao.checkCouponCode(code)) {
			 throw new RuntimeException("Coupon Code already Exist.");
		 }
		 return true;
		 
	}

	@Override
	public boolean addCoupon(Coupon coupon) throws Exception {
	    if(checkStartDate(coupon.getCouponStartDate()) && checkEndDate(coupon.getCouponStartDate(),coupon.getCouponEndDate())) {
	    	coupon.setActive(true);
	    	return couponDao.addCoupon(coupon);
	    }
	    else{
	    	return false;
	    }
		
	}

	@Override
	public boolean checkStartDate(Timestamp date) throws Exception {
		if(couponDao.checkStartDate(date)) {
			return true;
		}
		else {
			throw new Exception("Start date is not valid...");
		}
	}

	@Override
	public boolean checkEndDate(Timestamp startDate, Timestamp endDate) throws Exception {
		if(couponDao.checkEndDate(startDate, endDate))
			return true;
		else {
			throw new Exception("End Date is not valid...");
		}
	}

	@Override
	public boolean updateStatus() throws Exception {
		return couponDao.updateStatus();
	}

	@Override
	public boolean checkIsActive(String couponName) throws Exception {
		return couponDao.checkIsActive(couponName);
	}

	@Override
	public Coupon getCouponByName(String couponName) throws Exception {
		return couponDao.getCouponByName(couponName);
	}

	@Override
	public boolean updateCoupon(Coupon coupon) throws Exception {
		
		return couponDao.updateCoupon(coupon);
	}

	@Override
	public boolean deleteCoupon(String couponName) throws Exception {
		return couponDao.deleteCoupon(couponName);
	}

	@Override
	public List<Object> listOfCoupons() throws Exception {
		return couponDao.listOfCoupons();
	}
	

}
