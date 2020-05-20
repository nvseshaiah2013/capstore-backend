package com.cg.capstore.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

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
import com.cg.capstore.util.JwtUtil;

@RestController
@CrossOrigin("*")
public class MerchantController {
	
	@Autowired

	private IMerchantService merchantService;

	@Autowired
	private JwtUtil jwtUtil;
	
	@GetMapping("/admin/merchants/all")
	public ResponseEntity<List<MerchantDetails>> getMerchants() throws Exception {
		List<MerchantDetails> merchants = merchantService.getMerchants();
		return new ResponseEntity<List<MerchantDetails>>(merchants,HttpStatus.OK);
	}
	
	@GetMapping("/merchant/merchantInfo")
	public ResponseEntity<MerchantDetails> getMerchantInfo(HttpServletRequest request) throws Exception{
		String username = getUsernameOfMerchant(request);
		return new ResponseEntity<MerchantDetails>(merchantService.getMerchantInfo(username), HttpStatus.OK);
	}
	
	
	@GetMapping("/merchant/merchantOrders")
	public ResponseEntity<Set<Order>> getMerchantOrders(HttpServletRequest request) throws Exception{
		String username = getUsernameOfMerchant(request);
		return new ResponseEntity<Set<Order>> (merchantService.getMerchantOrders(username), HttpStatus.OK);
	}
	
	@GetMapping("/merchant/acceptMerchantOrder/{orderId}/{status}")
	public ResponseEntity<Order> acceptMerchantOrder(@PathVariable long orderId,@PathVariable String status){
		return new ResponseEntity<Order> (merchantService.acceptMerchantOrder(orderId, status), HttpStatus.OK);
	}

	
	@PostMapping(value="/admin/merchant/activate",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessMessage> activateMerchant(@RequestParam("username") String username ) throws Exception {
		this.merchantService.activateMerchant(username);
		return new ResponseEntity<SuccessMessage>(new SuccessMessage("Merchant Activation","Merchant " + username + " Activated Successfully"),HttpStatus.OK);
	}
	
	@PostMapping(value="/admin/merchant/deactivate",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessMessage> deActivateMerchant(@RequestParam("username") String username ) throws Exception {
		this.merchantService.deActivateMerchant(username);
		return new ResponseEntity<SuccessMessage>(new SuccessMessage("Merchant De-Activation","Merchant " + username + " De-Activated Successfully"),HttpStatus.OK);
	}

	
	@GetMapping("/merchant/getMerchantProducts")
	public ResponseEntity<Set<Product>> getMerchantProducts(HttpServletRequest request) throws Exception{
		String username = getUsernameOfMerchant(request);
		return new ResponseEntity<Set<Product>> (merchantService.getMerchantProducts(username), HttpStatus.OK);
	}
	
	@GetMapping(value="/merchant/invites",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<Invitation>> getInvites(HttpServletRequest request) throws Exception {
		String username = getUsernameOfMerchant(request);
		return new ResponseEntity<Set<Invitation>>(merchantService.getInvites(username),HttpStatus.OK);
	}
	
	@PostMapping(value="/merchant/activateProduct/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessMessage> markProductAsActive(@PathVariable Integer id) throws Exception {
		merchantService.activateProduct(id);
		return new ResponseEntity<SuccessMessage>(new SuccessMessage("Product Activation ","Activated Product " +  id + " Successfully "),HttpStatus.OK);	
	}
	
	@PostMapping(value="/merchant/inActivateProduct/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessMessage> markProductAsInActive(@PathVariable Integer id,HttpServletRequest request) throws Exception {
		String username = getUsernameOfMerchant(request);
		merchantService.deActivateProduct(id);
		return new ResponseEntity<SuccessMessage>(new SuccessMessage("Product Deactivation ","Deactivated Product " +  id + " Successfully "),HttpStatus.OK);	
	}
	
	public String getUsernameOfMerchant(HttpServletRequest request) throws Exception {
			String header = request.getHeader("Authorization");
			String token = header.substring(7);
			String username = jwtUtil.extractUsername(token);
			return username;	
	}

	@GetMapping(value="/sampleRoute")
	public ResponseEntity<Object> viewUsername(HttpServletRequest request) throws Exception {
		String username = getUsernameOfMerchant(request);
		// then u can pass on the username to further functions;
		// in the function parameter u can pass any kind of arguments along with HttpServletRequest @ReqestBody etc.  See Line 104
		System.out.println(username);
		return ResponseEntity.ok().build();
	}

}
