package com.cg.capstore.controller;

import java.sql.Timestamp;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.capstore.entities.Coupon;
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
	public ResponseEntity<SuccessMessage> markProductAsActive(@PathVariable Integer id,HttpServletRequest request) throws Exception {
		String username = getUsernameOfMerchant(request);
		merchantService.activateProduct(id,username);
		return new ResponseEntity<SuccessMessage>(new SuccessMessage("Product Activation ","Activated Product " +  id + " Successfully "),HttpStatus.OK);	
	}
	
	@PostMapping(value="/merchant/inActivateProduct/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessMessage> markProductAsInActive(@PathVariable Integer id,HttpServletRequest request) throws Exception {
		String username = getUsernameOfMerchant(request);
		merchantService.deActivateProduct(id,username);
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

	@PostMapping("/merchant/addCoupon")
	public ResponseEntity<Object> addCoupon(@RequestBody CouponValue1 couponValue,HttpServletRequest request) throws Exception{
	   String username = getUsernameOfMerchant(request);
		Coupon coupon = new Coupon();
	   coupon.setCouponCode(couponValue.couponCode);
	   coupon.setCouponAmount(couponValue.couponAmount);
	   coupon.setCouponDesc(couponValue.couponDesc);
	   coupon.setMinOrderAmount(couponValue.minOrderAmount);
	   coupon.setIssuedBy("Merchant");
	   Timestamp startDate = Timestamp.valueOf(couponValue.couponStartDate);
	   Timestamp endDate = Timestamp.valueOf(couponValue.couponEndDate);
	   coupon.setCouponStartDate(startDate);
	   coupon.setCouponEndDate(endDate);
	   coupon.setActive(false);
	   if(merchantService.addCoupon(coupon,username)) {
	   return new ResponseEntity<Object>("Coupon with coupon code: " + couponValue.couponCode + " generated successfully.", HttpStatus.OK);
	   }
	   else {
		   throw new Exception("Something went Wrong...");
	   }
	}
	
	@GetMapping("/merchant/checkstartdate")
	public ResponseEntity<Object> checkStartDate(@RequestParam("start") String dateTime) throws Exception {
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	    String dateandtime = formatter.format(dateTime);
		Timestamp  time = Timestamp.valueOf(dateTime);
		if(merchantService.checkStartDate(time)) {
			return new ResponseEntity<Object>(true, HttpStatus.OK);
		}
		else {
			throw new Exception("Date Must be greater then current date...");
		}
	}
	
	@GetMapping("/merchant/checkenddate")
	public ResponseEntity<Object> checkEndDate(@RequestParam("start") String startDate, @RequestParam("end") String endDate) throws Exception{
		Timestamp start = Timestamp.valueOf(startDate);
		Timestamp end = Timestamp.valueOf(endDate);
		if(merchantService.checkEndDate(start, end)) {
			return new ResponseEntity<Object>(true, HttpStatus.OK);
		}
		else {
			throw new Exception("EndDate Must be greater then StartDate...");
		}
	}
	
	@GetMapping("/merchant/checkCouponCode")
	public ResponseEntity<Object> checkCouponCode(@RequestParam("couponCode") String coupon) throws Exception{
		if(merchantService.checkCouponCode(coupon)) {
			return new ResponseEntity<Object>(true, HttpStatus.OK);
		}
		else
		{
			throw new Exception("Coupon Code Already Exist...");
		}
	}
	
	@GetMapping("/merchant/updateCouponStatus")
	public ResponseEntity<Object> updateCouponStatus() throws Exception{
		merchantService.updateStatus();
		return new ResponseEntity<Object>(true, HttpStatus.OK);
	}
	
	@GetMapping("/merchant/getCouponbyName")
	public ResponseEntity<Object> getCouponbyName(@RequestParam("couponName") String name) throws Exception{
		try {
			Coupon coupon = merchantService.getCouponByName(name);
			return new ResponseEntity<Object>(coupon, HttpStatus.OK);
		}catch (Exception exception) {
			return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.IM_USED);
		}
		
	}
	
	@GetMapping("/merchant/listOfCoupons")
	public ResponseEntity<List<Coupon>> listOfCoupons(HttpServletRequest request) throws Exception{
		 String username = getUsernameOfMerchant(request);
		return new ResponseEntity<List<Coupon>>(merchantService.listOfCoupons(username), HttpStatus.OK);
	}
	
	@PostMapping(value="/merchant/updateCoupon", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateCoupon(@RequestParam("coupon") String CouponCode, @RequestParam("start") String startDate, @RequestParam("end") String endDate) throws Exception{
		Timestamp Date1 = Timestamp.valueOf(startDate);
		Timestamp Date2 = Timestamp.valueOf(endDate);
		if(merchantService.updateCoupon(CouponCode, Date1, Date2)) {
			return new ResponseEntity<Object>("Coupon updated and activated successfully...",HttpStatus.OK);
		}else {
			throw new Exception("Something went wrong...");
		}
	}
	
	@PostMapping("/merchant/deleteCoupon")
	public ResponseEntity<Object> deleteCoupon(@RequestParam("couponName") String couponName) throws Exception{
		if(merchantService.deleteCoupon(couponName)) {
			return new ResponseEntity<Object>("coupon with name "+ couponName + " deleted...", HttpStatus.OK);
		}
		else {
			throw new Exception("Internal Server Error...");
		}
	}
}

class CouponValue1{
	public String couponCode;
	public String couponEndDate;
	public String couponStartDate;
	public int couponAmount;
	public int minOrderAmount;
	public String couponDesc;
}
