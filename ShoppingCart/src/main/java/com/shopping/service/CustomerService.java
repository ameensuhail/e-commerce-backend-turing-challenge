package com.shopping.service;

import org.json.simple.JSONObject;

import com.shopping.model.Customer;

public interface CustomerService {
	public JSONObject createCustomer(String name,String email,String password);
	//get customer details given auth token
	public JSONObject getcustomerdetails(String authToken);
	//update customer details given auth token
	public JSONObject updatecustomerdetails(String authToken, String email, String name, String day_phone,
			String mob_phone);
	//Update Customer Address given auth token
	public JSONObject updatecustomerAddress(String authToken, String address_1, String address_2, String city,
			String region, String postal_code, int shipping_region_id);
	//Update Customer Credit Card Number
	public JSONObject updatecustomerCreditCard(String authToken, String credit_card);
	

}
