package com.cg.capstore.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.capstore.dao.IMerchantDao;
import com.cg.capstore.entities.MerchantDetails;
import com.cg.capstore.entities.Order;
import com.cg.capstore.entities.Product;

@Service
@Transactional
public class MerchantServiceImpl implements IMerchantService {

	@Autowired
	private IMerchantDao merchantDao;

	@Override
	public List<MerchantDetails> getMerchants() throws Exception {
		return merchantDao.getMerchants();
	}

	@Override
	public MerchantDetails getMerchantInfo(String username) {
		return merchantDao.getMerchantInfo(username);
	}
	
	@Override
	public Set<Order> getMerchantOrders(String username){
		return merchantDao.getMerchantOrders(username);
	}
	
	@Override
	public Order acceptMerchantOrder(long orderId, String status) {
		return merchantDao.acceptMerchantOrder(orderId, status);
	}
	
	@Override
	public Set<Product> getMerchantProducts(String username){
		return merchantDao.getMerchantProducts(username);
	}
}
