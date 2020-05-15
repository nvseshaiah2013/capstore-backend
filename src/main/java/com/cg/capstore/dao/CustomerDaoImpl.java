package com.cg.capstore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.capstore.entities.CustomerDetails;

@Repository("CustomerDaoImpl")
public class CustomerDaoImpl implements CustomerDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Long countOfCustomers() throws Exception {
		Query query=entityManager.createQuery("SELECT COUNT(*) FROM CustomerDetails");
		return (Long) query.getSingleResult();
	}
}
