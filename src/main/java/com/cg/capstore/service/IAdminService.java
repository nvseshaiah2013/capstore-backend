package com.cg.capstore.service;

import java.util.List;

import com.cg.capstore.entities.Address;
import com.cg.capstore.entities.Category;
import com.cg.capstore.entities.CustomerDetails;
import com.cg.capstore.entities.SubCategory;

public interface IAdminService {
	public List<CustomerDetails> getCustomerList();
	
	public List<Address> getAddressByUsername(String username);
	
	public List<Category> addCategory(Category category);

	public List<Category> getAllCategory();

	public List<Category> deleteCategory(int id);

	public List<SubCategory> getAllSubCategory(int categoryId);
	
	public List<SubCategory> deleteSubCategory(int categoryId,int subCategoryId);

	List<SubCategory> addSubCategory(SubCategory subCategory, int categoryId);
}
