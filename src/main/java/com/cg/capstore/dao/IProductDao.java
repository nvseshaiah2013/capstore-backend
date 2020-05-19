package com.cg.capstore.dao;

import java.util.List;

import com.cg.capstore.entities.Category;
import com.cg.capstore.entities.Product;

public interface IProductDao {
    boolean addProduct(Product product, String merchantUserName, int category, String subCategory) throws Exception;
    boolean updateStock(int productId, int productCount, double prodPrice, String productInfo) throws Exception;
    Product getProductName(int productId) throws Exception;
    List<Category> getAllCategory() throws Exception;
}
