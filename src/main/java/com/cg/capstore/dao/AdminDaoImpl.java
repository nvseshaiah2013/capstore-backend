package com.cg.capstore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


import org.springframework.stereotype.Repository;

import com.cg.capstore.entities.CustomerDetails;
import com.cg.capstore.entities.Invitation;

@Repository
public class AdminDaoImpl implements IAdminDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<CustomerDetails> getCustomerList() {
		String str="SELECT customer FROM CustomerDetails customer";
		TypedQuery<CustomerDetails> query=entityManager.createQuery(str, CustomerDetails.class);
		List<CustomerDetails> customers=query.getResultList();
		return customers;
		
	}
	
	@Override
	public List<Invitation> getInvites(){
		String str = "SELECT invitation FROM Invitation invitation";
		TypedQuery<Invitation> query = entityManager.createQuery(str,Invitation.class);
		List<Invitation> invites = query.getResultList();
		return invites;
	}
	
	@Override
	public void sendInvite(Invitation invitation) throws Exception {
		entityManager.persist(invitation);
	}

}
