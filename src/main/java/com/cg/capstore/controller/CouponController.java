package com.cg.capstore.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.capstore.entities.Coupon;
import com.cg.capstore.service.ICouponService;

@SpringBootApplication
@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class CouponController {
	
	@Autowired
	private ICouponService couponService;
	
	@GetMapping("/hello")
	public ResponseEntity<Object> checkWorking(){
		return new ResponseEntity<Object>("Working fine..", HttpStatus.OK);
	}
	
	@PostMapping("/addCoupon")
	public ResponseEntity<Object> addCoupon(@RequestBody CouponValue couponValue) throws Exception{
	   Coupon coupon = new Coupon();
	   System.out.println("--------------------------------------------");
	   System.out.println(couponValue);
	   System.out.println("--------------------------------------------");
	   coupon.setCouponCode(couponValue.couponCode);
	   coupon.setCouponAmount(couponValue.couponAmount);
	   coupon.setCouponDesc(couponValue.couponDesc);
	   coupon.setMinOrderAmount(couponValue.minOrderAmount);
	   coupon.setIssuedBy("Admin");
	   Timestamp startDate = Timestamp.valueOf(couponValue.couponStartDate);
	   Timestamp endDate = Timestamp.valueOf(couponValue.couponEndDate);
	   coupon.setCouponStartDate(startDate);
	   coupon.setCouponEndDate(endDate);
	   coupon.setActive(false);
	   if(couponService.addCoupon(coupon)) {
	   return new ResponseEntity<Object>("Coupon with coupon code: " + couponValue.couponCode + " generated successfully.", HttpStatus.OK);
	   }
	   else {
		   throw new Exception("Something wents Wrong...");
	   }
	}
	
	@GetMapping("/checkstartdate")
	public ResponseEntity<Object> checkStartDate(@RequestParam("start") String dateTime) throws Exception {
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	    String dateandtime = formatter.format(dateTime);
		Timestamp  time = Timestamp.valueOf(dateTime);
		if(couponService.checkStartDate(time)) {
			return new ResponseEntity<Object>(true, HttpStatus.OK);
		}
		else {
			throw new Exception("Date Must be greater then current date...");
		}
	}
	
	@GetMapping("/checkenddate")
	public ResponseEntity<Object> checkEndDate(@RequestParam("start") String startDate, @RequestParam("end") String endDate) throws Exception{
		Timestamp start = Timestamp.valueOf(startDate);
		Timestamp end = Timestamp.valueOf(endDate);
		if(couponService.checkEndDate(start, end)) {
			return new ResponseEntity<Object>(true, HttpStatus.OK);
		}
		else {
			throw new Exception("EndDate Must be greater then StartDate...");
		}
	}
	
	@GetMapping("/checkCouponCode")
	public ResponseEntity<Object> checkCouponCode(@RequestParam("couponCode") String coupon) throws Exception{
		if(couponService.checkCouponCode(coupon)) {
			return new ResponseEntity<Object>(true, HttpStatus.OK);
		}
		else
		{
			throw new Exception("Coupon Code Already Exist...");
		}
	}
	
	@GetMapping("/updateCouponStatus")
	public ResponseEntity<Object> updateCouponStatus() throws Exception{
		couponService.updateStatus();
		return new ResponseEntity<Object>(true, HttpStatus.OK);
	}
	
	@GetMapping("/getCouponbyName")
	public ResponseEntity<Object> getCouponbyName(@RequestParam("couponName") String name) throws Exception{
		try {
			Coupon coupon = couponService.getCouponByName(name);
			return new ResponseEntity<Object>(coupon, HttpStatus.OK);
		}catch (Exception exception) {
			return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.IM_USED);
		}
		
	}
	
	@GetMapping("/listOfCoupons")
	public ResponseEntity<List<Object>> listOfCoupons() throws Exception{
		return new ResponseEntity<List<Object>>(couponService.listOfCoupons(), HttpStatus.OK);
	}
	
	@PostMapping(value="updateCoupon", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateCoupon(@RequestParam("coupon") String CouponCode, @RequestParam("start") String startDate, @RequestParam("end") String endDate) throws Exception{
		Timestamp Date1 = Timestamp.valueOf(startDate);
		Timestamp Date2 = Timestamp.valueOf(endDate);
		if(couponService.updateCoupon(CouponCode, Date1, Date2)) {
			return new ResponseEntity<Object>("Coupon updated and activated successfully...",HttpStatus.OK);
		}else {
			throw new Exception("Something wents wrong...");
		}
	}
	
	@PostMapping("/deleteCoupon")
	public ResponseEntity<Object> deleteCoupon(@RequestParam("couponName") String couponName) throws Exception{
		if(couponService.deleteCoupon(couponName)) {
			return new ResponseEntity<Object>("coupon with name "+ couponName + " deleted...", HttpStatus.OK);
		}
		else {
			throw new Exception("Internal Server Error...");
		}
	}
	
	

}

class CouponValue{
	public String couponCode;
	public String couponEndDate;
	public String couponStartDate;
	public int couponAmount;
	public int minOrderAmount;
	public String couponDesc;
}
