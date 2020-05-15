package com.cg.capstore.service;

import java.util.List;

import com.cg.capstore.entities.CustomerDetails;
import com.cg.capstore.entities.Invitation;

public interface IAdminService {
	public List<CustomerDetails> getCustomerList();
	public List<Invitation> getInvites();
	public void sendInvite(Invitation invitation) throws Exception;
}
