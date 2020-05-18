package com.cg.capstore.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.cg.capstore.entities.Product;

@Repository
public class ProductDaoImpl implements IProductDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void activateProduct(Product product) throws Exception {
		product.setProductActivated(true);
		entityManager.merge(product);
	}
	
	@Override
	public void inActivateProduct(Product product) throws Exception {
		product.setProductActivated(false);
		entityManager.merge(product);
	}
	
	@Override
	public Product findProductById(int id) throws Exception {
		return entityManager.find(Product.class, id);
	}
}
