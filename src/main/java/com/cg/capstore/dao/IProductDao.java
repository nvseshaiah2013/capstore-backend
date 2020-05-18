package com.cg.capstore.dao;

import com.cg.capstore.entities.Product;

public interface IProductDao {

	void activateProduct(Product product) throws Exception;

	void inActivateProduct(Product product) throws Exception;

	Product findProductById(int id) throws Exception;

}
