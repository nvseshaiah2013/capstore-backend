package com.cg.capstore.service;

import java.util.List;
import java.util.Set;

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
import com.cg.capstore.exceptions.NotAvailableException;
import com.cg.capstore.response.ThirdPartyMerchantDetails;

public interface IAdminService {
	
	public List<CustomerDetails> getCustomerList() throws  NotAvailableException;
	
	public List<Invitation> getInvites();
	
	public void sendInvite(Invitation invitation) throws Exception;
	
	public List<Address> getAddressByUsername(String username) throws InvalidAttributeException, NotAvailableException;
	
	public List<Category> addCategory(Category category) throws InvalidAttributeException;

	public List<Category> getAllCategory() throws  NotAvailableException;

	public List<SubCategory> getAllSubCategory(int categoryId) throws InvalidAttributeException;

	public List<SubCategory> addSubCategory(SubCategory subCategory, int categoryId);

	public Long countOfMerchants() throws Exception;
	
	public Long countOfCustomers() throws Exception;
	
	public List<MerchantDetails> topRatedMerchants() throws Exception;
	
	public boolean addMerchant(ThirdPartyMerchantDetails details);
	
	public boolean checkValidPhoneNumber(String phoneNo) throws InvalidAttributeException;
	
	public boolean checkValidEmail(String email) throws InvalidAttributeException;
	
	public int setMinOrderValueAmount(int amount) throws InvalidAttributeException;
	
	public int getMinOrderValueAmount();
	
	public List<Order> getOrders() throws Exception;
	
	int updateStatus(long orderId,String status);

	public List<CommonFeedback> getFeedbacks();
	
	public CommonFeedback redirectFeedback(int id) throws Exception;
	
	public CommonFeedback findCommonFeedbackById(int id) throws Exception;
	
	public Integer getOrderCount(String username) throws Exception;
	
	public MerchantDetails findMerchantByUsername(String username) throws Exception;
	
	public Integer getProductCount(String username) throws Exception;
	
	public Integer getMerchantRating(String username) throws Exception;
	
	public Set<CommonFeedback> getCommonFeedbackByMerchant(String username) throws Exception;
	
	public Set<Order> getOrdersByMerchant(String username) throws Exception;
	
	public Set<Product> getProductsByMerchant(String username) throws Exception;
	
	public Set<CommonFeedback> getFeedbacksByMerchant(String username) throws Exception;

	public List<Category> updateCategory(Category category);


	public List<Product> getTrendingProducts() throws Exception;

	public Double todayRevenue() throws Exception;
	
	public Long todayProductSales() throws Exception;

	public List<Order> recentOrders() throws Exception;

	public List<Double> recentRevenues() throws Exception;

	public List<Long> recentOrdersCount() throws Exception;

	public Product getProductById(int prodId) throws Exception;

	public List<Address> deleteAddressByAddressId(int addressId, String username);

	public void activateProduct(int prodId) throws Exception;

	public void inActivateProduct(int prodId) throws Exception;

	boolean checkCategoryExists(String categoryName) throws InvalidAttributeException;


}
