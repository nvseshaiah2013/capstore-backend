package com.cg.capstore.dao;

import java.util.List;
import java.util.Set;

import com.cg.capstore.entities.MerchantDetails;
import com.cg.capstore.entities.Order;

public interface IMerchantDao {
	
	List<MerchantDetails> getMerchants() throws Exception;

	MerchantDetails getMerchantInfo(String username);

	Set<Order> getMerchantOrders(String username);
}
