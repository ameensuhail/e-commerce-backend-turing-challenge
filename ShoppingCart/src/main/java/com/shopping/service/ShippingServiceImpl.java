package com.shopping.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.exceptionhandling.ServiceException;
import com.shopping.model.Shipping;
import com.shopping.model.ShippingRegion;
import com.shopping.repository.ShippingRepository;

@Service
public class ShippingServiceImpl implements ShippingService{
	@Autowired
	ShippingRepository shippingRepository;

	@Override
	public List<JSONObject> getAllShippingRegions() {
		List<JSONObject> shippingRegionJsonList=new ArrayList<JSONObject>();
		List<ShippingRegion> shippingRegions=shippingRepository.getShippingRegions();
		for(ShippingRegion shippingRegion:shippingRegions) {
			JSONObject shippingRegionJson=new JSONObject();
			shippingRegionJson.put("shipping_region_id",shippingRegion.getShippingRegionId());
			shippingRegionJson.put("shipping_region",shippingRegion.getShippingRegion());
			shippingRegionJsonList.add(shippingRegionJson);
		}
		return shippingRegionJsonList;
		
	}

	@Override
	public List<JSONObject> getShippingsInRegion(int regionId) throws Exception {
		List<JSONObject> shippingJsonList=new ArrayList<JSONObject>();
		try {
		List<Shipping> shippingList=shippingRepository.findByShippingRegion_ShippingRegionId(regionId);
		//System.out.println(shippingOptional.isPresent());
		
			//System.out.println("HI");
		
		for(Shipping shipping:shippingList) {
			//System.out.println(shipping.getShippingId());
			//System.out.println(shipping.getShippingId());
			JSONObject shippingJson=new JSONObject();
			shippingJson.put("shipping_id", shipping.getShippingId());
			shippingJson.put("shipping_type", shipping.getShipping_type());
			shippingJson.put("shipping_cost", shipping.getShipping_cost());
			shippingJson.put("shipping_region_id", shipping.getShippingRegion().getShippingRegionId());
			shippingJsonList.add(shippingJson);
		}
		
		return shippingJsonList;
		}catch (Exception e) {
			throw new ServiceException();
		}
		
		
	}
	
	

}
