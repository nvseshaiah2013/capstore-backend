package com.cg.capstore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.capstore.entities.CustomerDetails;
import com.cg.capstore.entities.MerchantDetails;

@Repository("MerchantDaoImpl")
public class MerchantDaoImpl implements IMerchantDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Long countOfMerchants() throws Exception {
		Query query=entityManager.createQuery("SELECT COUNT(*) FROM MerchantDetails");
		return (Long) query.getSingleResult();
	}

}
