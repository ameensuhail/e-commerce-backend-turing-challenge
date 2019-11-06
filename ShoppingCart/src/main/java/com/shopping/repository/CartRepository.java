package com.shopping.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shopping.model.Product;
import com.shopping.model.ShoppingCart;

@Repository
public interface CartRepository extends CrudRepository<ShoppingCart, Integer>{
	List<ShoppingCart> findByCartId(String cartid);
	Long deleteByCartId(String cartId);
	

}
