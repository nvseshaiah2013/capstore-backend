package com.cg.capstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.capstore.dao.ICustomerDao;
import com.cg.capstore.entities.CustomerDetails;

@Transactional
@Service("CustomerServiceImpl")
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private ICustomerDao customerDao;
	
	@Override
	public Long countOfCustomers() throws Exception {
		return customerDao.countOfCustomers();
	}

}