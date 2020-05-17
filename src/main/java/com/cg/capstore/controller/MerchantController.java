package com.cg.capstore.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cg.capstore.entities.MerchantDetails;
import com.cg.capstore.entities.Order;
import com.cg.capstore.service.IMerchantService;

@RestController
@CrossOrigin("*")
public class MerchantController {
	
	@Autowired

	private IMerchantService merchantService;
	
	@GetMapping("/merchants/all")
	public ResponseEntity<List<MerchantDetails>> getMerchants() throws Exception {
		List<MerchantDetails> merchants = merchantService.getMerchants();
		return new ResponseEntity<List<MerchantDetails>>(merchants,HttpStatus.OK);
	}
	
	@GetMapping("/merchantInfo/{username}")
	public ResponseEntity<MerchantDetails> getMerchantInfo(@PathVariable String username){
		return new ResponseEntity<MerchantDetails>(merchantService.getMerchantInfo(username), HttpStatus.OK);
	}
	
	
	@GetMapping("/merchantOrders/{username}")
	public ResponseEntity<Set<Order>> getMerchantOrders(@PathVariable String username){
		return new ResponseEntity<Set<Order>> (merchantService.getMerchantOrders(username), HttpStatus.OK);
	}
	
	@GetMapping("/acceptMerchantOrder/{orderId}/{status}")
	public ResponseEntity<Order> acceptMerchantOrder(@PathVariable long orderId,@PathVariable String status){
		return new ResponseEntity<Order> (merchantService.acceptMerchantOrder(orderId, status), HttpStatus.OK);
	}
}
