package com.cg.capstore.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.capstore.entities.MerchantDetails;
import com.cg.capstore.entities.Order;
import com.cg.capstore.entities.Product;


@Repository
public class MerchantDaoImpl implements IMerchantDao {

	@PersistenceContext
	private EntityManager entityManager;
	

	@Override
	public List<MerchantDetails> getMerchants() throws Exception {
		String str = "SELECT merchant FROM MerchantDetails merchant";
		TypedQuery<MerchantDetails> query = entityManager.createQuery(str,MerchantDetails.class);
		List<MerchantDetails> merchants = query.getResultList();
		return merchants;
	}
	
	@Override
	public MerchantDetails getMerchantInfo(String username) {
		return entityManager.find(MerchantDetails.class, username);
	}
	
	@Override
	public Set<Order> getMerchantOrders(String username){
		return entityManager.find(MerchantDetails.class, username).getOrders();
	}
	
	@Override
	public Order acceptMerchantOrder(long orderId, String status) {
		Order order = entityManager.find(Order.class, orderId);
		order.setOrderStatus(status);
		return entityManager.merge(order);
	}


	
	@Override
	public void activateMerchant(MerchantDetails merchant) throws Exception {
		merchant.setDeleted(false);
		entityManager.merge(merchant);
	}
	
	@Override
	public void deActivateMerchant(MerchantDetails merchant) throws Exception {
		merchant.setDeleted(true);
		entityManager.merge(merchant);
	}
	
	@Override
	public MerchantDetails findMerchantByUsername(String username) throws Exception {
		MerchantDetails merchant = entityManager.find(MerchantDetails.class, username);
		return merchant;
	}

	@Override
	public Set<Product> getMerchantProducts(String username){
		return entityManager.find(MerchantDetails.class, username).getProducts();
	}
	@Override
	public void activateProduct(Product product) throws Exception {
		product.setProductActivated(true);
		entityManager.merge(product);
	}
	
	@Override
	public void inActivateProduct(Product product) throws Exception {
		product.setProductActivated(false);
		entityManager.merge(product);
	}
	
	@Override
	public Product findProductById(int id) throws Exception {
		return entityManager.find(Product.class, id);
	}

}
