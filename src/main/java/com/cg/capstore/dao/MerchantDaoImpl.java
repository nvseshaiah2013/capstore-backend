package com.cg.capstore.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;


@Repository
public class MerchantDaoImpl implements IMerchantDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Long countOfMerchants() throws Exception {
		Query query=entityManager.createQuery("SELECT COUNT(*) FROM MerchantDetails");
		return (Long) query.getSingleResult();
	}

}
