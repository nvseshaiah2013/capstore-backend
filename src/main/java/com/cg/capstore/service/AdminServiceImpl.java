package com.cg.capstore.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.capstore.dao.IAdminDao;
import com.cg.capstore.entities.Address;
import com.cg.capstore.entities.Category;
import com.cg.capstore.entities.CommonFeedback;
import com.cg.capstore.entities.CustomerDetails;
import com.cg.capstore.entities.Invitation;
import com.cg.capstore.entities.MerchantDetails;
import com.cg.capstore.entities.Order;
import com.cg.capstore.entities.Product;
import com.cg.capstore.entities.SubCategory;
import com.cg.capstore.exceptions.InvalidAttributeException;
import com.cg.capstore.response.ThirdPartyMerchantDetails;

@Service
@Transactional
public class AdminServiceImpl implements IAdminService {

	@Autowired
	private IAdminDao adminDao;

	@Override
	public List<CustomerDetails> getCustomerList() {
		return adminDao.getCustomerList();
	}

	@Override
	public List<Invitation> getInvites(){
		return adminDao.getInvites();
	}

	@Override
	public List<Address> getAddressByUsername(String username) {
		return adminDao.getAddressByUsername(username);
	}

	@Override
	public List<Category> addCategory(Category category) {
		return adminDao.addCategory(category);
	}

	@Override
	public List<Category> getAllCategory() {

		return adminDao.getAllCategory();
	}



	@Override
	public List<SubCategory> getAllSubCategory(int categoryId) {
		
		return adminDao.getAllSubCategory(categoryId);
	}


	@Override
	public List<SubCategory> addSubCategory(SubCategory subCategory, int categoryId) {

		return adminDao.addSubCategory(subCategory, categoryId);
	}
	
	@Override
	public void sendInvite(Invitation invitation) throws Exception {
		adminDao.sendInvite(invitation);
	}

	@Override
	public Long countOfCustomers() throws Exception {
		return adminDao.countOfCustomers();
	}
	
	@Override
	public Long countOfMerchants() throws Exception {
		return adminDao.countOfMerchants();
	}

	@Override
	public List<MerchantDetails> topRatedMerchants() {
		return adminDao.topRatedMerchants();
	}
	
	@Override
	public void addMerchant(ThirdPartyMerchantDetails details) {
		adminDao.addMerchant(details);
	}

	@Override
	public boolean checkValidPhoneNumber(String phoneNo) throws InvalidAttributeException {
		if(adminDao.checkValidPhoneNumber(phoneNo))
			return true;
		else
			throw new InvalidAttributeException("Phone Number Already Exists");
	}

	@Override
	public boolean checkValidEmail(String email) throws InvalidAttributeException {
		if(adminDao.checkValidEmail(email))
			return true;
		else
			throw new InvalidAttributeException("Email Already Exists");
	}

	@Override
	public int setMinOrderValueAmount(int amount) {
		return adminDao.setMinOrderValueAmount(amount);
	}

	@Override
	public int getMinOrderValueAmount() {
		
		return  adminDao.getMinOrderValueAmount();
	}
	
	
	public List<Order> getOrders() {
		return adminDao.getOrders();
	}

	@Override
	public boolean updateStatus(long orderId, String status) {
		return adminDao.updateStatus(orderId, status);
	}

	@Override
	public List<CommonFeedback> getFeedbacks() {
		return this.adminDao.getFeedbacks();
	}
	
	@Override
	public CommonFeedback redirectFeedback(int id) throws Exception {
		CommonFeedback feedback = findCommonFeedbackById(id) ;
		this.adminDao.redirectFeedback(feedback);
		return feedback;
	}
	
	@Override
	public CommonFeedback findCommonFeedbackById(int id) throws Exception {
		CommonFeedback feedback = this.adminDao.findCommonFeedbackById(id);
		if(feedback==null) {
			throw new Exception("Common Feedback Not Found For id: " + id);
		}
		return feedback;
	}
	
	@Override
	public MerchantDetails findMerchantByUsername(String username) throws Exception {
		MerchantDetails merchant = this.adminDao.findMerchantByUsername(username);
		if(merchant == null)
		{
			throw new Exception("Merchant With username : " + username + " not found!");
		}
		return merchant;
	}
	
	@Override
	public Integer getOrderCount(String username) throws Exception {
		MerchantDetails merchant = findMerchantByUsername(username);
		return merchant.getOrders().size();
	}
	
	@Override
	public Integer getProductCount(String username) throws Exception {
		MerchantDetails merchant = findMerchantByUsername(username);
		return merchant.getProducts().size();
	}
	
	@Override
	public Integer getMerchantRating(String username) throws Exception {
		MerchantDetails merchant = findMerchantByUsername(username);
		return merchant.getRating();
	}
	
	@Override 
	public Set<CommonFeedback> getCommonFeedbackByMerchant(String username) throws Exception {
		MerchantDetails merchant = findMerchantByUsername(username);
		return merchant.getFeedbacks();
	}
	
	@Override 
	public Set<Order> getOrdersByMerchant(String username) throws Exception {
		MerchantDetails merchant = findMerchantByUsername(username);
		return merchant.getOrders();
	}
	
	@Override 
	public Set<Product> getProductsByMerchant(String username) throws Exception {
		MerchantDetails merchant = findMerchantByUsername(username);
		return merchant.getProducts();
	}
	
	@Override 
	public Set<CommonFeedback> getFeedbacksByMerchant(String username) throws Exception {
		MerchantDetails merchant = findMerchantByUsername(username);
		return merchant.getFeedbacks();
	}

	@Override
	public List<Product> getTrendingProducts() {
		return adminDao.getTrendingProducts();
	}

	@Override
	public Double todayRevenue() {
		return adminDao.todayRevenue();
	}
	
	@Override
	public Long todayProductSales() {
		return adminDao.todayProductSales();
	}

	@Override
	public List<Order> recentOrders() {
		return adminDao.recentOrders();
	}

	@Override
	public List<Double> recentRevenues() {
		return adminDao.recentRevenues();
	}

	@Override
	public List<Long> recentOrdersCount() {
		return adminDao.recentOrdersCount();
	}
}
