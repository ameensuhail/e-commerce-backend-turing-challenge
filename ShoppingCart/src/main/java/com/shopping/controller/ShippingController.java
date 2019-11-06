package com.shopping.controller;

import java.util.ArrayList;
import java.util.List;


import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.exceptionhandling.ResourceNotFoundException;
import com.shopping.exceptionhandling.ServiceException;
import com.shopping.service.ShippingService;

@RestController
public class ShippingController {
	
	@Autowired
	ShippingService shippingService;
	
	@GetMapping("/shipping/regions")
	public ResponseEntity<List<JSONObject>> getShippingRegions() {
		
		
		List<JSONObject> shippingRegionsonList=shippingService.getAllShippingRegions();
		
        
        return ResponseEntity.ok().body(shippingRegionsonList);
    }
	@GetMapping("/shipping/regions/{shipping_region_id}")
	public ResponseEntity<List<JSONObject>> getShippingsInRegion(@PathVariable("shipping_region_id") int regionId) throws Exception {
		
		
		//List<JSONObject> shippingJsonList=new ArrayList<JSONObject>();
		try {
			List<JSONObject> shippingJsonList = shippingService.getShippingsInRegion(regionId);
			if(shippingJsonList.isEmpty()) {
				throw new ResourceNotFoundException("No Shippings Available for Region "+regionId);
			}
			return ResponseEntity.ok().body(shippingJsonList);
			
		} catch (ServiceException e) {
			//System.out.println(e.getMessage());
			throw new Exception("Internal Server Error");
			
		}
		
        
        
    }
}
