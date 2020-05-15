package com.cg.capstore.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.capstore.dao.IAdminDao;

@Transactional
@Service("AdminOrderServiceImpl")
public class AdminServiceImpl implements AdminService {

	@Autowired
	private IAdminDao adminOrderDao;
	
	
}
