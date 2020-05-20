package com.cg.capstore.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import com.cg.capstore.entities.CustomerDetails;
import com.cg.capstore.entities.MerchantDetails;
import com.cg.capstore.entities.User;
import com.cg.capstore.dao.CustomerRepository;
import com.cg.capstore.dao.MerchantRepository;
import com.cg.capstore.dao.UserRepository;
import com.cg.capstore.request.UserDetails;

@Repository
public class CustomerDaoImpl implements ICustomerDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	MerchantRepository merchantRepository;
	CustomerDetails customer = new CustomerDetails();
	User user = new User();
	MerchantDetails merchant = new MerchantDetails();

	@Override
	public String createNewUser(UserDetails userDetails) {
		if(userRepository.existsById(userDetails.getUsername()))
		{
			return "User Already Exists.Try different username";
		}
		else {
			user.setUsername(userDetails.getUsername());
			user.setPassword(BCrypt.hashpw(userDetails.getPassword(), BCrypt.gensalt(12)));
			user.setRole(userDetails.getRole());
			user.setSecurityQuestion(userDetails.getSecurityQuestion());
			user.setSecurityAnswer(userDetails.getSecurityAnswer());
			
			userRepository.save(user);
			
			if(userDetails.getRole().equals("ROLE_CUSTOMER")) {
			
			customer.setName(userDetails.getName());
			customer.setGender(userDetails.getGender());
			customer.setPhoneNo(userDetails.getPhoneNo());
			customer.setAlternatePhoneNo(userDetails.getAlternatePhoneNo());
			customer.setAlternateEmail(userDetails.getAlternateEmail());
			customer.setUsername(userDetails.getUsername());
			
			customerRepository.save(customer);
			}
			else {
				merchant.setName(userDetails.getName());
				merchant.setGender(userDetails.getGender());
				merchant.setPhoneNo(userDetails.getPhoneNo());
				merchant.setAlternatePhoneNo(userDetails.getAlternatePhoneNo());
				merchant.setAlternateEmail(userDetails.getAlternateEmail());
				merchant.setUsername(userDetails.getUsername());
				
				merchantRepository.save(merchant);
			}
			return userDetails.getName()+" is registered successfully!..";
		}
	}
	
}
