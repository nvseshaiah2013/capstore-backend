package com.cg.capstore.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.capstore.dao.IAdminDao;
import com.cg.capstore.entities.Address;
import com.cg.capstore.entities.Category;
import com.cg.capstore.entities.CustomerDetails;
import com.cg.capstore.entities.Invitation;
import com.cg.capstore.entities.Order;
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
}
