package com.cg.capstore.service;

import java.util.List;

import com.cg.capstore.entities.Address;
import com.cg.capstore.entities.Category;
import com.cg.capstore.entities.CustomerDetails;

import com.cg.capstore.entities.Invitation;
import com.cg.capstore.entities.MerchantDetails;
import com.cg.capstore.entities.Order;
import com.cg.capstore.entities.SubCategory;
import com.cg.capstore.exceptions.InvalidAttributeException;
import com.cg.capstore.response.ThirdPartyMerchantDetails;

public interface IAdminService {
	
	public List<CustomerDetails> getCustomerList();
	
	public List<Invitation> getInvites();
	
	public void sendInvite(Invitation invitation) throws Exception;
	
	public List<Address> getAddressByUsername(String username);
	
	public List<Category> addCategory(Category category);

	public List<Category> getAllCategory();

	public List<SubCategory> getAllSubCategory(int categoryId);

	List<SubCategory> addSubCategory(SubCategory subCategory, int categoryId);
	
	Long countOfMerchants() throws Exception;
	
	Long countOfCustomers() throws Exception;
	
	public List<MerchantDetails> topRatedMerchants();
	
	public void addMerchant(ThirdPartyMerchantDetails details);
	
	public boolean checkValidPhoneNumber(String phoneNo) throws InvalidAttributeException;
	
	public boolean checkValidEmail(String email) throws InvalidAttributeException;
	
	public int setMinOrderValueAmount(int amount);
	
	public int getMinOrderValueAmount();
	
	List<Order> getOrders();
	
	boolean updateStatus(long orderId,String status);
}
