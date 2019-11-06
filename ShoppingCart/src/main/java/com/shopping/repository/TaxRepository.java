package com.shopping.repository;

import org.springframework.data.repository.CrudRepository;

import com.shopping.model.Tax;

public interface TaxRepository extends CrudRepository<Tax, Integer> {
	Tax findByTaxId(int taxId);
	

}
