package com.cg.capstore.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.capstore.entities.Coupon;
import com.cg.capstore.entities.MerchantDetails;
import com.cg.capstore.entities.Order;
import com.cg.capstore.entities.Product;
import com.cg.capstore.entities.User;


@Repository
public class MerchantDaoImpl implements IMerchantDao {

	@PersistenceContext
	private EntityManager entityManager;
	

	@Override
	public List<MerchantDetails> getMerchants() throws Exception {
		String str = "SELECT merchant FROM MerchantDetails merchant";
		TypedQuery<MerchantDetails> query = entityManager.createQuery(str,MerchantDetails.class);
		List<MerchantDetails> merchants = query.getResultList();
		return merchants;
	}
	
	@Override
	public MerchantDetails getMerchantInfo(String username) {
		return entityManager.find(MerchantDetails.class, username);
	}
	
	@Override
	public Set<Order> getMerchantOrders(String username){
		return entityManager.find(MerchantDetails.class, username).getOrders();
	}
	
	@Override
	public Order acceptMerchantOrder(long orderId, String status) {
		Order order = entityManager.find(Order.class, orderId);
		order.setOrderStatus(status);
		return entityManager.merge(order);
	}


	
	@Override
	public void activateMerchant(MerchantDetails merchant) throws Exception {
		merchant.setDeleted(false);
		entityManager.merge(merchant);
	}
	
	@Override
	public void deActivateMerchant(MerchantDetails merchant) throws Exception {
		merchant.setDeleted(true);
		entityManager.merge(merchant);
	}
	
	@Override
	public MerchantDetails findMerchantByUsername(String username) throws Exception {
		MerchantDetails merchant = entityManager.find(MerchantDetails.class, username);
		return merchant;
	}

	@Override
	public Set<Product> getMerchantProducts(String username){
		return entityManager.find(MerchantDetails.class, username).getProducts();
	}
	@Override
	public void activateProduct(Product product) throws Exception {
		product.setProductActivated(true);
		entityManager.merge(product);
	}
	
	@Override
	public void inActivateProduct(Product product) throws Exception {
		product.setProductActivated(false);
		entityManager.merge(product);
	}
	
	@Override
	public Product findProductById(int id) throws Exception {
		return entityManager.find(Product.class, id);
	}
	
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
	public boolean addCoupon(Coupon coupon,String username) throws Exception {
		if(!checkCouponCode(coupon.getCouponCode())) {
			return false;
		}
		
		User user=entityManager.find(User.class, username);
		if(user==null) {
			return false;
		}
		
		coupon.setUser(user);
		
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
	public boolean updateCoupon(String couponCode, Timestamp start, Timestamp end) throws Exception {
		try {
		 Coupon coupon = entityManager.find(Coupon.class, couponCode);
		 coupon.setCouponStartDate(start);
		 coupon.setCouponEndDate(end);
		 entityManager.merge(coupon);
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
			throw new Exception("No Such coupon Found...");
		}
	}

	@Override
	public List<Coupon> listOfCoupons(String username) throws Exception {
		updateStatus();
		String statement = "SELECT couponname FROM Coupon couponname";
		TypedQuery<Coupon> query = entityManager.createQuery(statement, Coupon.class);
		List<Coupon> couponList = query.getResultList();
		couponList=couponList.stream().filter(e->e.getUser()!=null && e.getUser().getUsername().equals(username)).collect(Collectors.toList());
		if(couponList.size() == 0) {
			throw new Exception("No coupons Available...");
		}
		return couponList;
	}
}
