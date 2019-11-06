package com.shopping.service;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.config.JwtTokenUtil;
import com.shopping.model.Customer;
import com.shopping.repository.CustomerRepository;
@Service

public class CustomerServiceImpl implements CustomerService{
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	private JwtUserDetailsService userDetailsService;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public JSONObject createCustomer(String name, String email, String password){
		// TODO Auto-generated method stub
		Customer customer=new Customer();
		customer.setName(name);
		customer.setEmail(email);
		//customer.setPassword(password);
		customer.setPassword(bcryptEncoder.encode(password));
		Customer savedCustomer=customerRepository.save(customer);
		//ObjectMapper om=new ObjectMapper();
		//String customerjsontext=om.writeValueAsString(savedCustomer);
		JSONObject customerJson=new JSONObject();
		customerJson.put("customer_id", savedCustomer.getCustomer_id());
		customerJson.put("name", savedCustomer.getName());
		customerJson.put("email", savedCustomer.getEmail());
		customerJson.put("address_1",savedCustomer.getAddress_1());
		customerJson.put("address_2", savedCustomer.getAddress_2());
		customerJson.put("city", savedCustomer.getCity());
		customerJson.put("postal_code", savedCustomer.getPostal_code());
		customerJson.put("shipping_region_id", savedCustomer.getShipping_region_id());
		customerJson.put("credit_card", savedCustomer.getCredit_card());
		customerJson.put("day_phone", savedCustomer.getDay_phone());
		customerJson.put("eve_phone", savedCustomer.getEve_phone());
		customerJson.put("mob_phone", savedCustomer.getMob_phone());
		customerJson.put("region", savedCustomer.getRegion());
		JSONObject finalcustomerJson=new JSONObject();
		finalcustomerJson.put("customer", customerJson);
		
		//to generate token & expiresin for username
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(savedCustomer.getEmail());

		final String token = jwtTokenUtil.generateToken(userDetails);
		
		finalcustomerJson.put("accessToken", token);
		finalcustomerJson.put("expiresIn",jwtTokenUtil.getExpirationDateFromToken(token));		
		return finalcustomerJson;
		
		
		
		
		
	}

	@Override
	public JSONObject getcustomerdetails(String authToken) {
		String email = jwtTokenUtil.getUsernameFromToken(authToken.substring(7));
		Customer savedCustomer=customerRepository.findByEmail(email);
		JSONObject customerJson=new JSONObject();
		customerJson.put("customer_id", savedCustomer.getCustomer_id());
		customerJson.put("name", savedCustomer.getName());
		customerJson.put("email", savedCustomer.getEmail());
		customerJson.put("address_1",savedCustomer.getAddress_1());
		customerJson.put("address_2", savedCustomer.getAddress_2());
		customerJson.put("city", savedCustomer.getCity());
		customerJson.put("postal_code", savedCustomer.getPostal_code());
		customerJson.put("shipping_region_id", savedCustomer.getShipping_region_id());
		customerJson.put("credit_card", savedCustomer.getCredit_card());
		customerJson.put("day_phone", savedCustomer.getDay_phone());
		customerJson.put("eve_phone", savedCustomer.getEve_phone());
		customerJson.put("mob_phone", savedCustomer.getMob_phone());
		customerJson.put("region", savedCustomer.getRegion());
		return customerJson;
		
	}

	@Override
	public JSONObject updatecustomerdetails(String authToken, String email, String name, String day_phone,
			String mob_phone) {
		//gets email from authtoken
		String username = jwtTokenUtil.getUsernameFromToken(authToken.substring(7));
		Customer updateCustomer=customerRepository.findByEmail(username);
		updateCustomer.setEmail(email);
		updateCustomer.setName(name);
		updateCustomer.setDay_phone(day_phone);
		updateCustomer.setmob_phone(mob_phone);
		Customer savedCustomer=customerRepository.save(updateCustomer);
		
		JSONObject customerJson=new JSONObject();
		customerJson.put("customer_id", savedCustomer.getCustomer_id());
		customerJson.put("name", savedCustomer.getName());
		customerJson.put("email", savedCustomer.getEmail());
		customerJson.put("address_1",savedCustomer.getAddress_1());
		customerJson.put("address_2", savedCustomer.getAddress_2());
		customerJson.put("city", savedCustomer.getCity());
		customerJson.put("postal_code", savedCustomer.getPostal_code());
		customerJson.put("shipping_region_id", savedCustomer.getShipping_region_id());
		customerJson.put("credit_card", savedCustomer.getCredit_card());
		customerJson.put("day_phone", savedCustomer.getDay_phone());
		customerJson.put("eve_phone", savedCustomer.getEve_phone());
		customerJson.put("mob_phone", savedCustomer.getMob_phone());
		customerJson.put("region", savedCustomer.getRegion());
		
		return customerJson;
	}

	@Override
	public JSONObject updatecustomerAddress(String authToken, String address_1, String address_2, String city,
			String region, String postal_code, int shipping_region_id) {
		
		//gets email from authtoken
		String username = jwtTokenUtil.getUsernameFromToken(authToken.substring(7));
		Customer updateCustomer=customerRepository.findByEmail(username);
		updateCustomer.setAddress_1(address_1);
		updateCustomer.setAddress_2(address_2);
		updateCustomer.setCity(city);
		updateCustomer.setRegion(region);
		updateCustomer.setPostal_code(postal_code);
		updateCustomer.setShipping_region_id(shipping_region_id);
		Customer savedCustomer=customerRepository.save(updateCustomer);
		
		JSONObject customerJson=new JSONObject();
		customerJson.put("customer_id", savedCustomer.getCustomer_id());
		customerJson.put("name", savedCustomer.getName());
		customerJson.put("email", savedCustomer.getEmail());
		customerJson.put("address_1",savedCustomer.getAddress_1());
		customerJson.put("address_2", savedCustomer.getAddress_2());
		customerJson.put("city", savedCustomer.getCity());
		customerJson.put("postal_code", savedCustomer.getPostal_code());
		customerJson.put("shipping_region_id", savedCustomer.getShipping_region_id());
		customerJson.put("credit_card", savedCustomer.getCredit_card());
		customerJson.put("day_phone", savedCustomer.getDay_phone());
		customerJson.put("eve_phone", savedCustomer.getEve_phone());
		customerJson.put("mob_phone", savedCustomer.getMob_phone());
		customerJson.put("region", savedCustomer.getRegion());
		
		return customerJson;
		
	}

	@Override
	public JSONObject updatecustomerCreditCard(String authToken, String credit_card) {
		
		//gets email from authtoken
		String username = jwtTokenUtil.getUsernameFromToken(authToken.substring(7));
		Customer updateCustomer=customerRepository.findByEmail(username);
		updateCustomer.setCredit_card(credit_card);
		Customer savedCustomer=customerRepository.save(updateCustomer);
		
		JSONObject customerJson=new JSONObject();
		customerJson.put("customer_id", savedCustomer.getCustomer_id());
		customerJson.put("name", savedCustomer.getName());
		customerJson.put("email", savedCustomer.getEmail());
		customerJson.put("address_1",savedCustomer.getAddress_1());
		customerJson.put("address_2", savedCustomer.getAddress_2());
		customerJson.put("city", savedCustomer.getCity());
		customerJson.put("postal_code", savedCustomer.getPostal_code());
		customerJson.put("shipping_region_id", savedCustomer.getShipping_region_id());
		customerJson.put("credit_card", savedCustomer.getCredit_card());
		customerJson.put("day_phone", savedCustomer.getDay_phone());
		customerJson.put("eve_phone", savedCustomer.getEve_phone());
		customerJson.put("mob_phone", savedCustomer.getMob_phone());
		customerJson.put("region", savedCustomer.getRegion());
		
		return customerJson;
		
	}
	
	

}
