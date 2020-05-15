package com.cg.capstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.capstore.entities.MerchantDetails;
import com.cg.capstore.service.IMerchantService;


@RestController
@CrossOrigin("*")
public class MerchantController {
	
	@Autowired

	private IMerchantService merchantService;

	
	@GetMapping("/countOfMerchants")
	public ResponseEntity<Long> countOfMerchants() throws Exception{
		return new ResponseEntity<Long>(merchantService.countOfMerchants(), HttpStatus.OK);
	}
	
	@GetMapping("/merchants/all")
	public ResponseEntity<List<MerchantDetails>> getMerchants() throws Exception {
		List<MerchantDetails> merchants = merchantService.getMerchants();
		return new ResponseEntity<List<MerchantDetails>>(merchants,HttpStatus.OK);
	}
	
}
