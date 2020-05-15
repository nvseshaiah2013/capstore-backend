package com.cg.capstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.capstore.entities.Address;
import com.cg.capstore.entities.Category;
import com.cg.capstore.entities.CustomerDetails;
import com.cg.capstore.entities.SubCategory;
import com.cg.capstore.service.IAdminService;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private IAdminService adminService;
	
	@GetMapping("/orders")
	public String orderStatus() {
		return "Working fine";
	}
	@GetMapping(value="/customer",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CustomerDetails>> getAllCustomers()
	{
		List<CustomerDetails> customers=adminService.getCustomerList();
		return new ResponseEntity<List<CustomerDetails>>(customers,HttpStatus.OK);
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
	@DeleteMapping(value="/category/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Category>> deleteCategoryById(@PathVariable int id)
	{
		List<Category> categories=adminService.deleteCategory(id);
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
	@DeleteMapping(value="/subCategory/{categoryId}/{subCategoryId}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SubCategory>> deleteSubCategoryById(@PathVariable int categoryId,@PathVariable int subCategoryId)
	{
		List<SubCategory> subCategory=adminService.deleteSubCategory(categoryId,subCategoryId);
		return new ResponseEntity<List<SubCategory>>(subCategory,HttpStatus.OK);
	}
	
}
