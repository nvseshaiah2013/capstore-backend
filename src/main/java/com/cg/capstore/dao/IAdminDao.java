package com.cg.capstore.dao;

import java.util.List;

import com.cg.capstore.entities.CustomerDetails;
import com.cg.capstore.entities.Invitation;

public interface IAdminDao {
	public List<CustomerDetails> getCustomerList();
	public List<Invitation> getInvites();
	public void sendInvite(Invitation invitation) throws Exception;
}
