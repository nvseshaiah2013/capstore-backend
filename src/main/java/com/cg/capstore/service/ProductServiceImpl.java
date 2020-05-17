package com.cg.capstore.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.capstore.dao.IProductDao;
import com.cg.capstore.entities.Product;

@Service
@Transactional
public class ProductServiceImpl implements IProductService{

	@Autowired
	private IProductDao productDao;
	
	@Override
	public void activateProduct(int id) throws Exception {
		Product product = findProductById(id);
		productDao.activateProduct(product);
	}
	
	@Override
	public void deActivateProduct(int id) throws Exception {
		Product product = findProductById(id);
		productDao.inActivateProduct(product);
	}
	
	@Override
	public Product findProductById(int id) throws Exception {
		Product product = this.productDao.findProductById(id);
		if(product == null) 
			throw new Exception("Product with " + id + " not found");
		return product;
	}
}
