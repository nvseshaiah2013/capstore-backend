package com.cg.capstore.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.capstore.entities.Invitation;
import com.cg.capstore.entities.MerchantDetails;
import com.cg.capstore.entities.Order;
import com.cg.capstore.response.SuccessMessage;
import com.cg.capstore.entities.Product;
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

	
	@PostMapping(value="/merchant/activate",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessMessage> activateMerchant(@RequestParam("username") String username ) throws Exception {
		this.merchantService.activateMerchant(username);
		return new ResponseEntity<SuccessMessage>(new SuccessMessage("Merchant Activation","Merchant " + username + " Activated Successfully"),HttpStatus.OK);
	}
	
	@PostMapping(value="/merchant/deactivate",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessMessage> deActivateMerchant(@RequestParam("username") String username ) throws Exception {
		this.merchantService.deActivateMerchant(username);
		return new ResponseEntity<SuccessMessage>(new SuccessMessage("Merchant De-Activation","Merchant " + username + " De-Activated Successfully"),HttpStatus.OK);
	}

	
	@GetMapping("/getMerchantProducts/{username}")
	public ResponseEntity<Set<Product>> getMerchantProducts(@PathVariable String username){
		return new ResponseEntity<Set<Product>> (merchantService.getMerchantProducts(username), HttpStatus.OK);
	}
	
	@GetMapping(value="/merchant/invites",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<Invitation>> getInvites(@RequestParam("username") String username) throws Exception {
		return new ResponseEntity<Set<Invitation>>(merchantService.getInvites(username),HttpStatus.OK);
	}

}
