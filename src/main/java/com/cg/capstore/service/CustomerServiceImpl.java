package com.cg.capstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.capstore.dao.ICustomerDao;
import com.cg.capstore.dao.UserRepository;
import com.cg.capstore.entities.Address;
import com.cg.capstore.entities.CustomerDetails;
import com.cg.capstore.entities.Order;
import com.cg.capstore.entities.User;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService, UserDetailsService{


	@Autowired
	private ICustomerDao customerDao;
	
	@Autowired
	private UserRepository userRepository;


	@Override
	public Long countOfCustomers() throws Exception {
		return customerDao.countOfCustomers();
	}
	
	
	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
	
	@Override
	public String createNewUser( com.cg.capstore.request.UserDetails user) throws Exception {
		return customerDao.createNewUser(user);
	}

	@Override
	public boolean changePassword(String username, String oldPassword, String newPassword) throws Exception {
		return customerDao.changePassword(username, oldPassword, newPassword);
	}

	@Override
	public String forgotPassword(String username, String securityQuestion, String securityAnswer) throws Exception {
		return customerDao.forgotPassword(username, securityQuestion, securityAnswer);
	}

	@Override
	public Set<Order> getOrders(String username) {
		return customerDao.getOrders(username);
	}

	@Override
	public String getStatus(String username, Integer orderId) {
		return customerDao.getStatus(username, orderId);
	}

	@Override
	public boolean updateStatus(String username, Integer orderId, String status) {
		return customerDao.updateStatus(username, orderId, status);
	}

	@Override
	public List<Address> viewAddress(String username) {
		return customerDao.viewAddress(username);
	}

	@Override
	public boolean deleteAddress(Integer addressId) {
		return customerDao.deleteAddress(addressId);
	}

	@Override
	public boolean addAddress(Address add, String userName) {
		return customerDao.addAddress(add, userName);
	}

	@Override
	public CustomerDetails getUserDetails(String username) {
		return customerDao.getUserDetails(username);
	}

	@Override
	public String editUser(CustomerDetails customer) {
		return customerDao.editUser(customer);
	}

}
