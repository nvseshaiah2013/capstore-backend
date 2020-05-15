package com.cg.capstore.dao;

import java.util.List;

import com.cg.capstore.entities.MerchantDetails;

public interface IMerchantDao {
	
	Long countOfMerchants() throws Exception;
	List<MerchantDetails> getMerchants() throws Exception;
}
