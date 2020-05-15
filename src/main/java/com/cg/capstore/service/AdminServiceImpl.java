package com.cg.capstore.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.capstore.dao.IAdminDao;

import com.cg.capstore.entities.CustomerDetails;
import com.cg.capstore.entities.Invitation;

@Service
@Transactional
public class AdminServiceImpl implements IAdminService {

	@Autowired
	private IAdminDao adminDao;

	@Override
	public List<CustomerDetails> getCustomerList() {
		return adminDao.getCustomerList();
	}

	@Override
	public List<Invitation> getInvites(){
		return adminDao.getInvites();
	}
	
	@Override
	public void sendInvite(Invitation invitation) throws Exception {
		adminDao.sendInvite(invitation);
	}
}
