package com.shopping.service;

import java.util.List;

import org.json.simple.JSONObject;

import com.shopping.model.Product;

public interface ProductService {
	
	JSONObject getProducts(int page,int limit,int description_length);
	JSONObject searchProduct(String searchQuery,String allwords);
	JSONObject getProductById(int product_id);
	JSONObject getProductsByCategory(int category_id);
	JSONObject getProductByDepartment(int dept_id);
	JSONObject getReviewsByProduct(int product_id);
	JSONObject postReviewByProduct(int product_id);
	//using spring-jpa-data
	//get all products
	JSONObject getProductsPagination(int page,int limit,int description_length);
	//allwords logic not complete
	JSONObject searchProductsPagination(int page, int limit, int description_length,String query,String allwords);
	//get product by id//complete
	JSONObject getProductById(int product_id,int description_length);
	//get products by category
	JSONObject getProductsByCategoryPagination(int category_id,int page,int limit,int description_length);
	//get products by department
	JSONObject getProductsByDepartmentPagination(int department_id,int page,int limit,int description_length);
	//to get reviews of product
	List<JSONObject> getReviewsByProductId(int product_id);
	

}
