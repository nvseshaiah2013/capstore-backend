package com.cg.capstore.service;

import java.util.List;
import java.util.Set;

import com.cg.capstore.entities.Address;
import com.cg.capstore.entities.CustomerDetails;
import com.cg.capstore.entities.Order;
import com.cg.capstore.request.UserDetails;

public interface ICustomerService {

	String createNewUser(UserDetails user) throws Exception;
	
	boolean changePassword(String username, String oldPassword, String newPassword) throws Exception;

	String forgotPassword(String username, String securityQuestion, String securityAnswer) throws Exception;

	Set<Order> getOrders(String username);

	String getStatus(String username, Integer orderId);

	boolean updateStatus(String username, Integer orderId, String status);

	public List<Address> viewAddress(String username);

	public boolean deleteAddress(Integer addressId);

	public boolean addAddress(Address add, String userName);

	public CustomerDetails getUserDetails(String username);

	public String editUser(CustomerDetails customer);

	Long countOfCustomers() throws Exception;

}
