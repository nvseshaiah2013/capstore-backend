package com.cg.capstore.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.capstore.dao.IMerchantDao;
import com.cg.capstore.entities.Invitation;
import com.cg.capstore.entities.MerchantDetails;
import com.cg.capstore.entities.Order;
import com.cg.capstore.entities.Product;

@Service
@Transactional
public class MerchantServiceImpl implements IMerchantService {

	@Autowired
	private IMerchantDao merchantDao;
	
	private Logger logger = Logger.getLogger(getClass());

	@Override
	public List<MerchantDetails> getMerchants() throws Exception {
		logger.info("List of Merchants returned");
		return merchantDao.getMerchants();
	}

	@Override
	public MerchantDetails getMerchantInfo(String username) {
		logger.info("Details of Merchants returned");
		return merchantDao.getMerchantInfo(username);
	}
	
	@Override
	public Set<Order> getMerchantOrders(String username){
		logger.info("No of orders of Merchants returned");
		return merchantDao.getMerchantOrders(username);
	}
	
	@Override
	public Order acceptMerchantOrder(long orderId, String status) {
		logger.info("Merchant accepted the order");
		return merchantDao.acceptMerchantOrder(orderId, status);
	}

	
	@Override
	public void activateMerchant(String username ) throws Exception {
		logger.info("Merchant is activated");
		MerchantDetails merchant = findMerchantByUsername(username);
		this.merchantDao.activateMerchant(merchant);
	}
	
	@Override
	public void deActivateMerchant(String username ) throws Exception {
		logger.info("Merchnat is deactivated");
		MerchantDetails merchant = findMerchantByUsername(username);
		this.merchantDao.deActivateMerchant(merchant);
	}
	
	@Override
	public MerchantDetails findMerchantByUsername(String username ) throws Exception {
		logger.info("Details of merchant of particular id returned");
		MerchantDetails merchant = this.merchantDao.findMerchantByUsername(username);
		if(merchant == null) {
			throw new Exception("Merchant with " + username  + " Not Found");
		}
		return merchant;
	}

	
	@Override
	public Set<Product> getMerchantProducts(String username){
		logger.info("List of Product returned");
		return merchantDao.getMerchantProducts(username);
	}
	
	@Override
	public Set<Invitation> getInvites(String username) throws Exception {
		logger.info("Invite for merchant received");
		MerchantDetails merchant = findMerchantByUsername(username);
		return merchant.getInvites();
	}
	
	@Override
	public void activateProduct(int id) throws Exception {
		logger.info("Product is enabled");
		Product product = findProductById(id);
		merchantDao.activateProduct(product);
	}
	
	@Override
	public void deActivateProduct(int id) throws Exception {
		logger.info("Product is disabled");
		Product product = findProductById(id);
		merchantDao.inActivateProduct(product);
	}
	
	@Override
	public Product findProductById(int id) throws Exception {
		logger.info("Product with particular id is found");
		Product product = this.merchantDao.findProductById(id);
		if(product == null) 
			throw new Exception("Product with " + id + " not found");
		return product;
	}

}
