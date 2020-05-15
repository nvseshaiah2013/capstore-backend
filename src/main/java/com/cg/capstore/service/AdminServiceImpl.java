package com.cg.capstore.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.capstore.dao.IAdminDao;
import com.cg.capstore.entities.Address;
import com.cg.capstore.entities.Category;
import com.cg.capstore.entities.CustomerDetails;
import com.cg.capstore.entities.SubCategory;

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
	public List<Category> deleteCategory(int id) {

		return adminDao.deleteCategory(id);
	}

	@Override
	public List<SubCategory> getAllSubCategory(int categoryId) {
		
		return adminDao.getAllSubCategory(categoryId);
	}

	@Override
	public List<SubCategory> deleteSubCategory(int categoryId,int subCategoryId) {
		
		return adminDao.deleteSubCategory( categoryId, subCategoryId);
	}

	@Override
	public List<SubCategory> addSubCategory(SubCategory subCategory, int categoryId) {

		return adminDao.addSubCategory(subCategory, categoryId);
	}

	
	
}
