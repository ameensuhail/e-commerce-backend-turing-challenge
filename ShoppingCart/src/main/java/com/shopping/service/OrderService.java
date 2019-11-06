package com.shopping.service;

import java.util.List;

import org.json.simple.JSONObject;

import com.shopping.model.Order;

public interface OrderService {

	public JSONObject placeOrder(String authToken, String cart_id, int shipping_id, int tax_id);

	public JSONObject getOrderById(int orderId);

	public List<JSONObject> getOrderByCustomer(String authToken);

	public JSONObject getShortOrderById(int orderId);

}
