package com.cg.capstore.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import com.cg.capstore.entities.Address;
import com.cg.capstore.entities.CustomerDetails;
import com.cg.capstore.entities.MerchantDetails;
import com.cg.capstore.entities.Order;
import com.cg.capstore.entities.User;
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
	public Long countOfCustomers() throws Exception {
		Query query = entityManager.createQuery("SELECT COUNT(*) FROM CustomerDetails");
		return (Long) query.getSingleResult();
	}

	@Override
	public String createNewUser(UserDetails userDetails) throws Exception {
		if (userRepository.existsById(userDetails.getUsername())) {
			return "User Already Exists.Try Login/forgot password...";
		} else {
			user.setUsername(userDetails.getUsername());
			user.setPassword(BCrypt.hashpw(userDetails.getPassword(), BCrypt.gensalt(12)));
			user.setSecurityQuestion(userDetails.getSecurityQuestion());
			user.setSecurityAnswer(userDetails.getSecurityAnswer());

			if (userDetails.getRole().equals("Customer")) {
				user.setRole("ROLE_CUSTOMER");
				customer.setName(userDetails.getName());
				customer.setGender(userDetails.getGender());
				customer.setPhoneNo(userDetails.getPhoneNo());
				customer.setAlternatePhoneNo(userDetails.getAlternatePhoneNo());
				customer.setAlternateEmail(userDetails.getAlternateEmail());
				customer.setUsername(userDetails.getUsername());

				customerRepository.save(customer);
			} else {
				user.setRole("ROLE_MERCHANT");
				merchant.setName(userDetails.getName());
				merchant.setGender(userDetails.getGender());
				merchant.setPhoneNo(userDetails.getPhoneNo());
				merchant.setAlternatePhoneNo(userDetails.getAlternatePhoneNo());
				merchant.setAlternateEmail(userDetails.getAlternateEmail());
				merchant.setUsername(userDetails.getUsername());

				merchantRepository.save(merchant);
			}
			userRepository.save(user);
			return userDetails.getName() + " is registered successfully!..";
		}
	}

	@Override
	public boolean changePassword(String username, String oldPassword, String newPassword) throws Exception {

		try {

			user = userRepository.getOne(username);
			System.out.println(user);
			if (user.getUsername().equals(username)) {
				if (BCrypt.checkpw(oldPassword, user.getPassword())) {
					String hashNewPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt(12));
					user.setPassword(hashNewPassword);
					userRepository.save(user);
					return true;
				}
			}

		} catch (Exception exception) {
			throw exception;
		}
		return false;
	}

	@Override
	public String forgotPassword(String username, String securityQuestion, String securityAnswer) throws Exception {
		try {
			user = userRepository.getOne(username);
			;
			if (user.getSecurityQuestion().equals(securityQuestion)
					&& user.getSecurityAnswer().equals(securityAnswer)) {

				// chose a Character random from this String
				String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

				// create StringBuffer size of AlphaNumericString
				StringBuilder sb = new StringBuilder(8);

				for (int i = 0; i <= 8; i++) {

					// generate a random number between
					// 0 to AlphaNumericString variable length
					int index = (int) (AlphaNumericString.length() * Math.random());

					// add Character one by one in end of sb
					sb.append(AlphaNumericString.charAt(index));
				}

				String randomPass = sb.toString();
				// String pass= User.getPassword();
				// String pass1=pass.replace( pass, randomPass);
				user.setPassword(BCrypt.hashpw(randomPass, BCrypt.gensalt(12)));
				userRepository.save(user);
				return randomPass;
			} else {
				return "invalid";
			}
		} catch (Exception exception) {
			throw exception;
		}

	}

	@Override
	public Set<Order> getOrders(String username) {
		if (customerRepository.existsById(username)) {
			CustomerDetails cd = customerRepository.getOne(username);
			return cd.getOrders();
		}

		return null;
	}

	@Override
	public String getStatus(String username, Integer orderId) {
		if (customerRepository.existsById(username)) {
			CustomerDetails customer = customerRepository.getOne(username);
			Set<Order> orders = customer.getOrders();
			for (Order order : orders) {
				if (order.getOrderId() == orderId) {
					System.out.println(order.getTransaction().getCoupon());
					if (order.getTransaction().getCoupon() == null) {
						return "false";
					} else {
						return "true";
					}
				}
			}
		}
		return "false";
	}

	@Override
	public boolean updateStatus(String username, Integer orderId, String status) {
		if (customerRepository.existsById(username)) {
			CustomerDetails customer = customerRepository.getOne(username);
			Set<Order> orders = customer.getOrders();

			for (Order order : orders) {
				if (order.getOrderId() == orderId) {

					Date date = new Date();
					Timestamp timeStamp = new Timestamp(date.getTime());

					order.setStatusDate(timeStamp);
					order.setOrderStatus(status);

					customer.setOrders(orders);
					customerRepository.save(customer);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public List<Address> viewAddress(String userName) {
		String qStr = "SELECT a FROM Address a WHERE username=:uId";
		TypedQuery<Address> query = entityManager.createQuery(qStr, Address.class);
		query.setParameter("uId", userName);
		List<Address> add = query.getResultList();
		return add;
	}

	@Override
	public boolean deleteAddress(Integer addressId)

	{

		String qStr = "SELECT a FROM Address a WHERE a.addressId =: addId";
		TypedQuery<Address> query = entityManager.createQuery(qStr, Address.class);
		query.setParameter("addId", addressId);
		Address add = query.getSingleResult();
		add.setDeleted(true);
		Session cs = entityManager.unwrap(Session.class);
		cs.saveOrUpdate(add);
		return true;

	}

	@Override
	public boolean addAddress(Address add, String userName) {
		String command = "select user from User user where user.username =: puser";
		TypedQuery<User> query2 = entityManager.createQuery(command, User.class);
		query2.setParameter("puser", userName);

		User user = query2.getSingleResult();
		add.setUser(user);
		add.setDeleted(false);
		Session cs = entityManager.unwrap(Session.class);
		cs.saveOrUpdate(add);
		return true;
	}

	@Override
	public CustomerDetails getUserDetails(String username) {
		if (customerRepository.existsById(username)) {
			CustomerDetails cd = customerRepository.findById(username).get();
			return cd;
		}
		return null;

	}

	@Override
	public String editUser(CustomerDetails customer) {
		customerRepository.save(customer);
		return "updated successfully";

	}
	
}
