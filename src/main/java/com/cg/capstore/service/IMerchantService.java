package com.cg.capstore.service;

import java.util.List;
import java.util.Set;

import com.cg.capstore.entities.MerchantDetails;
import com.cg.capstore.entities.Order;

public interface IMerchantService {
	
	List<MerchantDetails> getMerchants() throws Exception;

	MerchantDetails getMerchantInfo(String username);

	Set<Order> getMerchantOrders(String username);

	Order acceptMerchantOrder(long orderId, String status);

	void activateMerchant(String username) throws Exception;

	void deActivateMerchant(String username) throws Exception;

	MerchantDetails findMerchantByUsername(String username) throws Exception;
}
