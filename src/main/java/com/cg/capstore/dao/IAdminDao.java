package com.cg.capstore.dao;

import java.util.List;

import com.cg.capstore.entities.Address;
import com.cg.capstore.entities.Category;
import com.cg.capstore.entities.CustomerDetails;
import com.cg.capstore.entities.Invitation;

import com.cg.capstore.entities.SubCategory;

public interface IAdminDao {
	public List<CustomerDetails> getCustomerList();
	public List<Invitation> getInvites();
	public void sendInvite(Invitation invitation) throws Exception;

	public List<Address> getAddressByUsername(String username);

	public List<Category> addCategory(Category category);

	public List<Category> getAllCategory();

	public List<SubCategory> getAllSubCategory(int categoryId);

	List<SubCategory> addSubCategory(SubCategory subCategory, int categoryId);
}
