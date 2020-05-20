package com.cg.capstore.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.capstore.dao.IAdminDao;
import com.cg.capstore.entities.Address;
import com.cg.capstore.entities.Category;
import com.cg.capstore.entities.CommonFeedback;
import com.cg.capstore.entities.CustomerDetails;
import com.cg.capstore.entities.Invitation;
import com.cg.capstore.entities.MerchantDetails;
import com.cg.capstore.entities.Order;
import com.cg.capstore.entities.Product;
import com.cg.capstore.entities.SubCategory;
import com.cg.capstore.exceptions.InvalidAttributeException;
import com.cg.capstore.exceptions.NotAvailableException;
import com.cg.capstore.response.ThirdPartyMerchantDetails;

@Service
@Transactional
public class AdminServiceImpl implements IAdminService {

	@Autowired
	private IAdminDao adminDao;
	
	private Logger logger = Logger.getLogger(getClass());

	@Override
	public List<CustomerDetails> getCustomerList() throws  NotAvailableException {
		List<CustomerDetails> list= adminDao.getCustomerList();
		if(list.size()==0) {
			logger.error("No Customer's availble");
			throw new NotAvailableException("No Customer's available");
		}
		else
		{
			logger.info("Request for all customer details");
			return list;
		}
			
	}

	@Override
	public List<Invitation> getInvites(){
		return adminDao.getInvites();
	}

	@Override
	public List<Address> getAddressByUsername(String username) throws InvalidAttributeException, NotAvailableException {
		if(!adminDao.checkValidEmail(username)) {
		List<Address>addresses= adminDao.getAddressByUsername(username);
		if(addresses.isEmpty()) {
			logger.info("No address available ");
			throw new NotAvailableException("No addresses available");
		}
		else {
			logger.info("Request for address of "+username);
			return addresses;
		}
		}
		else {
			logger.error("Invalid username");
			throw new InvalidAttributeException("Username doesn't exist");
		}
	}

	@Override
	public List<Category> addCategory(Category category) {
		List<Category> categories=adminDao.addCategory(category);
		logger.info(category.getName()+" category added");
		return categories;
	}

	@Override
	public List<Category> getAllCategory() throws  NotAvailableException {

		List<Category> categories= adminDao.getAllCategory();
		if(categories.isEmpty()) {
			logger.warn("No categories available");
			throw new NotAvailableException("No categories available");
		}
		else
			{
			logger.info("Request for all category");
			return categories;
			}
	}



	@Override
	public List<SubCategory> getAllSubCategory(int categoryId) throws InvalidAttributeException {
		
		
		
		List<SubCategory> subCategories= adminDao.getAllSubCategory(categoryId);
		if(subCategories.isEmpty()) {
			logger.warn("No subcategories available");
			throw new InvalidAttributeException("No subcategories available");
		}
		else {
			logger.info("Request for all subcategories of "+categoryId);
			return subCategories;
		}
	}


	@Override
	public List<SubCategory> addSubCategory(SubCategory subCategory, int categoryId) {
		List<SubCategory> subCategories=adminDao.addSubCategory(subCategory, categoryId);
		logger.info(subCategory.getName()+" subcategory Added");
		return subCategories;
	}
	
	@Override
	public void sendInvite(Invitation invitation) throws Exception {
		adminDao.sendInvite(invitation);
	}

	@Override
	public Long countOfCustomers() throws Exception {
		if(adminDao.countOfCustomers()!=null) {
			logger.info("Customer's Count returned");
			return adminDao.countOfCustomers();
		}
		else {
			logger.error("No Customers Found");
			throw new Exception("0");
		}
	}
	
	@Override
	public Long countOfMerchants() throws Exception {
		if(adminDao.countOfMerchants()!=null) {
			logger.info("Merchant's Count returned");
			return adminDao.countOfMerchants();
		}
		else {
			logger.error("No Merchants Found");
			throw new Exception("0");
		}
	}

	@Override
	public List<MerchantDetails> topRatedMerchants() throws Exception {
		
		if(adminDao.topRatedMerchants()!=null) {
			logger.info("Top Rated Merchants returned");
			return adminDao.topRatedMerchants();
		}
		else {
			logger.error("No Top Rated Merchants Found");
			throw new Exception("No Top Rated Merchants Found");
		}
	}
	
	@Override
	public boolean addMerchant(ThirdPartyMerchantDetails details) {
		boolean status=adminDao.addMerchant(details);
		if(status) {
		logger.info(details.getName()+" merchant's detail added");
		return true;
		}
		else
		{
			logger.error("Merchant not added");
			return false;
		}
	}

	@Override
	public boolean checkValidPhoneNumber(String phoneNo) throws InvalidAttributeException {
		if(adminDao.checkValidPhoneNumber(phoneNo)) {
			logger.info("Phone Number "+phoneNo+" validation request");
			return true;
		}
		else {
			logger.warn(phoneNo+" Phone Number Already Exists");
			throw new InvalidAttributeException("Phone Number Already Exists");
		}
	}

	@Override
	public boolean checkValidEmail(String email) throws InvalidAttributeException {
		if(adminDao.checkValidEmail(email)) {
			logger.info("Email "+email+" validation request");
			return true;
		}
		else {
			logger.warn(email+" Email Already Exists");
			throw new InvalidAttributeException("Email Already Exists");
		}
	}

	@Override
	public int setMinOrderValueAmount(int amount) throws InvalidAttributeException {
		if(amount<1) {
			logger.warn("Cannot set 0 or less than that minimum order value amount");
			throw new InvalidAttributeException("Cannot set 0 or less than that");
		}
		else if(amount<100) {
			logger.warn("Cannot set min order value less than 100");
			throw new InvalidAttributeException("Capstore policy violated..!!! Cannot set min order value less than 100");
		}
		else {
			int updatedAmount=adminDao.setMinOrderValueAmount(amount);
			logger.info("Minimum order value amount updated");
			return updatedAmount;
		}
	}

	@Override
	public int getMinOrderValueAmount() {
		logger.info("Request for minimum order value amount");
		return  adminDao.getMinOrderValueAmount();
	}
	
	
	public List<Order> getOrders() throws Exception{
		List<Order> orders=adminDao.getOrders();
		if(orders==null) {
			logger.error("No orders found..!!");
			throw new Exception("No orders found in the database..!!");
		}
		logger.info("Fetched all orders..!!");
		return orders;
	}

	@Override
	public int updateStatus(long orderId, String status) {
		int response=adminDao.updateStatus(orderId, status);
		
		if(response==1) {
			logger.info("Order updated..!!");
		}
		else {
			logger.error("Error while updating the order..!!");
		}
		return response;
	}

	@Override
	public List<CommonFeedback> getFeedbacks() {
		return this.adminDao.getFeedbacks();
	}
	
	@Override
	public CommonFeedback redirectFeedback(int id) throws Exception {
		CommonFeedback feedback = findCommonFeedbackById(id) ;
		this.adminDao.redirectFeedback(feedback);
		return feedback;
	}
	
	@Override
	public CommonFeedback findCommonFeedbackById(int id) throws Exception {
		CommonFeedback feedback = this.adminDao.findCommonFeedbackById(id);
		if(feedback==null) {
			throw new Exception("Common Feedback Not Found For id: " + id);
		}
		return feedback;
	}
	
	@Override
	public MerchantDetails findMerchantByUsername(String username) throws Exception {
		MerchantDetails merchant = this.adminDao.findMerchantByUsername(username);
		if(merchant == null)
		{
			throw new Exception("Merchant With username : " + username + " not found!");
		}
		return merchant;
	}
	
	@Override
	public Integer getOrderCount(String username) throws Exception {
		MerchantDetails merchant = findMerchantByUsername(username);
		return merchant.getOrders().size();
	}
	
	@Override
	public Integer getProductCount(String username) throws Exception {
		MerchantDetails merchant = findMerchantByUsername(username);
		return merchant.getProducts().size();
	}
	
	@Override
	public Integer getMerchantRating(String username) throws Exception {
		MerchantDetails merchant = findMerchantByUsername(username);
		return merchant.getRating();
	}
	
	@Override 
	public Set<CommonFeedback> getCommonFeedbackByMerchant(String username) throws Exception {
		MerchantDetails merchant = findMerchantByUsername(username);
		return merchant.getFeedbacks();
	}
	
	@Override 
	public Set<Order> getOrdersByMerchant(String username) throws Exception {
		MerchantDetails merchant = findMerchantByUsername(username);
		return merchant.getOrders();
	}
	
	@Override 
	public Set<Product> getProductsByMerchant(String username) throws Exception {
		MerchantDetails merchant = findMerchantByUsername(username);
		return merchant.getProducts();
	}
	
	@Override 
	public Set<CommonFeedback> getFeedbacksByMerchant(String username) throws Exception {
		MerchantDetails merchant = findMerchantByUsername(username);
		return merchant.getFeedbacks();
	}

	@Override
	public List<Category> updateCategory(Category category) {
		logger.info(category.getName()+" Category updated");
		return adminDao.updateCategory(category);
}

	@Override
	public List<Product> getTrendingProducts() throws Exception {

		if(adminDao.getTrendingProducts()!=null) {
			logger.info("Trending Products returned");
			return adminDao.getTrendingProducts();
		}
		else {
			logger.error("No Trending Products Found");
			throw new Exception("No Trending Products Found");
		}
	}

	@Override
	public Double todayRevenue() throws Exception {

		if(adminDao.todayRevenue()!=null) {
			logger.info("Today's Revenues returned");
			return adminDao.todayRevenue();
		}
		else
		{
			logger.error("No Revenue for today");
			throw new Exception("No Revenue for today");
		}
	}
	
	@Override
	public Long todayProductSales() throws Exception {
	
		if(adminDao.todayProductSales()!=null) {
			logger.info("Today's Product Sales returned");
			return adminDao.todayProductSales();
		}
		else {
			logger.error("No Products Sold today");
			throw new Exception("No Products Sold today");
		}
	}

	@Override
	public List<Order> recentOrders() throws Exception {
	
		if(adminDao.recentOrders()!=null) {
			logger.info("Recent Orders returned");
			return adminDao.recentOrders();
		}
		else {
			logger.error("No Recent Orders Found");
			throw new Exception("No Recent Orders Found");
		}
	}

	@Override
	public List<Double> recentRevenues() throws Exception {
		
		if(adminDao.recentRevenues()!=null) {
			logger.info("Last 7 Days revenues returned");
			return adminDao.recentRevenues();
		}
		else {
			logger.error("No Revenues in last 7 Days");
			throw new Exception("No Revenues in last 7 Days");
		}
	}

	@Override
	public List<Long> recentOrdersCount() throws Exception {
		
		if(adminDao.recentOrdersCount()!=null) {
			logger.info("Last 7 Days orders returned");
			return adminDao.recentOrdersCount();
		}
		else {
			logger.error("No Orders in last 7 Days");
			throw new Exception("No Orders in last 7 Days");
		}
	}

	@Override
	public Product getProductById(int prodId) throws Exception {
		
		if(adminDao.getProductById(prodId)!=null) {
			logger.info("Product Data Returned");
			return adminDao.getProductById(prodId);
		}
		else {
			logger.error("Product Not Found");
			throw new Exception("Product Not Found");
		}
	}

	@Override
	public List<Address> deleteAddressByAddressId(int addressId,String username) {
		logger.warn("Delete address of "+username);
		return adminDao.deleteAddressByAddressId(addressId,username);
	}

	@Override
	public void activateProduct(int prodId) throws Exception {
		Product product = getProductById(prodId);
		adminDao.activateProduct(product);
	}

	@Override
	public void inActivateProduct(int prodId) throws Exception {
		Product product = getProductById(prodId);
		adminDao.inActivateProduct(product);

	}

	@Override
	public boolean checkCategoryExists(String categoryName) throws InvalidAttributeException {
		if(adminDao.checkCategoryExists(categoryName))
		{logger.info("Check if exist category "+categoryName);
		return true;
		}
		else {
			logger.info(categoryName+" Category or Subcategory already exist");
			throw new InvalidAttributeException("Category or Subcategory already exist");
		}
		
	}
}
