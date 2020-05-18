package com.cg.capstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.capstore.response.SuccessMessage;
import com.cg.capstore.service.IProductService;

@RestController
@CrossOrigin("*")
public class ProductController {
	
	@Autowired
	private IProductService productService;

	@PostMapping(value="/admin/activateProduct",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessMessage> markProductAsActive(@RequestParam("username") String username, @RequestParam("id") Integer id) throws Exception {
		productService.activateProduct(id);
		return new ResponseEntity<SuccessMessage>(new SuccessMessage("Product Activation ","Activated Product " +  id + "Successfully "),HttpStatus.OK);	
	}
	
	@PostMapping(value="/admin/inActivateProduct",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessMessage> markProductAsInActive(@RequestParam("username") String username, @RequestParam("id") Integer id) throws Exception {
		productService.deActivateProduct(id);
		return new ResponseEntity<SuccessMessage>(new SuccessMessage("Product In Activation ","In Activated Product " +  id + "Successfully "),HttpStatus.OK);	
	}
}
