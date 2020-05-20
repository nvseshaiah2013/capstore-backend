package com.cg.capstore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.capstore.entities.Category;
import com.cg.capstore.entities.Product;
import com.cg.capstore.service.IProductService;
import com.cg.capstore.util.JwtUtil;

@SpringBootApplication
@RestController
@CrossOrigin("*")
@RequestMapping("/merchant/product")
public class ProductController {

	@Autowired
	private IProductService productService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	
	@GetMapping("/hello")
	public ResponseEntity<Object> checkWorking(){
		return new ResponseEntity<Object>("Working with product...", HttpStatus.OK);
	}
	
	@PostMapping("/addProduct")
	public ResponseEntity<Object> addProduct(@RequestBody GetProduct productAndUserName, HttpServletRequest request) throws Exception{
		String username = getUsernameOfMerchant(request);
		Product product = new Product();
		product.setDiscount(0);
		product.setFeatured(false);
		product.setProductActivated(false);
		product.setProductName(productAndUserName.productName);
		product.setProductPrice(productAndUserName.productPrice);
		product.setProductBrand(productAndUserName.productBrand);
		product.setNoOfProducts(productAndUserName.noOfProducts);
		product.setNoOfViews(0);
		product.setProductRating(0);
		product.setProductInfo(productAndUserName.productInfo);
		product.setProductImage(productAndUserName.productImage);
		int category = productAndUserName.categoryName;
		String subCategory = productAndUserName.subCategory;
		
		if(productService.addProduct(product,username, category, subCategory)) {
			return new ResponseEntity<Object>("Product is added successfully...", HttpStatus.OK);
		}
		else {
			throw new Exception("Internal server Error...");
		}
	}
	
	@PostMapping("/updateproduct")
	public ResponseEntity<Object> updateStock(@RequestParam("prodId") int productId,@RequestParam("prodCount") int productCount, @RequestParam("ProdPrice") int prodctPrice, @RequestParam("Prodinfo") String ProductInfo) throws Exception{
		if(productService.updateStock(productId, productCount, prodctPrice, ProductInfo)) {
			return new ResponseEntity<Object>("Product List updated...", HttpStatus.OK);
		}
		else {
			throw new Exception("Internal server error...");
		}
	}
	
	@GetMapping("/getProduct")
	public ResponseEntity<Product> getProductName(@RequestParam("prodId") int productId) throws Exception{
		return new ResponseEntity<Product>(productService.getProductName(productId), HttpStatus.OK);
	}
	
	
	@GetMapping("/getCategory")
	public ResponseEntity<List<Category>> getAllcategory() throws Exception{
		return new ResponseEntity<List<Category>>(productService.getAllCategory(), HttpStatus.OK);
	}
	
	
	public String getUsernameOfMerchant(HttpServletRequest request) throws Exception {
		String header = request.getHeader("Authorization");
		String token = header.substring(7);
		String username = jwtUtil.extractUsername(token);
		return username;	
}
}


class GetProduct{
	public String productName;
	public double productPrice;
	public String productBrand;
	public int categoryName;
	public String subCategory;
	public int noOfProducts;
	public String productInfo;
	public String productImage;
}