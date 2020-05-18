package com.cg.capstore.service;

import com.cg.capstore.entities.Product;

public interface IProductService {

	public void activateProduct(int id) throws Exception;

	void deActivateProduct(int id) throws Exception;

	Product findProductById(int id) throws Exception;
}
