package com.cg.capstore.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.capstore.dao.ICustomerDao;

@Transactional
@Service("CustomerServiceImpl")

public class CustomerServiceImpl implements ICustomerService{

	
	@Autowired
	private ICustomerDao customerDao;
	
	@Override
	public Long countOfCustomers() throws Exception {
		return customerDao.countOfCustomers();
	}

}
