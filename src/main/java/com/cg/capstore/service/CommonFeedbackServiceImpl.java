package com.cg.capstore.service;

import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.capstore.dao.CommonFeedbackDaoImpl;
import com.cg.capstore.entities.CommonFeedback;
@Service
@Transactional
public class CommonFeedbackServiceImpl implements CommonFeedbackService {
	@Autowired
	CommonFeedbackDaoImpl capstore_dao;
	Logger logger=LoggerFactory.getLogger(CommonFeedbackServiceImpl.class);
	
	/**
	 * This service method is used to give feedback to a merchant
	 * @param username This is the first parameter to commonFeedbackUser method
	 * @param merchant_name This is the second parameter to commonFeedbackUser method
	 * @param common_feedback This is the third parameter to commonFeedbackUser method
	 * @return boolean This returns true/false based on value returned from dao layer
	 */
	@Override
	public boolean commonFeedbackUser(String username,String merchant_name,CommonFeedback common_feedback) {
		logger.trace("commonFeedbackUser method accessed at service layer");
		return capstore_dao.commonFeedbackUser(username,merchant_name,common_feedback);
	}
	
	/**
	 * This service method is used to fetch the list of the merchant names 
	 * @param username This is the only parameter to merchantNameList method
	 * @return Set<String> This returns the set of the merchant names for the corresponding username ,i.e, list of those merchants from whom the user has brought products
	 */
	@Override
	public Set<String> merchantNameList(String username) {
		logger.trace("merchantNameList method accessed at service layer");
		return capstore_dao.merchantNameList(username);
	}
	
	/**
	 * This service method is used to fetch the set of feedbacks received by a particular merchant
	 * @param m_username This is the only parameter to commonFeedbackMerchant method
	 * @return Set<CommonFeedback> This returns the set of feedbacks received by a particular merchant
	 */
	@Override
	public Set<CommonFeedback> commonFeedbackMerchant(String m_username) {
		logger.trace("commonFeedbackMerchant method accessed at service layer");
		return capstore_dao.commonFeedbackMerchant(m_username);
	}

	/**
	 * This service method is used to add the response given by the merchant for a particular feedback 
	 * @param feedbackId This is the first parameter to merchantResponse method
	 * @param response This is the second parameter to merchantResponse method
	 * @return boolean This returns true/false based on the value returned from the database
	 */
	@Override
	public boolean merchantResponse(int feedbackId, String response) {
		logger.trace("merchantResponse method accessed at service layer");
		return capstore_dao.merchantResponse(feedbackId, response);
		
	}

	/**
	 * This service method is used to fetch the set of feedbacks the user has given to different merchants and their responses
	 * @param username This is the only parameter to responseToUser method
	 * @return Set<CommonFeedback> This returns the set of the feedbacks which the user has given to different merchants and their replies if any
	 */
	@Override
	public Set<CommonFeedback> responseToUser(String username) {
		logger.trace("responseToUser method accessed at service layer");
		return capstore_dao.responseToUser(username);
	}

	/**
	 * This service method is used to check if the merchant name list is empty or not
	 * @param username This is the only parameter to merchantNameListNotEmpty method
	 * @return boolean This returns true if the list of merchant names is not empty
	 */
	@Override
	public boolean merchantNameListNotEmpty(String username) {
		logger.trace("merchantNameListNotEmpty method accessed at service layer");
		return capstore_dao.merchantNameListNotEmpty(username);
	}
	
	/**
	 * This method is used to check if the merchant feedback list is empty or not
	 * @param m_username This is the only parameter to merchantFeedbackListNotEmpty method
	 * @return boolean This returns true if the list of merchant feedbacks is not empty 
	 */

	@Override
	public boolean merchantFeedbackListNotEmpty(String m_username) {
		logger.trace("merchantFeedbackListNotEmpty method accessed at service layer");
		return capstore_dao.merchantFeedbackListNotEmpty(m_username);
	}
	
	/**
	 * This method is used to check if the user feedback list is empty or not
	 * @param username This is the only parameter to userFeedbackListNotEmpty method
	 * @return boolean This returns true if the list of user feedbacks is not empty
	 */
	@Override
	public boolean userFeedbacktListNotEmpty(String username) {
		logger.trace("userFeedbacktListNotEmpty method accessed at service layer");
		return capstore_dao.userFeedbacktListNotEmpty(username);
	}
	

}
