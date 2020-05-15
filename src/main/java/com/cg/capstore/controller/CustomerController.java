package com.cg.capstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.capstore.entities.CustomerDetails;
import com.cg.capstore.service.CouponService;
import com.cg.capstore.service.CustomerService;

@RestController
@CrossOrigin("*")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/helloCust")
	public ResponseEntity<Object> checkWorking(){
		return new ResponseEntity<Object>("Hello Customer..", HttpStatus.OK);
	}
	
	@GetMapping("/countOfCustomers")
	public ResponseEntity<Long> countOfCustomers() throws Exception{
		return new ResponseEntity<Long>(customerService.countOfCustomers(), HttpStatus.OK);
	}

}
