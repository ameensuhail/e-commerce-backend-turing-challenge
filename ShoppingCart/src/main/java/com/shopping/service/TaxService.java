package com.shopping.service;

import java.util.List;

import org.json.simple.JSONObject;

public interface TaxService {
	List<JSONObject> getTaxes();
	JSONObject getTaxById(int taxId);

}
