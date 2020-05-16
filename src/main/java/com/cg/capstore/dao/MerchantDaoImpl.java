package com.cg.capstore.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.capstore.entities.MerchantDetails;
import com.cg.capstore.entities.Order;


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

}
