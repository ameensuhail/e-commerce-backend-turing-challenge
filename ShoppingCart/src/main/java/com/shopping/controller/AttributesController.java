package com.shopping.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.service.AttributeService;

@RestController
public class AttributesController {
	
	@Autowired
	AttributeService attributeService;
	
	@GetMapping("/attributes")
    public ResponseEntity<List<JSONObject>> getAllCategories() {

		List<JSONObject> attributeListJSON=attributeService.getAllAttributes();
		
        
        return ResponseEntity.ok().body(attributeListJSON);
    }
	@GetMapping("/attributes/{attribute_id}")
    public ResponseEntity<JSONObject> getAttributeById(@PathVariable("attribute_id") int id) {

		JSONObject attributeJSON=attributeService.getAttributeById(id);
		
        
        return ResponseEntity.ok().body(attributeJSON);
    }
	@GetMapping("/attributes/values/{attribute_id}")
    public ResponseEntity<List<JSONObject>> getAttributeValuesById(@PathVariable("attribute_id") int id) {

		List<JSONObject> attributeValuesJSON=attributeService.getAttributeValuesByAttributeId(id);
		
        
        return ResponseEntity.ok().body(attributeValuesJSON);
    }
	@GetMapping("/attributes/inProduct/{product_id}")
    public ResponseEntity<List<JSONObject>> getAttributeValuesByProduct(@PathVariable("product_id") int productId) {

		List<JSONObject> attributeValuesJSON=attributeService.getAttributeValuesByProduct(productId);
		
        
        return ResponseEntity.ok().body(attributeValuesJSON);
    }

}
