package com.cg.capstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.capstore.entities.Address;
import com.cg.capstore.entities.Category;
import com.cg.capstore.entities.CustomerDetails;
import com.cg.capstore.entities.Invitation;
import com.cg.capstore.entities.Order;
import com.cg.capstore.response.SuccessMessage;
import com.cg.capstore.response.ThirdPartyMerchantDetails;
import com.cg.capstore.entities.SubCategory;
import com.cg.capstore.exceptions.InvalidAttributeException;
import com.cg.capstore.service.IAdminService;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private IAdminService adminService;
	
	@GetMapping(value="/orders",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Order>> getAllOrders() {
		List<Order> orders=adminService.getOrders();
		return new ResponseEntity<List<Order>>(orders,HttpStatus.OK);
	}
	
	@GetMapping(value="/customer",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CustomerDetails>> getAllCustomers()
	{
		List<CustomerDetails> customers=adminService.getCustomerList();
		return new ResponseEntity<List<CustomerDetails>>(customers,HttpStatus.OK);
	}

	@GetMapping(value="/invites",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Invitation>> getInvites(){
		List<Invitation> invites = adminService.getInvites();
		return new ResponseEntity<List<Invitation>>(invites,HttpStatus.OK);
	}
	
	@PostMapping(value="/invite",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessMessage> sendInvite(@RequestBody Invitation invitation ) throws Exception{
		adminService.sendInvite(invitation);
		return new ResponseEntity<SuccessMessage>(new SuccessMessage("Invitation Request","Invitation Successfully Sent"),HttpStatus.OK);
	}

	@GetMapping(value="/address/{username}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Address>> getAddressByUsername(@PathVariable String username)
	{
		List<Address> addresses=adminService.getAddressByUsername(username);
		return new ResponseEntity<List<Address>>(addresses,HttpStatus.OK);
	}
	
	@PostMapping(value="/category",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Category>> addCategory(@RequestBody Category category)
	{
		List<Category> categories=adminService.addCategory(category);
		return new ResponseEntity<List<Category>>(categories,HttpStatus.CREATED);
	}
	@GetMapping(value="/category",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Category>> getAllCategory()
	{
		List<Category> categories=adminService.getAllCategory();
		return new ResponseEntity<List<Category>>(categories,HttpStatus.OK);
	}
		
	@PostMapping(value="/subCategory/{id}",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SubCategory>> addCategory(@RequestBody SubCategory subCategory,@PathVariable int id)
	{
		
		List<SubCategory> subCategories=adminService.addSubCategory(subCategory,id);
		return new ResponseEntity<List<SubCategory>>(subCategories,HttpStatus.CREATED);
	}
	@GetMapping(value="/subCategory/{categoryId}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SubCategory>> getAllSubCategory(@PathVariable int categoryId)
	{
		List<SubCategory> subCategories=adminService.getAllSubCategory(categoryId);
		return new ResponseEntity<List<SubCategory>>(subCategories,HttpStatus.OK);
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
	@PatchMapping("/minOrderValue/{amount}")
	public ResponseEntity<Integer> setMinOrderValueAmount(@PathVariable int amount)
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
		adminService.updateStatus(orderId, status);
		return new ResponseEntity<>("Updated..!!",HttpStatus.OK);
	}
}
