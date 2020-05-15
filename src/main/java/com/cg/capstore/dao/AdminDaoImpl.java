package com.cg.capstore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


import org.springframework.stereotype.Repository;

import com.cg.capstore.entities.Address;
import com.cg.capstore.entities.Category;
import com.cg.capstore.entities.CustomerDetails;
import com.cg.capstore.entities.SubCategory;

@Repository
public class AdminDaoImpl implements IAdminDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<CustomerDetails> getCustomerList() {
		String str="SELECT customer FROM CustomerDetails customer";
		TypedQuery<CustomerDetails> query=entityManager.createQuery(str, CustomerDetails.class);
		List<CustomerDetails> customers=query.getResultList();
		return customers;
		
	}

	@Override
	public List<Address> getAddressByUsername(String username) {
		String str="SELECT address FROM Address address WHERE address.user.username=:username";
		TypedQuery<Address> query=entityManager.createQuery(str, Address.class);
		query.setParameter("username", username);
		List<Address> addresses=query.getResultList();
		return addresses;
	}

	@Override
	public List<Category> addCategory(Category category) {
		entityManager.persist(category);
		String str="SELECT categories FROM Category categories";
		TypedQuery<Category> query=entityManager.createQuery(str, Category.class);
		List<Category> categories=query.getResultList();
		return categories;
	}

	@Override
	public List<Category> getAllCategory() {
		String str="SELECT categories FROM Category categories";
		TypedQuery<Category> query=entityManager.createQuery(str, Category.class);
		List<Category> categories=query.getResultList();
		return categories;
	}

	@Override
	public List<Category> deleteCategory(int id) {
		Category category=entityManager.find(Category.class, id);
		if(category!=null)
		entityManager.remove(category);
		String str="SELECT categories FROM Category categories";
		TypedQuery<Category> query=entityManager.createQuery(str, Category.class);
		List<Category> categories=query.getResultList();
		return categories;
		
	}

	@Override
	public List<SubCategory> addSubCategory(SubCategory subCategory, int categoryId) {
		
		Category category=entityManager.find(Category.class, categoryId);
		category.addSubCategory(subCategory);
		entityManager.merge(category);
		String str="SELECT subCategories FROM SubCategory subCategories WHERE subCategories.category.id=:categoryId";
		TypedQuery<SubCategory> query=entityManager.createQuery(str, SubCategory.class);
		query.setParameter("categoryId", categoryId);
		List<SubCategory> subCategories=query.getResultList();
		return subCategories;
		
	}

	@Override
	public List<SubCategory> getAllSubCategory(int categoryId) {
		String str="SELECT subCategories FROM SubCategory subCategories WHERE subCategories.category.id=:id";
		TypedQuery<SubCategory> query=entityManager.createQuery(str, SubCategory.class);
		query.setParameter("id", categoryId);
		List<SubCategory> subCategories=query.getResultList();
		return subCategories;
	}

	@Override
	public List<SubCategory> deleteSubCategory(int categoryId,int subCategoryId) {
		SubCategory subCategory=entityManager.find(SubCategory.class, subCategoryId);
		System.out.println(subCategory);
		entityManager.remove(subCategory);
		System.out.println("deleted");
		String str="SELECT subCategories FROM SubCategory subCategories WHERE subCategories.category.id=:id";
		TypedQuery<SubCategory> query=entityManager.createQuery(str, SubCategory.class);
		query.setParameter("id", categoryId);
		List<SubCategory> subCategories=query.getResultList();
		return subCategories;
	}

}
