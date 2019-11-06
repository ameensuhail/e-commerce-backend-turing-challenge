package com.shopping.service;

import java.util.List;

import org.json.simple.JSONObject;

import com.shopping.model.Category;

public interface CategoryService {
	//To get all Categories
		JSONObject getAllCategories();
		//To get category by id
		JSONObject getCategoryById(int id);
		//To retrieve category for a product
		JSONObject getCategoryByProduct(int productId);
		//To get categories in a department
		JSONObject getCategoriesByDepartment(int deptId);
}
