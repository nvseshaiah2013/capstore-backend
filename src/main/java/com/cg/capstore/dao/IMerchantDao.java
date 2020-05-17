package com.cg.capstore.dao;

import java.util.List;
import java.util.Set;

import com.cg.capstore.entities.MerchantDetails;
import com.cg.capstore.entities.Order;
import com.cg.capstore.entities.Product;

public interface IMerchantDao {
	
	List<MerchantDetails> getMerchants() throws Exception;

	MerchantDetails getMerchantInfo(String username);

	Set<Order> getMerchantOrders(String username);

	Order acceptMerchantOrder(long orderId, String status);

	Set<Product> getMerchantProducts(String username);
}
