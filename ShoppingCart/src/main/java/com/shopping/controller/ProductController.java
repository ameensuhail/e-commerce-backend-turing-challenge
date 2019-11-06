package com.shopping.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	@GetMapping("/products")
	public ResponseEntity<JSONObject> getAllProducts(@RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "20") int limit,@RequestParam(value = "description_length", defaultValue = "200") int description_length) {
		
		
		JSONObject productListJSON=productService.getProductsPagination(page-1, limit, description_length);
		
        
        return ResponseEntity.ok().body(productListJSON);
    }
	@GetMapping("/products/search")
	public ResponseEntity<JSONObject> searchProducts(@RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "20") int limit,@RequestParam(value = "description_length", defaultValue = "200") int description_length,
            @RequestParam(value = "query_string") String searchQuery,@RequestParam(value = "all_words",defaultValue = "on") String allwords) {
		
		
		JSONObject productListJSON=productService.searchProductsPagination(page-1, limit, description_length, searchQuery, allwords);
		
        
        return ResponseEntity.ok().body(productListJSON);
    }
	@GetMapping("/products/{product_id}")
	public ResponseEntity<JSONObject> getProductById(@PathVariable("product_id") int product_id,
            @RequestParam(value = "description_length", defaultValue = "200") int description_length) {
		
		
		JSONObject productJSON=productService.getProductById(product_id, description_length);
		
        
        return ResponseEntity.ok().body(productJSON);
    }
	@GetMapping("/products/inCategory/{category_id}")
	public ResponseEntity<JSONObject> getAllProductsByCategory(@RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "20") int limit,@RequestParam(value = "description_length", defaultValue = "200") int description_length,
            @PathVariable("category_id") int category_id) {
		
		
		JSONObject productListJSON=productService.getProductsByCategoryPagination(category_id, page-1, limit, description_length);
		
        
        return ResponseEntity.ok().body(productListJSON);
    }
	
	@GetMapping("/products/inDepartment/{department_id}")
	public ResponseEntity<JSONObject> getAllProductsByDepartment(@RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "20") int limit,@RequestParam(value = "description_length", defaultValue = "200") int description_length,
            @PathVariable("department_id") int department_id) {
		
		
		JSONObject productListJSON=productService.getProductsByDepartmentPagination(department_id, page, limit, description_length);
		
        
        return ResponseEntity.ok().body(productListJSON);
    }
	
	@GetMapping("/products/{product_id}/reviews")
	public ResponseEntity<List<JSONObject>> getReviewsByProduct(@PathVariable("product_id") int product_id) {
		
		
		List<JSONObject> productJSON=productService.getReviewsByProductId(product_id);
		//productJSON.put("hi", "hi");
        
        return ResponseEntity.ok().body(productJSON);
    }
	
	

}
