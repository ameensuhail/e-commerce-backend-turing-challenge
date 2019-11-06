package com.shopping.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.model.Attribute;
import com.shopping.model.AttributeValue;
import com.shopping.repository.AttributeRepository;
@Service
@Transactional(readOnly = true)
public class AttributeServiceImpl implements AttributeService{
	
	@Autowired
	AttributeRepository attributeRepository;
	
	
	@Override
	public List<JSONObject> getAllAttributes() {
		List<Attribute> attributes=(List<Attribute>) attributeRepository.findAll();
		List<JSONObject> attributejsons=new ArrayList<>();
		for(Attribute attribute:attributes) {
			JSONObject Attributejson=new JSONObject();
			Attributejson.put("attribute_id", attribute.getAttributeId());
			Attributejson.put("name", attribute.getName());
			attributejsons.add(Attributejson);
		}
	
		return attributejsons;
	}

	@Override
	public JSONObject getAttributeById(int attributeId) {
		Attribute attribute=attributeRepository.findByAttributeId(attributeId);
		JSONObject attributejson=new JSONObject();
		attributejson.put("attribute_id", attribute.getAttributeId());
		attributejson.put("name", attribute.getName());
		
		return attributejson;
	}

	@Override
	public List<JSONObject> getAttributeValuesByAttributeId(int attributeId) {
		List<AttributeValue> attributeValues=attributeRepository.findAttributeValuesByAttributeId(attributeId);
		List<JSONObject> attributejsons=new ArrayList<>();
		for(AttributeValue attributeValue:attributeValues) {
			JSONObject Attributejson=new JSONObject();
			Attributejson.put("attribute_value_id", attributeValue.getAttributeValueId());
			Attributejson.put("value", attributeValue.getValue());
			attributejsons.add(Attributejson);
		}
	
		return attributejsons;
	}

	@Override
	public List<JSONObject> getAttributeValuesByProduct(int productId) {
		List<AttributeValue> attributeValues=attributeRepository.findAttributeValuesByProduct(productId);
		List<JSONObject> attributejsons=new ArrayList<>();
		for(AttributeValue attributeValue:attributeValues) {
			JSONObject Attributejson=new JSONObject();
			Attributejson.put("attribute_name", attributeValue.getAttribute().getName());
			Attributejson.put("attribute_value_id", attributeValue.getAttributeValueId());
			Attributejson.put("value", attributeValue.getValue());
			attributejsons.add(Attributejson);
		}
	
		return attributejsons;
	}
	
	

}
