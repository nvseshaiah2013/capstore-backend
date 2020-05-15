package com.cg.capstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.capstore.dao.ICustomerDao;
import com.cg.capstore.dao.IMerchantDao;

@Transactional
@Service("MerchantServiceImpl")
public class MerchantServiceImpl implements IMerchantService {

	@Autowired
	private IMerchantDao merchantDao;
	
	@Override
	public Long countOfMerchants() throws Exception {
		return merchantDao.countOfMerchants();
	}

}
