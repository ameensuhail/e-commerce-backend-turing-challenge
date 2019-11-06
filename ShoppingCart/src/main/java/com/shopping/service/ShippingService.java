package com.shopping.service;

import java.util.List;

import org.json.simple.JSONObject;

public interface ShippingService {
	List<JSONObject> getAllShippingRegions();
	List<JSONObject> getShippingsInRegion(int regionId) throws Exception;
}
