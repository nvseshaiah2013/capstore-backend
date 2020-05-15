package com.cg.capstore.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.capstore.entities.Coupon;

@Repository("CouponDaoImpl")
public class CouponDaoImpl implements ICouponDao{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public boolean checkCouponCode(String code) throws Exception {
		try {
		Coupon coupon = entityManager.find(Coupon.class, code);
		if(coupon == null) {
			return true;
		}
		return false;
		}
		catch(Exception exception) {
			throw new Exception("Internal Server Error...");
		}
	}

	@Override
	public boolean addCoupon(Coupon coupon) throws Exception {
		if(!checkCouponCode(coupon.getCouponCode())) {
			return false;
		}
		
		try {
			entityManager.persist(coupon);
			return true;
		}
		catch(Exception exception) {
			throw new Exception("Internal Server Error..");
		}
		
	}

	
	@Override
	public boolean updateStatus() throws Exception {
		String statement = "SELECT couponname FROM Coupon couponname";
		TypedQuery<Coupon> query = entityManager.createQuery(statement, Coupon.class);
		List<Coupon> couponList = query.getResultList();
		Date date = new Date();
		for(int i = 0; i < couponList.size(); i++) {
			int num = couponList.get(i).getCouponStartDate().compareTo(date);
			
			int num1 = couponList.get(i).getCouponEndDate().compareTo(date);
			if(num > 0 || num1 < 0) {
				couponList.get(i).setActive(false);
			}
			else {
				couponList.get(i).setActive(true);
			}
		}
		for(Coupon coupon: couponList) {
			entityManager.merge(coupon);
		}
		return true;
	}

	@Override
	public boolean checkStartDate(Timestamp timeStamp) throws Exception {
		Date date = new Date();
		int num = timeStamp.compareTo(date);
		if(num < 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean checkEndDate(Timestamp startDate, Timestamp endDate) throws Exception {
		int num = startDate.compareTo(endDate);
		if(num >= 0) {
		return false;
	   }
		return true;
	}

	@Override
	public boolean checkIsActive(String couponName) throws Exception {
		updateStatus();
		Coupon coupon = entityManager.find(Coupon.class, couponName);
		return coupon.isActive();
	}

	@Override
	public Coupon getCouponByName(String couponName) throws Exception {
		Coupon coupon = entityManager.find(Coupon.class, couponName);
		if(coupon == null) {
			throw new Exception("No Such Coupon Available");
		}
		else {
			return coupon;
		}
	}

	@Override
	public boolean updateCoupon(Coupon coupon) throws Exception {
		try {
		entityManager.merge(coupon);
		System.out.println("Here We are means we are next to update the details...");
		updateStatus();
		return true;
		}catch(Exception exception) {
			throw new Exception("Internal Server Error...");
		}
	}

	@Override
	public boolean deleteCoupon(String couponName) throws Exception {
		Coupon coupon = entityManager.find(Coupon.class, couponName);
		if(coupon != null) {
			entityManager.remove(coupon);
			return true;
		}
		else {
			throw new Exception("No Such coupon is Founded...");
		}
	}

	@Override
	public List<Object> listOfCoupons() throws Exception {
		updateStatus();
		String statement = "SELECT couponname FROM Coupon couponname";
		TypedQuery<Object> query = entityManager.createQuery(statement, Object.class);
		List<Object> couponList = query.getResultList();
		if(couponList.size() == 0) {
			throw new Exception("No coupon Available...");
		}
		return couponList;
	}
	
	
}
