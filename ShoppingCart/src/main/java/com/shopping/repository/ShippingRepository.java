package com.shopping.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.shopping.model.Shipping;
import com.shopping.model.ShippingRegion;

public interface ShippingRepository extends CrudRepository<Shipping, Integer> {
	Shipping findByShippingId(int shipping_id);
	
	@Query("select shippingRegion from Shipping")
	List<ShippingRegion> getShippingRegions();
	
	//important
	List<Shipping> findByShippingRegion_ShippingRegionId(int shippingRegionId);

}
