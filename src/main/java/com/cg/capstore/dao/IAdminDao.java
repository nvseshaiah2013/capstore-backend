package com.cg.capstore.dao;

import java.util.List;

import com.cg.capstore.entities.Address;
import com.cg.capstore.entities.Category;
import com.cg.capstore.entities.CommonFeedback;
import com.cg.capstore.entities.CustomerDetails;
import com.cg.capstore.entities.Invitation;
import com.cg.capstore.entities.MerchantDetails;
import com.cg.capstore.entities.Order;
import com.cg.capstore.entities.Product;
import com.cg.capstore.entities.SubCategory;
import com.cg.capstore.response.ThirdPartyMerchantDetails;

public interface IAdminDao {
	
	public List<CustomerDetails> getCustomerList();
	
	public List<Invitation> getInvites();
	
	public void sendInvite(Invitation invitation) throws Exception;

	public List<Address> getAddressByUsername(String username);

	public List<Category> addCategory(Category category);

	public List<Category> getAllCategory();

	public List<SubCategory> getAllSubCategory(int categoryId);

	List<SubCategory> addSubCategory(SubCategory subCategory, int categoryId);


	public Long countOfMerchants() throws Exception;

	public Long countOfCustomers() throws Exception;

	
	public List<MerchantDetails> topRatedMerchants();

	public void addMerchant(ThirdPartyMerchantDetails details);
	
	public boolean checkValidEmail(String email);
	
	public boolean checkValidPhoneNumber(String phoneNo);
	
	public int setMinOrderValueAmount(int amount);
	
	public int getMinOrderValueAmount();
	
	public List<Order> getOrders();
	
	boolean updateStatus(long orderId,String status);

	public List<CommonFeedback> getFeedbacks();
	
	public CommonFeedback findCommonFeedbackById(int id) throws Exception;
	
	public void redirectFeedback(CommonFeedback feedback);
	
	public MerchantDetails findMerchantByUsername(String username);
	
	public List<Category> updateCategory(Category category);

	public List<Product> getTrendingProducts();

	public Double todayRevenue();

	public Long todayProductSales();

	public List<Order> recentOrders();

	public List<Double> recentRevenues();

	public List<Long> recentOrdersCount();

	public Product getProductById(int prodId);
}
