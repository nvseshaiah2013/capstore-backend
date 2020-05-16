package com.cg.capstore.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.capstore.dao.IMerchantDao;
import com.cg.capstore.entities.MerchantDetails;

@Service
@Transactional
public class MerchantServiceImpl implements IMerchantService {

	@Autowired
	private IMerchantDao merchantDao;

	@Override
	public List<MerchantDetails> getMerchants() throws Exception {
		return merchantDao.getMerchants();
	}

}
