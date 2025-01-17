package com.cg.capstore.controller;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.capstore.entities.Address;
import com.cg.capstore.entities.Category;
import com.cg.capstore.entities.CommonFeedback;
import com.cg.capstore.entities.CustomerDetails;
import com.cg.capstore.entities.Invitation;
import com.cg.capstore.entities.MerchantDetails;
import com.cg.capstore.entities.Order;
import com.cg.capstore.entities.Product;
import com.cg.capstore.response.ErrorMessage;
import com.cg.capstore.response.SuccessMessage;
import com.cg.capstore.response.ThirdPartyMerchantDetails;
import com.cg.capstore.entities.SubCategory;
import com.cg.capstore.exceptions.InvalidAttributeException;
import com.cg.capstore.exceptions.NotAvailableException;
import com.cg.capstore.service.IAdminService;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private IAdminService adminService;
	
	
	@GetMapping(value="/orders",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Order>> getAllOrders() throws Exception{
		List<Order> orders=adminService.getOrders();
		return new ResponseEntity<List<Order>>(orders,HttpStatus.OK);
	}
	
	@GetMapping(value="/customer",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CustomerDetails>> getAllCustomers() throws NotAvailableException
	{
		List<CustomerDetails> customers=adminService.getCustomerList();
		return new ResponseEntity<List<CustomerDetails>>(customers,HttpStatus.OK);
	}

	/**
	 * Function to get a list of invites sent by the admin
	 * REturns a list of invites
	 * 
	 * 
	 */

	@GetMapping(value="/invites",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Invitation>> getInvites(){
		List<Invitation> invites = adminService.getInvites();
		return new ResponseEntity<List<Invitation>>(invites,HttpStatus.OK);
	}

	/*
		Function to send an invite to merchant
		On Success it sends an invite to merchant
	*/
	
	@PostMapping(value="/invite",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessMessage> sendInvite(@RequestBody Invitation invitation ) throws Exception{
		adminService.sendInvite(invitation);
		return new ResponseEntity<SuccessMessage>(new SuccessMessage("Invitation Request","Invitation Successfully Sent"),HttpStatus.OK);
	}

	@GetMapping(value="/address/{username}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Address>> getAddressByUsername(@PathVariable String username) throws InvalidAttributeException, NotAvailableException
	{
		List<Address> addresses=adminService.getAddressByUsername(username);
		return new ResponseEntity<List<Address>>(addresses,HttpStatus.OK);
	}
	
	@DeleteMapping(value="/address/{addressId}/{username}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Address>> deleteAddressByAddressId(@PathVariable int addressId,@PathVariable String username)
	{
		List<Address> addresses=adminService.deleteAddressByAddressId(addressId,username);
		return new ResponseEntity<List<Address>>(addresses,HttpStatus.OK);
	}
	
	@PostMapping(value="/category",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Category>> addCategory(@RequestBody Category category) throws InvalidAttributeException
	{
		List<Category> categories=adminService.addCategory(category);
		
		return new ResponseEntity<List<Category>>(categories,HttpStatus.CREATED);
	}
	@GetMapping(value="/category",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Category>> getAllCategory() throws NotAvailableException
	{
		List<Category> categories=adminService.getAllCategory();
		return new ResponseEntity<List<Category>>(categories,HttpStatus.OK);
	}
	@PutMapping(value="/category",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Category>> updateCategory(@RequestBody Category category)
	{
		List<Category> categories=adminService.updateCategory(category);
		return new ResponseEntity<List<Category>>(categories,HttpStatus.OK);
	}
	@GetMapping(value="/checkCategoryExist/{categoryName}")
	public ResponseEntity<SuccessMessage> checkCategoryExists(@PathVariable String categoryName) throws InvalidAttributeException
	{
		adminService.checkCategoryExists(categoryName);
		return new ResponseEntity<SuccessMessage>(new SuccessMessage("Category Validation Request","Category Doesn't Exist"),HttpStatus.OK);
		
	}
	@PostMapping(value="/subCategory/{id}",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SubCategory>> addSubCategory(@RequestBody SubCategory subCategory,@PathVariable int id)
	{
		List<SubCategory> subCategories=adminService.addSubCategory(subCategory,id);
		
		return new ResponseEntity<List<SubCategory>>(subCategories,HttpStatus.CREATED);
	}
	@GetMapping(value="/subCategory/{categoryId}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SubCategory>> getAllSubCategory(@PathVariable int categoryId) throws InvalidAttributeException
	{
		List<SubCategory> subCategories=adminService.getAllSubCategory(categoryId);
		return new ResponseEntity<List<SubCategory>>(subCategories,HttpStatus.OK);
	}

	@GetMapping("/countOfCustomers")
	public ResponseEntity<Long> countOfCustomers() throws Exception{
		return new ResponseEntity<Long>(adminService.countOfCustomers(), HttpStatus.OK);
	}
	
	@GetMapping("/countOfMerchants")
	public ResponseEntity<Long> countOfMerchants() throws Exception{
		return new ResponseEntity<Long>(adminService.countOfMerchants(), HttpStatus.OK);
	}
	
	@GetMapping("/topRatedMerchants")
	public ResponseEntity<List<MerchantDetails>> topRatedMerchants() throws Exception{
		return new ResponseEntity<List<MerchantDetails>>(adminService.topRatedMerchants(), HttpStatus.OK);
	}

	/**
	 * FUnction to get a list of Common Feedbacks received by merchant
	 * On Success returns a list of common feedbacks
	 * throws merchant not found with merchant username 
	 * 
	 */

	
	@GetMapping(value="/commonFeedbacks",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CommonFeedback>> getFeedbacks(){
		List<CommonFeedback> feedbacks = this.adminService.getFeedbacks();
		return new ResponseEntity<List<CommonFeedback>>(feedbacks,HttpStatus.OK);
	}

	/*
		Function To Redirect the feedbacks to merchant
		On Success redirects the feedback to merchant identified by his username
		Throws Feedback Id Not Found Exception
	*/
	
	@PostMapping(value="/redirectFeedback/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessMessage> redirectFeedback(@PathVariable int id) throws Exception
	{
		CommonFeedback feedback = this.adminService.redirectFeedback(id);
		return new ResponseEntity<SuccessMessage>(new SuccessMessage("Feedback Redirection","Feedback Redirected To Merchant " + feedback.getMerchant().getUsername()),HttpStatus.OK);
	}
	
	@GetMapping(value="/productCount",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> getProductCount(@RequestParam("username") String username) throws Exception {
		return new ResponseEntity<Integer>(this.adminService.getProductCount(username),HttpStatus.OK);
	}
	

	@GetMapping(value="/orderCount",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> getOrderCount(@RequestParam("username") String username) throws Exception {
		return new ResponseEntity<Integer>(this.adminService.getOrderCount(username),HttpStatus.OK);
	}

	/*
		Function to Get the list of all orders of merchant identified by his username
		On Success Returns a List of ORders
		Throw Merchant with usrname not Found Exception
	*/
	
	@GetMapping(value="/orders/all",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<Order>> getOrdersByMerchantUsername(@RequestParam("username") String username) throws Exception{
		return new ResponseEntity<Set<Order>>(this.adminService.getOrdersByMerchant(username),HttpStatus.OK);
	}

	/*
		Function Get The list of all products of merchant by identifying his username
		On Success Returns a List of products
		Throws Merchant with username not Found Exception
	*/
	
	@GetMapping(value="products/all",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<Product>> getProductsByMerchantUsername(@RequestParam("username") String username ) throws Exception {
		return new ResponseEntity<Set<Product>>(this.adminService.getProductsByMerchant(username),HttpStatus.OK);
	}
	
	@GetMapping(value="feedbacks/all",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<CommonFeedback>> getFeedbacksByMerchantUsername(@RequestParam("username") String username ) throws Exception {
		return new ResponseEntity<Set<CommonFeedback>>(this.adminService.getFeedbacksByMerchant(username),HttpStatus.OK);
	}	
		
	@PostMapping(value="/addMerchant",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessMessage> addMerchant(@RequestBody ThirdPartyMerchantDetails details)
	{
		adminService.addMerchant(details);
		return new ResponseEntity<SuccessMessage>(new SuccessMessage("Add Merchant Request","Merchant Successfully Registered"),HttpStatus.CREATED);
	}
	
	@GetMapping(value="/checkPhoneNo/{phoneNo}")
	public ResponseEntity<SuccessMessage> checkValidPhoneNo(@PathVariable String phoneNo) throws InvalidAttributeException
	{
		adminService.checkValidPhoneNumber(phoneNo);

		return new ResponseEntity<SuccessMessage>(new SuccessMessage("PhoneNo Validation Request","PhoneNo Doesn't Exist"),HttpStatus.OK);
	}
	@GetMapping(value="/checkEmail/{email}")
	public ResponseEntity<SuccessMessage> checkValidEmail(@PathVariable String email) throws InvalidAttributeException
	{
		adminService.checkValidEmail(email);
		return new ResponseEntity<SuccessMessage>(new SuccessMessage("Email Validation Request","Email Doesn't Exist"),HttpStatus.OK);
	}
	@GetMapping("/minOrderValue/{amount}")
	public ResponseEntity<Integer> setMinOrderValueAmount(@PathVariable int amount) throws InvalidAttributeException
	{
		int updatedAmount=adminService.setMinOrderValueAmount(amount);

		return new ResponseEntity<Integer>(updatedAmount,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/minOrderValue")
	public ResponseEntity<Integer> getMinOrderValueAmount()
	{
		int amount=adminService.getMinOrderValueAmount();
		return new ResponseEntity<Integer>(amount,HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value="/orders/{orderId}/{status}")
	public ResponseEntity<Object> updateStatus(@PathVariable long orderId,@PathVariable String status)
	{
		int result=adminService.updateStatus(orderId, status);
		if(result==0) {
			return new ResponseEntity<>(new ErrorMessage(HttpStatus.BAD_REQUEST,"This order is not accepted by Merchant yet !!"),HttpStatus.BAD_REQUEST);
		}
		if(result==2) {
			return new ResponseEntity<>(new ErrorMessage(HttpStatus.BAD_REQUEST,"This order can only be returned as already delivered !!"),HttpStatus.BAD_REQUEST);
		}
		if(result==3) {
			return new ResponseEntity<>(new ErrorMessage(HttpStatus.BAD_REQUEST,"Return is not requested !!"),HttpStatus.BAD_REQUEST);
		}
		if(result==4) {
			return new ResponseEntity<>(new ErrorMessage(HttpStatus.BAD_REQUEST,"Can't be Returned due to Applied Coupons !!"),HttpStatus.BAD_REQUEST);
		}
		if(result==5) {
			return new ResponseEntity<>(new ErrorMessage(HttpStatus.BAD_REQUEST,"Cancellation is not requested !!"),HttpStatus.BAD_REQUEST);
		}
		if(result==6) {
			return new ResponseEntity<>(new ErrorMessage(HttpStatus.BAD_REQUEST,"Order Already Cancelled/Returned !!"),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new SuccessMessage("Order Status","Order Updated..!!"),HttpStatus.OK);
	}
	
	@GetMapping(value="/trendingProducts")
	public ResponseEntity<List<Product>> getTrendingProducts() throws Exception
	{
		return new ResponseEntity<List<Product>>(adminService.getTrendingProducts(),HttpStatus.OK);
	}
	
	@GetMapping(value="/todayRevenue")
	public ResponseEntity<Double> todayRevenue() throws Exception
	{
		return new ResponseEntity<Double>(adminService.todayRevenue(),HttpStatus.OK);
	}
	
	@GetMapping(value="/todayProductSales")
	public ResponseEntity<Long> todayProductSales() throws Exception
	{
		return new ResponseEntity<Long>(adminService.todayProductSales(),HttpStatus.OK);
	}
	
	@GetMapping(value="/recentOrders") //recent 3 orders - Dashboard
	public ResponseEntity<List<Order>> recentOrders() throws Exception
	{
		return new ResponseEntity<List<Order>>(adminService.recentOrders(),HttpStatus.OK);
	}
	
	@GetMapping(value="/recentRevenues") //Recent 7 days revenues - Statistics
	public ResponseEntity<List<Double>> recentRevenues() throws Exception
	{
		return new ResponseEntity<List<Double>>(adminService.recentRevenues(),HttpStatus.OK);
	}
	
	@GetMapping(value="/recentOrdersCount") //Recent 7 days orders count - Statistics
	public ResponseEntity<List<Long>> recentOrdersCount() throws Exception
	{
		return new ResponseEntity<List<Long>>(adminService.recentOrdersCount(),HttpStatus.OK);
	}
	
	@GetMapping(value="/getProduct/{prodId}")
	public ResponseEntity<Product> getproductById(@PathVariable int prodId) throws Exception
	{
		return new ResponseEntity<Product>(adminService.getProductById(prodId),HttpStatus.OK);
	}
	
	@GetMapping(value="/getMerchant/{username}")
	public ResponseEntity<MerchantDetails> findMerchantByUsername(@PathVariable String username) throws Exception
	{
		return new ResponseEntity<MerchantDetails>(adminService.findMerchantByUsername(username),HttpStatus.OK);
	}

	/*
	 * Function To Dectivate a Product 
	 * On Success Activates the Product with product
	 * Id Throws Exception Product with Product Id Not Found
	 */


	@PostMapping(value = "/activateProduct", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessMessage> markProductAsActive(@RequestParam("username") String username,
			@RequestParam("id") Integer id) throws Exception {
		adminService.activateProduct(id);
		return new ResponseEntity<SuccessMessage>(
				new SuccessMessage("Product Activation ", "Activated Product " + id + " Successfully "), HttpStatus.OK);
	}

	/*
		Function To Activate a Product
		On Success Activates the Product with product Id
		Throws Exception Product with Product Id Not Found
	*/

	@PostMapping(value = "/inActivateProduct", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessMessage> markProductAsInActive(@RequestParam("username") String username,
			@RequestParam("id") Integer id) throws Exception {
		adminService.inActivateProduct(id);
		return new ResponseEntity<SuccessMessage>(
				new SuccessMessage("Product In Activation ", "In Activated Product " + id + " Successfully "),
				HttpStatus.OK);
	}

}
