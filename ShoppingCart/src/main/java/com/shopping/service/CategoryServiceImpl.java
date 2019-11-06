package com.shopping.service;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.util.JSONPObject;

import com.shopping.dao.CategoryDao;
import com.shopping.model.Category;
import com.shopping.repository.CategoryRepository;
@Service
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {
	
//	@Autowired
//	CategoryDao categoryDao;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public JSONObject getAllCategories() {
		
		//List<Category> categories=categoryDao.getAllCategories();
		List<Category> categories=categoryRepository.findAll();
		List<JSONObject> catjsons=new ArrayList<>();
		for(Category cat:categories) {
			JSONObject categoryjson=new JSONObject();
			categoryjson.put("category_id", cat.getCategoryId());
			categoryjson.put("name", cat.getName());
			categoryjson.put("description", cat.getDescription());
			categoryjson.put("department_id", cat.getDepartment().getDepartmentId());
			catjsons.add(categoryjson);
		}
		JSONObject finalcategoryjson=new JSONObject();
		finalcategoryjson.put("rows", catjsons);
		return finalcategoryjson;
	}

	@Override
	public JSONObject getCategoryById(int id) {
		
		//Category category=categoryDao.getCategoryById(id);
		Category category=categoryRepository.findByCategoryId(id);
		JSONObject categoryjson=new JSONObject();
		categoryjson.put("category_id", category.getCategoryId());
		categoryjson.put("name", category.getName());
		categoryjson.put("description", category.getDescription());
		categoryjson.put("department_id", category.getDepartment().getDepartmentId());
		return categoryjson;
		
		
	}

	@Override
	public JSONObject getCategoryByProduct(int productId) {
		// TODO Auto-generated method stub
		//Category category=categoryDao.getCategoryByProduct(productid);
		Category category=categoryRepository.getCategoryByProduct(productId);
		JSONObject categoryjson=new JSONObject();
		categoryjson.put("category_id", category.getCategoryId());
		categoryjson.put("department_id", category.getDepartment().getDepartmentId());
		categoryjson.put("name", category.getName());
		return categoryjson;
		
	}

	@Override
	public JSONObject getCategoriesByDepartment(int deptId) {
		//List<Category> categories=categoryDao.getCategoriesByDepartment(deptid);
		List<Category> categories=categoryRepository.getCategoryByDepartment(deptId);
		List<JSONObject> catjsons=new ArrayList<>();
		for(Category cat:categories) {
			JSONObject categoryjson=new JSONObject();
			categoryjson.put("category_id", cat.getCategoryId());
			categoryjson.put("name", cat.getName());
			categoryjson.put("description", cat.getDescription());
			categoryjson.put("department_id", cat.getDepartment().getDepartmentId());
			catjsons.add(categoryjson);
		}
		JSONObject finalcategoryjson=new JSONObject();
		finalcategoryjson.put("rows", catjsons);
		return finalcategoryjson;
	}


}
