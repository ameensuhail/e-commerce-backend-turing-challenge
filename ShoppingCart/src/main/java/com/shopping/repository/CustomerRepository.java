package com.shopping.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shopping.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer>{
	Customer findByEmail(String email);
	
}
