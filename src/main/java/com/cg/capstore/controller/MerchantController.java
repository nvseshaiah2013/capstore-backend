package com.cg.capstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.capstore.service.CustomerService;
import com.cg.capstore.service.MerchantService;

@RestController
@CrossOrigin("*")
public class MerchantController {
	
	@Autowired
	private MerchantService merchantService;
	
	@GetMapping("/countOfMerchants")
	public ResponseEntity<Long> countOfMerchants() throws Exception{
		return new ResponseEntity<Long>(merchantService.countOfMerchants(), HttpStatus.OK);
	}
	
}
