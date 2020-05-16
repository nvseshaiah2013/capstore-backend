package com.cg.capstore.dao;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.capstore.entities.Address;
import com.cg.capstore.entities.Category;
import com.cg.capstore.entities.CommonFeedback;
import com.cg.capstore.entities.CustomerDetails;
import com.cg.capstore.entities.Invitation;
import com.cg.capstore.entities.MerchantDetails;
import com.cg.capstore.entities.Order;
import com.cg.capstore.entities.SubCategory;
import com.cg.capstore.entities.User;
import com.cg.capstore.response.ThirdPartyMerchantDetails;


@Repository
public class AdminDaoImpl implements IAdminDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<CustomerDetails> getCustomerList() {
		String str="SELECT customer FROM CustomerDetails customer";
		TypedQuery<CustomerDetails> query=entityManager.createQuery(str, CustomerDetails.class);
		List<CustomerDetails> customers=query.getResultList();
		return customers;
		
	}
	
	@Override
	public List<Invitation> getInvites(){
		String str = "SELECT invitation FROM Invitation invitation";
		TypedQuery<Invitation> query = entityManager.createQuery(str,Invitation.class);
		List<Invitation> invites = query.getResultList();
		return invites;
	}
	
	@Override
	public void sendInvite(Invitation invitation) throws Exception {
		entityManager.persist(invitation);
	}

	@Override
	public List<Address> getAddressByUsername(String username) {
		String str="SELECT address FROM Address address WHERE address.user.username=:username AND address.isDeleted=0";
		TypedQuery<Address> query=entityManager.createQuery(str, Address.class);
		query.setParameter("username", username);
		List<Address> addresses=query.getResultList();
		return addresses;
	}

	@Override
	public List<Category> addCategory(Category category) {
		entityManager.persist(category);
		
		return getAllCategory();
	}

	@Override
	public List<Category> getAllCategory() {
		String str="SELECT categories FROM Category categories";
		TypedQuery<Category> query=entityManager.createQuery(str, Category.class);
		List<Category> categories=query.getResultList();
		return categories;
	}


	@Override
	public List<SubCategory> addSubCategory(SubCategory subCategory, int categoryId) {
		
		Category category=entityManager.find(Category.class, categoryId);
		category.addSubCategory(subCategory);
		entityManager.merge(category);
		
		return getAllSubCategory(categoryId);
		
	}

	@Override
	public List<SubCategory> getAllSubCategory(int categoryId) {
		String str="SELECT subCategories FROM SubCategory subCategories WHERE subCategories.category.id=:id";
		TypedQuery<SubCategory> query=entityManager.createQuery(str, SubCategory.class);
		query.setParameter("id", categoryId);
		List<SubCategory> subCategories=query.getResultList();
		return subCategories;
	}
	
	@Override
	public Long countOfMerchants() throws Exception {
		Query query=entityManager.createQuery("SELECT COUNT(*) FROM MerchantDetails");
		return (Long) query.getSingleResult();
	}
	
	@Override
	public Long countOfCustomers() throws Exception {
		Query query=entityManager.createQuery("SELECT COUNT(*) FROM CustomerDetails");
		return (Long) query.getSingleResult();
	}

	@Override
	public List<MerchantDetails> topRatedMerchants() {
		Query query=entityManager.createQuery("SELECT m FROM MerchantDetails m ORDER BY m.rating DESC");
		return query.setMaxResults(3).getResultList();
	}

	@Override
	public void addMerchant(ThirdPartyMerchantDetails details) {
		
		MerchantDetails merchantDetails=new MerchantDetails();
		merchantDetails.setAlternateEmail(details.getAlternateEmail());
		merchantDetails.setAlternatePhoneNo(details.getAlternatePhoneNo());
		merchantDetails.setDeleted(false);
		merchantDetails.setName(details.getName());
		merchantDetails.setUsername(details.getUsername());
		merchantDetails.setThirdParty(true);
		merchantDetails.setGender(details.getGender());
		merchantDetails.setRating(0);
		merchantDetails.setPhoneNo(details.getPhoneNo());
		
		entityManager.persist(merchantDetails);
		
		User userDetails=new User();
		userDetails.setPassword(details.getPassword());
		userDetails.setUsername(details.getUsername());
		userDetails.setSecurityAnswer(details.getSecurityAnswer());
		userDetails.setSecurityQuestion(details.getSecurityQuestion());
		userDetails.setRole("ROLE_MERCHANT");
		Address addressDetails=new Address();
		addressDetails.setAddressLineOne(details.getAddressLineOne());
		addressDetails.setAddressLineTwo(details.getAddressLineTwo());
		addressDetails.setDistrict(details.getDistrict());
		addressDetails.setState(details.getState());
		addressDetails.setLandmark(details.getLandmark());
		addressDetails.setDeleted(false);
		
		userDetails.addAddress(addressDetails);
		
		entityManager.merge(userDetails);

	}
<<<<<<< HEAD

	@Override
	public boolean checkValidEmail(String email) {
		String str="SELECT details.username FROM MerchantDetails details";
		TypedQuery<String> query=entityManager.createQuery(str,String.class);
		List<String> list = query.getResultList().stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());
		str="SELECT details.alternateEmail FROM MerchantDetails details";
		query=entityManager.createQuery(str,String.class);
		List<String> list2 = query.getResultList().stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());
		if(list.contains(email) || list2.contains(email))
			return false;
		else
			return true;

	}

	@Override
	public boolean checkValidPhoneNumber(String phoneNo) {
		String str="SELECT details.phoneNo FROM MerchantDetails details";
		TypedQuery<String> query=entityManager.createQuery(str,String.class);
		List<String> list = query.getResultList();
		str="SELECT details.alternatePhoneNo FROM MerchantDetails details";
		query=entityManager.createQuery(str,String.class);
		List<String> list2 = query.getResultList();
		if(list.contains(phoneNo) || list2.contains(phoneNo))
			return false;
		else
			return true;
	}

	@Override
	public int setMinOrderValueAmount(int amount) {
		String str="UPDATE AdminDetails details SET details.minOrderAmount=:amount";
		Query query=entityManager.createQuery(str);
		query.setParameter("amount",amount);
		System.out.println("setted");
		query.executeUpdate();
		System.out.println("done");
		return getMinOrderValueAmount();
	}

	@Override
	public int getMinOrderValueAmount() {
		String str="SELECT details.minOrderAmount FROM AdminDetails details GROUP BY details.minOrderAmount";
		TypedQuery<Integer> query=entityManager.createQuery(str, Integer.class);
		return query.getSingleResult();
	}
=======
>>>>>>> fddfae32b28f09e8652e0626c3c08200ed47d480

	@Override
	public boolean checkValidEmail(String email) {
		String str="SELECT details.username FROM MerchantDetails details";
		TypedQuery<String> query=entityManager.createQuery(str,String.class);
		List<String> list = query.getResultList().stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());
		str="SELECT details.alternateEmail FROM MerchantDetails details";
		query=entityManager.createQuery(str,String.class);
		List<String> list2 = query.getResultList().stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());
		if(list.contains(email) || list2.contains(email))
			return false;
		else
			return true;

	}

	@Override
	public boolean checkValidPhoneNumber(String phoneNo) {
		String str="SELECT details.phoneNo FROM MerchantDetails details";
		TypedQuery<String> query=entityManager.createQuery(str,String.class);
		List<String> list = query.getResultList();
		str="SELECT details.alternatePhoneNo FROM MerchantDetails details";
		query=entityManager.createQuery(str,String.class);
		List<String> list2 = query.getResultList();
		if(list.contains(phoneNo) || list2.contains(phoneNo))
			return false;
		else
			return true;
	}

	@Override
	public int setMinOrderValueAmount(int amount) {
		String str="UPDATE AdminDetails details SET details.minOrderAmount=:amount";
		Query query=entityManager.createQuery(str);
		query.setParameter("amount",amount);
		System.out.println("setted");
		query.executeUpdate();
		System.out.println("done");
		return getMinOrderValueAmount();
	}

	@Override
	public int getMinOrderValueAmount() {
		String str="SELECT details.minOrderAmount FROM AdminDetails details GROUP BY details.minOrderAmount";
		TypedQuery<Integer> query=entityManager.createQuery(str, Integer.class);
		return query.getSingleResult();
	}

	@Override
	public List<CommonFeedback> getFeedbacks(){
		String str = "SELECT feedback FROM CommonFeedback feedback";
		TypedQuery<CommonFeedback> query = entityManager.createQuery(str,CommonFeedback.class);
		List<CommonFeedback> feedbacks = query.getResultList();
		return feedbacks;
	}
	
	@Override
	public CommonFeedback findCommonFeedbackById(int id) throws Exception{
		CommonFeedback feedback = entityManager.find(CommonFeedback.class, id);
		return feedback;
	}
	
<<<<<<< HEAD
	public List<Order> getOrders() {
		String str="SELECT allOrders FROM Order allOrders";
		TypedQuery<Order> query=entityManager.createQuery(str,Order.class);
		List<Order> orders=query.getResultList();
		return orders;
	}

	@Override
	public int updateStatus(long orderId,String status) {
		Order order=entityManager.find(Order.class, orderId);
		if(order.getOrderStatus().equals("Returned") || order.getOrderStatus().equals("Cancelled")) {
			return 0;
		}
		if(status.equals("Returned") && order.getTransaction().getCoupon()!=null) {
			return -1;
		}
		if(status.equals("Cancelled") && order.getOrderStatus().equals("Delivered")) {
			return 2;
		}
		
		order.setOrderStatus(status);
		if(status.equals("Returned") || status.equals("Cancelled")) {
			order.getCustomer().setBalance(order.getCustomer().getBalance()+order.getOrderAmount());
		}
		entityManager.merge(order);
		return 1;
=======
	@Override
	public MerchantDetails findMerchantByUsername(String username) {
		MerchantDetails merchant = entityManager.find(MerchantDetails.class,username);
		return merchant;
	}

	@Override
	public void redirectFeedback(CommonFeedback feedback) {
		feedback.setEnableRead(true);
		entityManager.merge(feedback);		
	}
	
	public List<Order> getOrders() {
		String str="SELECT allOrders FROM Order allOrders";
		TypedQuery<Order> query=entityManager.createQuery(str,Order.class);
		List<Order> orders=query.getResultList();
		return orders;
	}

	@Override
	public boolean updateStatus(long orderId,String status) {
		Order order=entityManager.find(Order.class, orderId);
		order.setOrderStatus(status);
		entityManager.merge(order);
		return true;
>>>>>>> fddfae32b28f09e8652e0626c3c08200ed47d480
	}
}
