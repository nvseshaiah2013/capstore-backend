package com.cg.capstore.controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.capstore.entities.CommonFeedback;
import com.cg.capstore.service.CommonFeedbackServiceImpl;
import com.cg.capstore.util.JwtUtil;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CommonFeedbackController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CommonFeedbackServiceImpl capstore_service;
	Logger logger = LoggerFactory.getLogger(CommonFeedbackController.class);

	@PostMapping("/customer/addfeedback")
	public boolean commonFeedbackUser(@RequestBody CommonFeedback1 common_feedback,HttpServletRequest request) throws Exception {
		String username = this.extractUsername(request);
		CommonFeedback cf = new CommonFeedback();
		cf.setFeedbackSubject(common_feedback.feedbackSubject);
		cf.setFeedbackMessage(common_feedback.feedbackMessage);
		return capstore_service.commonFeedbackUser(username, common_feedback.merchant_name, cf);
	}

	@GetMapping("/customer/getMerchantNameList")
	public Set<String> merchantNameList(HttpServletRequest request) throws Exception {
		String username = this.extractUsername(request);
		return capstore_service.merchantNameList(username);
	}

	@GetMapping("/merchant/getFeedbacksMerchant")
	public Set<CommonFeedbackDetailsForMerchant> commonFeedbackMerchant(HttpServletRequest request) throws Exception {
		String m_username = this.extractUsername(request);
		Set<CommonFeedback> feedbackDetailed = new HashSet<CommonFeedback>();
		Set<CommonFeedbackDetailsForMerchant> feedbackRequired = new HashSet<CommonFeedbackDetailsForMerchant>();
		feedbackDetailed = capstore_service.commonFeedbackMerchant(m_username);
		Iterator<CommonFeedback> itr = feedbackDetailed.iterator();
		while (itr.hasNext()) {
			CommonFeedback feedback1 = (CommonFeedback) itr.next();
			CommonFeedbackDetailsForMerchant obj = new CommonFeedbackDetailsForMerchant();
			obj.feedbackId = feedback1.getFeedbackId();
			obj.feedbackSubject = feedback1.getFeedbackSubject();
			obj.feedbackMessage = feedback1.getFeedbackMessage();
			feedbackRequired.add(obj);
		}
		return feedbackRequired;
	}

	@PostMapping("/merchant/getMerchantResponse")
	public boolean merchantResponse(@RequestBody merchantResponse1 response1) {
		return capstore_service.merchantResponse(response1.feedbackId, response1.response);
	}

	@GetMapping("/customer/responseToUser")
	public Set<FeedbackDetailsForUSer> responseToUser(HttpServletRequest request) throws Exception {
		String username = this.extractUsername(request);
		Set<CommonFeedback> feedbackDetailed = new HashSet<CommonFeedback>();
		Set<FeedbackDetailsForUSer> feedbackRequired = new HashSet<FeedbackDetailsForUSer>();
		feedbackDetailed = capstore_service.responseToUser(username);
		Iterator<CommonFeedback> itr = feedbackDetailed.iterator();
		while (itr.hasNext()) {
			CommonFeedback feedback1 = (CommonFeedback) itr.next();
			FeedbackDetailsForUSer obj = new FeedbackDetailsForUSer();
			obj.feedbackId = feedback1.getFeedbackId();
			obj.m_username = feedback1.getMerchant().getUsername();
			obj.feedbackSubject = feedback1.getFeedbackSubject();
			obj.feedbackMessage = feedback1.getFeedbackMessage();
			obj.response = feedback1.getResponse();
			feedbackRequired.add(obj);

		}
		return feedbackRequired;

	}

	@GetMapping("/getMerchantListSize")
	public boolean mechantNameListNotEmpty(HttpServletRequest request) throws Exception {
		String username = this.extractUsername(request);
		return capstore_service.merchantNameListNotEmpty(username);
	}

	@GetMapping("/getMerchantFeedbackSize")
	public boolean mechantFeedbackListNotEmpty(HttpServletRequest request) throws Exception {
		String m_username = this.extractUsername(request);
		return capstore_service.merchantFeedbackListNotEmpty(m_username);
	}

	@GetMapping("/getUserFeedbackSize")
	public boolean userFeedbackListNotEmpty(HttpServletRequest request) throws Exception {
		String username = this.extractUsername(request);
		logger.trace("userFeedbackListNotEmpty method is accessed at controller layer");
		return capstore_service.userFeedbacktListNotEmpty(username);
	}

	private String extractUsername(HttpServletRequest request) throws Exception {
		String header = request.getHeader("Authorization");
		String token = header.substring(7);
		String username = jwtUtil.extractUsername(token);
		return username;
	}

}

class FeedbackDetailsForUSer {
	public int feedbackId;
	public String m_username;
	public String feedbackSubject;
	public String feedbackMessage;
	public String response;

}

class CommonFeedback1 {
	public String feedbackSubject;
	public String feedbackMessage;
	public String merchant_name;
}

class CommonFeedbackDetailsForMerchant {
	public int feedbackId;
	public String feedbackSubject;
	public String feedbackMessage;
}

class merchantResponse1 {
	public int feedbackId;
	public String response;
}
