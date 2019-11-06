package com.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shopping.model.Category;
@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
	List<Category> findAll();
	Category findByCategoryId(int id);
	//Category findByProducts(Product product);
	@Query(value="select c from Category c JOIN c.products p where p.product_id=:productId")
	Category getCategoryByProduct(@Param("productId") int productId);
	@Query(value="select c from Category c where c.department.departmentId=:deptId")
	List<Category> getCategoryByDepartment(@Param("deptId")int deptId);
	

	
	
}
