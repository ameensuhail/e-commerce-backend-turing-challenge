package com.shopping.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shopping.model.Product;
@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer>{
	@Query(value = "SELECT p FROM Product p")
	Page<Product> findAllProductsWithPagination(Pageable pageable);
	
	/*@Query(value = "SELECT * FROM Product p WHERE " +
            "LOWER(p.name) LIKE LOWER(CONCAT('%',:searchTerm, '%')) OR " +
            "LOWER(p.description) LIKE LOWER(CONCAT('%',:searchTerm, '%'))",
            nativeQuery = true
    )*/
	@Query("select p from Product p where upper(p.name) like concat('%',upper(:searchTerm), '%') or upper(p.description) like concat('%', upper(:searchTerm), '%')")
	Page<Product> searchProductsWithPagination(Pageable pageable,@Param("searchTerm") String searchTerm);
	//Page<Product> findByNameIgnoreCaseContainingOrDescriptionIgnoreCaseContaining(Pageable pageable,String searchTerm);
	
	
	@Query(value = "SELECT p FROM Product p WHERE p.product_id=:product_id")
	Product getProductById(@Param("product_id") int product_id);
	
	@Query(value = "SELECT p FROM Product p where p.category.categoryId=:category_id")
	Page<Product> findAllProductsByCategoryWithPagination(Pageable pageable,@Param("category_id") int category_id);
	
	@Query(value = "SELECT p FROM Product p where p.category.department.departmentId=:department_id")
	Page<Product> findAllProductsByDepartmentWithPagination(Pageable pageable,@Param("department_id") int department_id);
	
	
	@Query(value = "SELECT p.name,r FROM Product p,Review r WHERE p.product_id=r.product.product_id AND r.product.product_id=:product_id")
	List<Object[]> findReviewsByProduct(@Param("product_id") int product_id);

}
