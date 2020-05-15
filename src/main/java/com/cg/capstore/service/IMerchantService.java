package com.cg.capstore.service;

import java.util.List;

import com.cg.capstore.entities.MerchantDetails;

public interface IMerchantService {
	
	Long countOfMerchants() throws Exception;
	List<MerchantDetails> getMerchants() throws Exception;
}
