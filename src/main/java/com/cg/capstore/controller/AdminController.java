package com.cg.capstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.capstore.entities.CustomerDetails;
import com.cg.capstore.entities.Invitation;
import com.cg.capstore.response.SuccessMessage;
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
}
