package com.cg.capstore.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.capstore.dao.IAdminDao;

import com.cg.capstore.entities.CustomerDetails;

@Service
@Transactional
public class AdminServiceImpl implements IAdminService {

	@Autowired
	private IAdminDao adminDao;

	@Override
	public List<CustomerDetails> getCustomerList() {
		return adminDao.getCustomerList();
	}

	
	
}
