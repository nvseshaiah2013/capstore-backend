package com.cg.capstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cg.capstore.dao.IProductDao;
import com.cg.capstore.entities.Category;
import com.cg.capstore.entities.Product;


@Transactional
@Service("ProductServiceImpl")
public class ProductServiceImpl implements IProductService{
	
	@Autowired
	private IProductDao couponDao;

	@Override
	public boolean addProduct(Product product, String merchantUserName, int category, String subCategory) throws Exception {
		return couponDao.addProduct(product, merchantUserName, category, subCategory);
	}

	@Override
	public boolean updateStock(int productId, int productCount, int prodPrice, String productInfo) throws Exception {
		return couponDao.updateStock(productId,productCount, prodPrice, productInfo);
	}

	@Override
	public Product getProductName(int productId) throws Exception {
		return couponDao.getProductName(productId);
	}

	@Override
	public List<Category> getAllCategory() throws Exception {
		return couponDao.getAllCategory();
	}
	

}
