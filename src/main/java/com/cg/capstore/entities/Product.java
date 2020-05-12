package com.cg.capstore.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="PRODUCTS")
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="PROD_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int productId;
	
	@Column(name="PROD_NAME")
	private String productName;
	
	@Column(name="PROD_IMAGE")
	private String productImage;
	
	@Column(name="PROD_PRICE")
	private double productPrice;
	
	@Column(name="PROD_RATING")
	private int productRating;
	
	@Column(name="PROD_VIEWS")
	private int noOfViews;
	
	@Column(name="PROD_BRAND")
	private String productBrand;
	
	@Column(name="PROD_COUNT")
	private int noOfProducts;
	
	@Column(name="PROD_INFO")
	private String productInfo;
	
	@ManyToOne
	@JoinColumn(name="PROD_CATEGORY")
	private Category productCategory;
	
	@ManyToOne
	@JoinColumn(name="SUB_CATEGORY")
	private Category subCategory;
	
	@Column(name="PRODUCT_IS_ACTIVATED")
	private boolean productActivated;
	
	@Column(name="FEATURED")
	private boolean isFeatured;
	
	@ManyToOne
	@JoinColumn(name="M_USERNAME")
	private MerchantDetails merchant;
	
	@OneToMany(mappedBy="product")
	private Set<ProductFeedback> feedbacks = new HashSet<>();


	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductImage() {
		return productImage;
	}


	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}


	public double getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}


	public int getProductRating() {
		return productRating;
	}


	public void setProductRating(int productRating) {
		this.productRating = productRating;
	}


	public int getNoOfViews() {
		return noOfViews;
	}


	public void setNoOfViews(int noOfViews) {
		this.noOfViews = noOfViews;
	}


	public String getProductBrand() {
		return productBrand;
	}


	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}


	public int getNoOfProducts() {
		return noOfProducts;
	}


	public void setNoOfProducts(int noOfProducts) {
		this.noOfProducts = noOfProducts;
	}


	public String getProductInfo() {
		return productInfo;
	}


	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}

	public boolean isProductActivated() {
		return productActivated;
	}


	public void setProductActivated(boolean productActivated) {
		this.productActivated = productActivated;
	}


	public Set<ProductFeedback> getFeedbacks() {
		return feedbacks;
	}


	public void setFeedbacks(Set<ProductFeedback> feedbacks) {
		this.feedbacks = feedbacks;
	}


	public boolean isFeatured() {
		return isFeatured;
	}


	public void setFeatured(boolean isFeatured) {
		this.isFeatured = isFeatured;
	}

	
	
}
