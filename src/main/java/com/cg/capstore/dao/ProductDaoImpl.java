package com.cg.capstore.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.capstore.entities.Category;
import com.cg.capstore.entities.MerchantDetails;
import com.cg.capstore.entities.Product;
import com.cg.capstore.entities.SubCategory;

@Repository("ProductDaoImpl")
public class ProductDaoImpl implements IProductDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public boolean addProduct(Product product, String merchantUserName, int category, String subCateogy) throws Exception {
		MerchantDetails merchantDetail = entityManager.find(MerchantDetails.class, merchantUserName);
		if(merchantDetail == null) {
			throw new Exception("No such Merchant founded...");
		}
		try {
		Category categoryObject = entityManager.find(Category.class, category);
		SubCategory subCategoryObject = new SubCategory();
		subCategoryObject.setName(subCateogy);
		entityManager.persist(subCategoryObject);
		categoryObject.addSubCategory(subCategoryObject);
		entityManager.merge(categoryObject);
		product.setSubCategory(subCategoryObject);
		entityManager.persist(product);
		merchantDetail.addProduct(product);
		entityManager.merge(merchantDetail);
		return true;
		}catch(Exception exception) {
			throw new Exception("Internal Server Error...");
		}
	}

	@Override
	public boolean updateStock(int productId, int productCount, double prodPrice, String prodInfo) throws Exception {
	    try {
	    	Product product = entityManager.find(Product.class, productId);
	    	product.setNoOfProducts(productCount);
	    	product.setProductPrice(prodPrice);
	    	product.setProductInfo(prodInfo);
		    entityManager.merge(product);
		    return true;
	    }catch(Exception exception) {
	    	throw new Exception("Internal server error...");
	    }
	    
	}

	@Override
	public Product getProductName(int productId) throws Exception {
		Product product = entityManager.find(Product.class, productId);
		if(product == null) {
			throw new Exception("No Product Available with this name.");
		}
		else
			return product;
	}

	@Override
	public List<Category> getAllCategory() throws Exception {
		String statement = "select category FROM Category category";
		TypedQuery<Category> query = entityManager.createQuery(statement, Category.class);
		List<Category> productList = query.getResultList();
		if(productList.size() == 0) {
			throw new Exception("No Category available...");
		}
		else
		{
			return productList;
		}
	}	
	
}
