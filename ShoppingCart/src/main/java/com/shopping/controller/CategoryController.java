package com.shopping.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.model.Category;
import com.shopping.model.Department;
import com.shopping.service.CategoryService;

@RestController
public class CategoryController {

	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/categories")
    public ResponseEntity<JSONObject> getAllCategories() {

		JSONObject categoryListJSON=categoryService.getAllCategories();
		
        
        return ResponseEntity.ok().body(categoryListJSON);
    }
	
	@GetMapping("/categories/{category_id}")
	public ResponseEntity<JSONObject> getCategory(@PathVariable("category_id") int id) {

			//List<Department> departmentList=departmentService.getAllDepartments();
	        JSONObject category=categoryService.getCategoryById(id);
	        return ResponseEntity.ok().body(category);
	   }
	@GetMapping("/categories/inProduct/{product_id}")
	public ResponseEntity<JSONObject> getCategoryByProduct(@PathVariable("product_id") int prod_id) {

			//List<Department> departmentList=departmentService.getAllDepartments();
	        JSONObject category=categoryService.getCategoryByProduct(prod_id);
	        return ResponseEntity.ok().body(category);
	   }
	@GetMapping("/categories/inDepartment/{department_id}")
	public ResponseEntity<JSONObject> getCategoryByDepartment(@PathVariable("department_id") int dept_id) {

			//List<Department> departmentList=departmentService.getAllDepartments();
	        JSONObject category=categoryService.getCategoriesByDepartment(dept_id);
	        return ResponseEntity.ok().body(category);
	   }
	

	
	
}
