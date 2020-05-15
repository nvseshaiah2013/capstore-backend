package com.cg.capstore.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository("AdminOrderDaoImpl")
public class AdminOrderDaoImpl implements AdminOrderDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	

}
