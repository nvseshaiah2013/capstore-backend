package com.cg.capstore.dao;

import java.util.List;

import com.cg.capstore.entities.MerchantDetails;

public interface IMerchantDao {
	
	List<MerchantDetails> getMerchants() throws Exception;
}
