package com.cg.capstore.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.capstore.dao.AdminOrderDao;

@Transactional
@Service("AdminOrderServiceImpl")
public class AdminOrderServiceImpl implements AdminOrderService {

	@Autowired
	private AdminOrderDao adminOrderDao;
	
	
}
