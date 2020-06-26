package com.cg.capstore.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cg.capstore.entities.CommonFeedback;
import com.cg.capstore.entities.CustomerDetails;
import com.cg.capstore.entities.MerchantDetails;
import com.cg.capstore.entities.Order;
import com.cg.capstore.entities.Product;
import com.cg.capstore.exceptions.EmptyMerchantListException;
import com.cg.capstore.exceptions.NoFeedbacksException;
@Repository
public class CommonFeedbackDaoImpl implements CommonFeedbackDao {
	@Autowired
	EntityManager entity_manager;
	Logger logger=LoggerFactory.getLogger(CommonFeedbackDaoImpl.class);

	/**
	 * This method is used to persist the data fetched from the feedback form to the database
	 * @param username This is the first parameter to commonFeedbackUser method
	 * @param merchant_name This is the second parameter to commonFeedbackUser method
	 * @param common_feedback This is the third parameter to commonFeedbackUser method
	 * @return boolean This returns true after adding the data to the database
	 */
	@Override
	public boolean commonFeedbackUser(String username,String merchant_name,CommonFeedback common_feedback) {
		logger.trace("commonFeedbackUser method is accessed at dao layer ");
		CustomerDetails ob=entity_manager.find(CustomerDetails.class,username);
		ob.addCommonFeedback(common_feedback);
		MerchantDetails merchant=entity_manager.find(MerchantDetails.class, merchant_name);
		merchant.addFeedback(common_feedback);
		entity_manager.merge(merchant);
		entity_manager.merge(ob);
		entity_manager.persist(common_feedback);
		logger.info("Common feedback given by user added to the database");
		return true;
	}

	/**
	 * This method is used to fetch the list of the merchant names from the database
	 * @param username This is the only parameter to merchantNameList method
	 * @return Set<String> This returns the set of the merchant names for the corresponding username ,i.e, list of those merchants from whom the user has brought products
	 */
	@Override
	public Set<String> merchantNameList(String username){
		logger.trace("merchantNameList is accessed at dao layer");
		CustomerDetails customer=entity_manager.find(CustomerDetails.class, username);
		Set<String> merchant_name=new HashSet<>();
		Set<Order> order_set=new HashSet<>();
		List<Product> product_list=new ArrayList<>();
		order_set=customer.getOrders();
		Iterator itr=order_set.iterator();
		while(itr.hasNext()) {
			Order order=(Order)itr.next();
			product_list.add(order.getProduct());
		}
		Iterator itr1=product_list.iterator();
		while(itr1.hasNext()) {
			Product product=(Product)itr1.next();
			merchant_name.add(product.getMerchant().getUsername());
		}
		logger.info("Merchant list fetched");
		return merchant_name;

	}

	/**
	 * This method is used to fetch the set of feedbacks received by a particular merchant from the database
	 * @param m_username This is the only parameter to commonFeedbackMerchant method
	 * @return Set<CommonFeedback> This returns the set of feedbacks received by a particular merchant
	 */
	@Override
	public Set<CommonFeedback> commonFeedbackMerchant(String m_username){
		logger.trace("commonFeedbackMerchant is accessed at dao layer");
		MerchantDetails merchant=entity_manager.find(MerchantDetails.class, m_username);
		Set<CommonFeedback> feedbacksAll=new HashSet<>();
		feedbacksAll=merchant.getFeedbacks();
		Set<CommonFeedback> feedbacks=new HashSet<>();
		Iterator itr=feedbacksAll.iterator();
		while(itr.hasNext()) {
			CommonFeedback feedback1=(CommonFeedback)itr.next();
			if(feedback1.isEnableRead() && feedback1.getResponse()==null)
				feedbacks.add(feedback1);
		}
		logger.info("Feedbacks fetched to be displayed to the merchant");
		return feedbacks;

	}

	/**
	 * This method is used to persist the response given by the merchant for a particular feedback to the database
	 * @param feedbackId This is the first parameter to merchantResponse method
	 * @param response This is the second parameter to merchantResponse method
	 * @return boolean This returns true after successfully adding the data to the database
	 */
	@Override
	public boolean merchantResponse(int feedbackId,String response) {
		logger.trace("merchantRespose method called at dao layer");
		CommonFeedback feedback=entity_manager.find(CommonFeedback.class,feedbackId);
		feedback.setResponse(response);
		entity_manager.merge(feedback);
		logger.info("Merchant Response set to the database column");
		return true;

	}

	/**
	 * This method is used to fetch the set of feedbacks the user has given to different merchants and their responses
	 * @param username This is the only parameter to responseToUser method
	 * @return Set<CommonFeedback> This returns the set of the feedbacks which the user has given to different merchants and their replies if any
	 */
	@Override
	public Set<CommonFeedback> responseToUser(String username) {
		logger.trace("responseToUser method is accessed at dao layer");
		CustomerDetails ob=entity_manager.find(CustomerDetails.class,username);
		logger.info("All the feedbacks for a respective user fetched");
		return ob.getFeedbacks();


	}

	/**
	 * This method is used to check if the merchant name list is empty or not
	 * @param username This is the only parameter to merchantNameListNotEmpty method
	 * @return boolean This returns true if the list of merchant names is not empty and throws Exception otherwise
	 * @throws EmptyMerchantListException This exception is thrown if the list of merchant name is empty
	 */

	@Override
	public boolean merchantNameListNotEmpty(String username)throws EmptyMerchantListException {
		logger.trace("merchantNameListNotEmpty method is accessed at dao layer");
		if(merchantNameList(username).size()!=0)
		{
			logger.info("Merchant list is not empty");
			return true;
		}
		else {
			logger.error("Merchant list is empty");
			throw new EmptyMerchantListException("No orders placed yet!! Order now to avail this option!!");
		}
	}

	/**
	 * This method is used to check if the merchant feedback list is empty or not
	 * @param m_username This is the only parameter to merchantFeedbackListNotEmpty method
	 * @return boolean This returns true if the list of merchant feedbacks is not empty and throws Exception otherwise
	 * @throws NoFeedbacksException This exception is thrown if the list of merchant feedbacks is empty
	 */
	@Override
	public boolean merchantFeedbackListNotEmpty(String m_username) throws NoFeedbacksException {
		logger.trace("merchantFeedbackListNotEmpty method is accessed at dao layer");
		if(commonFeedbackMerchant(m_username).size()!=0) {
			logger.info("Feedback list to be shown to the merchant is not empty");
			return true;
		}

		else {
			logger.error("No feedbacks to show to the merchant");
			throw new NoFeedbacksException("No feedbacks to show!!");
		}
	}

	/**
	 * This method is used to check if the user feedback list is empty or not
	 * @param username This is the only parameter to userFeedbackListNotEmpty method
	 * @return boolean This returns true if the list of user feedbacks is not empty and throws Exception otherwise
	 * @throws NoFeedbacksException This exception is thrown if the list of user feedbacks is empty
	 */
	@Override
	public boolean userFeedbacktListNotEmpty(String username) throws NoFeedbacksException {
		logger.trace("userFeedbacktListNotEmpty method is accessed at dao layer");
		if(responseToUser(username).size()!=0) {
			logger.info("The feedback list to be displayed to the user is not empty");
			return true;	
		}

		else {
			logger.error("No feedbacks given by the user so empty list fetched from the database");
			throw new NoFeedbacksException("You have not given any feedbacks yet!!");

		}
	}

}
