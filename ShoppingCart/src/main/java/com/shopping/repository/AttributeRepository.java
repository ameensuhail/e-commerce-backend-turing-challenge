package com.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.shopping.model.Attribute;
import com.shopping.model.AttributeValue;

public interface AttributeRepository extends CrudRepository<Attribute, Integer> {
	
	@Query(value="select attributeValues from Attribute a where a.attributeId=:attributeId")
	List<AttributeValue> findAttributeValuesByAttributeId(@Param("attributeId") int attributeId);
	Attribute findByAttributeId(int attributeId);
	
	@Query(value="select attributeValues from Product p where p.product_id=:productId")
	List<AttributeValue> findAttributeValuesByProduct(@Param("productId") int productId);

}
