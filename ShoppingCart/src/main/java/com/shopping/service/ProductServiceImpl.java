package com.shopping.service;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.shopping.dao.ProductDao;

import com.shopping.model.Product;
import com.shopping.model.Review;
import com.shopping.repository.ProductRepository;
@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService{
	
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	ProductRepository productRepository;
	@Override
	//to complete logic for description length
	//add pagination metadata to final json
	//not used
	public JSONObject getProducts(int page,int limit,int description_length) {
		List<Product> products=productDao.getProducts(page, limit);
		List<JSONObject> prodjsons=new ArrayList<>();
		for(Product prod:products) {
			JSONObject productjson=new JSONObject();
			productjson.put("category_id", prod.getProduct_id());
			productjson.put("name", prod.getName());
			productjson.put("description", prod.getDescription());
			productjson.put("price", prod.getPrice());
			productjson.put("discounted_price", prod.getDiscounted_price());
			productjson.put("thumbnail", prod.getThumbnail());
			
			prodjsons.add(productjson);
		}
		JSONObject finalprodjson=new JSONObject();
		finalprodjson.put("rows", prodjsons);
		return finalprodjson;
	}
	
	

	@Override
	public JSONObject searchProduct(String searchQuery, String allwords) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getProductById(int product_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getProductsByCategory(int category_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getProductByDepartment(int dept_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getReviewsByProduct(int product_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject postReviewByProduct(int product_id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public JSONObject getProductsPagination(int page, int limit, int description_length) {
		Pageable paging = PageRequest.of(page, limit);
		 
		Page<Product> pagedResult = productRepository.findAllProductsWithPagination(paging);
		List<Product> products=pagedResult.getContent();
		List<JSONObject> prodjsons=new ArrayList<>();
		for(Product prod:products) {
			JSONObject productjson=new JSONObject();
			productjson.put("product_id", prod.getProduct_id());
			productjson.put("name", prod.getName());
			//to truncate description length
			String description=prod.getDescription();
			productjson.put("description",description.substring(0, Math.min(description.length(),description_length)) );
			productjson.put("price", prod.getPrice());
			productjson.put("discounted_price", prod.getDiscounted_price());
			productjson.put("thumbnail", prod.getThumbnail());
			
			prodjsons.add(productjson);
		}
		JSONObject pagemeta=new JSONObject();
		pagemeta.put("page", page+1);
		pagemeta.put("currentPageSize",pagedResult.getSize());
		pagemeta.put("totalPages", pagedResult.getTotalPages());
		pagemeta.put("totalRecords", pagedResult.getTotalElements());
		
		JSONObject finalprodjson=new JSONObject();
		finalprodjson.put("paginationMeta", pagemeta);
		finalprodjson.put("rows", prodjsons);
		return finalprodjson;
		
		
	}


	@Override
	public JSONObject searchProductsPagination(int page, int limit, int description_length, String query, String allwords) {
		Pageable paging = PageRequest.of(page, limit);
		 
		Page<Product> pagedResult = productRepository.searchProductsWithPagination(paging, query);
		//Page<Product> pagedResult = productRepository.findByNameIgnoreCaseContainingOrDescriptionIgnoreCaseContaining(paging, query);
		List<Product> products=pagedResult.getContent();
		List<JSONObject> prodjsons=new ArrayList<>();
		for(Product prod:products) {
			JSONObject productjson=new JSONObject();
			productjson.put("product_id", prod.getProduct_id());
			productjson.put("name", prod.getName());
			//to truncate description length
			String description=prod.getDescription();
			productjson.put("description",description.substring(0, Math.min(description.length(),description_length)) );
			productjson.put("price", prod.getPrice());
			productjson.put("discounted_price", prod.getDiscounted_price());
			productjson.put("thumbnail", prod.getThumbnail());
			
			prodjsons.add(productjson);
		}
		//JSONObject pagemeta=new JSONObject();
		//pagemeta.put("page", page+1);
		//pagemeta.put("currentPageSize",pagedResult.getSize());
		//pagemeta.put("totalPages", pagedResult.getTotalPages());
		//pagemeta.put("totalRecords", pagedResult.getTotalElements());
		//
		JSONObject finalprodjson=new JSONObject();
		//finalprodjson.put("paginationMeta", pagemeta);
		finalprodjson.put("rows", prodjsons);
		return finalprodjson;
	}



	@Override
	public JSONObject getProductById(int product_id, int description_length) {
		Product prod=productRepository.getProductById(product_id);
		//System.out.println(prod.getCategory().getCategory_id());
		JSONObject productjson=new JSONObject();
		productjson.put("product_id", prod.getProduct_id());
		productjson.put("name", prod.getName());
		//to truncate description length
		String description=prod.getDescription();
		productjson.put("description",description.substring(0, Math.min(description.length(),description_length)) );
		productjson.put("price", prod.getPrice());
		productjson.put("discounted_price", prod.getDiscounted_price());
		productjson.put("image", prod.getImage());
		productjson.put("image_2", prod.getImage_2());
		productjson.put("thumbnail", prod.getThumbnail());
		productjson.put("display", prod.getDisplay());
		return productjson;
	}



	@Override
	public JSONObject getProductsByCategoryPagination(int category_id, int page, int limit, int description_length) {
		Pageable paging = PageRequest.of(page, limit);
		 
		Page<Product> pagedResult = productRepository.findAllProductsByCategoryWithPagination(paging, category_id);
		List<Product> products=pagedResult.getContent();
		List<JSONObject> prodjsons=new ArrayList<>();
		for(Product prod:products) {
			JSONObject productjson=new JSONObject();
			productjson.put("product_id", prod.getProduct_id());
			productjson.put("name", prod.getName());
			//to truncate description length
			String description=prod.getDescription();
			productjson.put("description",description.substring(0, Math.min(description.length(),description_length)) );
			productjson.put("price", prod.getPrice());
			productjson.put("discounted_price", prod.getDiscounted_price());
			productjson.put("thumbnail", prod.getThumbnail());
			
			prodjsons.add(productjson);
		}
		
		
		JSONObject finalprodjson=new JSONObject();
		
		finalprodjson.put("rows", prodjsons);
		return finalprodjson;

	}



	@Override
	public JSONObject getProductsByDepartmentPagination(int department_id, int page, int limit,
			int description_length) {
		Pageable paging = PageRequest.of(page, limit);
		 
		Page<Product> pagedResult = productRepository.findAllProductsByDepartmentWithPagination(paging, department_id);
		List<Product> products=pagedResult.getContent();
		List<JSONObject> prodjsons=new ArrayList<>();
		for(Product prod:products) {
			JSONObject productjson=new JSONObject();
			productjson.put("product_id", prod.getProduct_id());
			productjson.put("name", prod.getName());
			//to truncate description length
			String description=prod.getDescription();
			productjson.put("description",description.substring(0, Math.min(description.length(),description_length)) );
			productjson.put("price", prod.getPrice());
			productjson.put("discounted_price", prod.getDiscounted_price());
			productjson.put("thumbnail", prod.getThumbnail());
			
			prodjsons.add(productjson);
		}
		
		
		JSONObject finalprodjson=new JSONObject();
		
		finalprodjson.put("rows", prodjsons);
		return finalprodjson;
	}



	@Override
	public List<JSONObject> getReviewsByProductId(int product_id) {
		List<Object[]> lst=productRepository.findReviewsByProduct(product_id);
		List<JSONObject> reviewjsons=new ArrayList<>();
		for(Object o[]:lst) {
			JSONObject reviewjson=new JSONObject();
			String name=(String)o[0];
			Review r=(Review)o[1];
			reviewjson.put("name", name);
			reviewjson.put("review", r.getReview());
			reviewjson.put("rating",r.getRating());
			reviewjson.put("created_on", r.getCreated_on());
			reviewjsons.add(reviewjson);
			
		}
		//System.out.println("hi");
		//System.out.println(reviewjsons);
		return reviewjsons;
	}

}
