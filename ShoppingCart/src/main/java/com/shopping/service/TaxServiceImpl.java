package com.shopping.service;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.model.Tax;
import com.shopping.repository.TaxRepository;
@Service
public class TaxServiceImpl implements TaxService {
	@Autowired
	TaxRepository taxRepository;

	@Override
	public List<JSONObject> getTaxes() {
		Iterable<Tax> taxes=taxRepository.findAll();
		List<JSONObject> taxJsonList=new ArrayList<JSONObject>();
		for(Tax tax:taxes) {
			JSONObject taxJson=new JSONObject();
			taxJson.put("tax_id", tax.getTaxId());
			taxJson.put("tax_type", tax.getTax_type());
			taxJson.put("tax_percentage", tax.getTax_percentage());
			taxJsonList.add(taxJson);
			
			
		}
		return taxJsonList;
		
	}

	@Override
	public JSONObject getTaxById(int taxId) {
		Tax tax=taxRepository.findByTaxId(taxId);
		JSONObject taxJson=new JSONObject();
		taxJson.put("tax_id", tax.getTaxId());
		taxJson.put("tax_type", tax.getTax_type());
		taxJson.put("tax_percentage", tax.getTax_percentage());
		return taxJson;
		
		
	
	}

}
