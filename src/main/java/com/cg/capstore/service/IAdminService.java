package com.cg.capstore.service;

import java.util.List;

import com.cg.capstore.entities.CustomerDetails;

public interface IAdminService {
	public List<CustomerDetails> getCustomerList();
}
