package com.cg.capstore.dao;
import java.util.Set;

import com.cg.capstore.entities.CommonFeedback;
import com.cg.capstore.exceptions.EmptyMerchantListException;
import com.cg.capstore.exceptions.NoFeedbacksException;
public interface CommonFeedbackDao {
	public boolean commonFeedbackUser(String username,String merchant_name,CommonFeedback common_feedback);
	public Set<String> merchantNameList(String username);
	public Set<CommonFeedback> commonFeedbackMerchant(String m_username);
	public boolean merchantResponse(int feedbackId,String response);
	public Set<CommonFeedback> responseToUser(String username);
	public boolean merchantNameListNotEmpty(String username)throws EmptyMerchantListException;
	public boolean merchantFeedbackListNotEmpty(String m_username)throws NoFeedbacksException;
	public boolean userFeedbacktListNotEmpty(String username)throws NoFeedbacksException;

//	public void orderUser(String username,Order order);
}
