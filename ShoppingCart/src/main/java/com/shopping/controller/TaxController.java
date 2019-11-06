package com.shopping.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.service.TaxService;

@RestController
public class TaxController {
	
	@Autowired
	TaxService taxService;
	
	@GetMapping("/tax")
	public ResponseEntity<List<JSONObject>> getAllTaxes() {
		
		
		List<JSONObject> taxJsonList=taxService.getTaxes();
		
        
        return ResponseEntity.ok().body(taxJsonList);
    }
	@GetMapping("/tax/{tax_id}")
	public ResponseEntity<JSONObject> getTaxById(@PathVariable("tax_id") int taxId) {
		
		
		JSONObject taxJson=taxService.getTaxById(taxId);
		
        
        return ResponseEntity.ok().body(taxJson);
    }

}
