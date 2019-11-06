package com.shopping.service;

import java.util.List;

import org.json.simple.JSONObject;

public interface AttributeService {
	
	public List<JSONObject> getAllAttributes();
	public JSONObject getAttributeById(int attributeId);
	public List<JSONObject> getAttributeValuesByAttributeId(int attributeId);
	public List<JSONObject> getAttributeValuesByProduct(int productId);
	

}
