package com.cg.capstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.capstore.dao.CustomerDao;
import com.cg.capstore.dao.MerchantDao;

@Transactional
@Service("MerchantServiceImpl")
public class MerchantInterfaceImpl implements MerchantService {

	@Autowired
	private MerchantDao merchantDao;
	
	@Override
	public Long countOfMerchants() throws Exception {
		return merchantDao.countOfMerchants();
	}

}
