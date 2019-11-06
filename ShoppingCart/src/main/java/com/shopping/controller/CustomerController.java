package com.shopping.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.model.CustomerDto;
import com.shopping.service.CustomerService;

@RestController
public class CustomerController {
	@Autowired
	CustomerService customerService;
	
	@PostMapping("/customers")
	public ResponseEntity<JSONObject> createCustomer(@RequestBody CustomerDto customerDto) {
		
		
		JSONObject customerJson=customerService.createCustomer(customerDto.getName(), customerDto.getEmail(), customerDto.getPassword());
		
        
        return ResponseEntity.ok().body(customerJson);
    }
	@GetMapping("/customers")
	public ResponseEntity<JSONObject> getCustomerDetails(@RequestHeader(value="Authorization") String authToken) {
		
		
		JSONObject customerJson=customerService.getcustomerdetails(authToken);
		
        
        return ResponseEntity.ok().body(customerJson);
    }
	
	@PutMapping("/customer")
	public ResponseEntity<JSONObject> updateCustomerDetails(@RequestHeader(value="Authorization") String authToken,@RequestBody CustomerDto customerDto) {
		
		String email=customerDto.getEmail();
		String name=customerDto.getName();
		String day_phone=customerDto.getDay_phone();
		String mob_phone=customerDto.getMob_phone();
		
		JSONObject customerJson=customerService.updatecustomerdetails(authToken,email,name,day_phone,mob_phone);
		
        
        return ResponseEntity.ok().body(customerJson);
    }
	@PutMapping("/customer/address")
	public ResponseEntity<JSONObject> updateCustomerAddress(@RequestHeader(value="Authorization") String authToken,@RequestBody CustomerDto customerDto) {
		
		String address_1=customerDto.getAddress_1();
		String address_2=customerDto.getAddress_2();
		String city=customerDto.getCity();
		String region=customerDto.getRegion();
		String postal_code=customerDto.getPostal_code();
		int shipping_region_id=customerDto.getShipping_region_id();
		
		JSONObject customerJson=customerService.updatecustomerAddress(authToken,address_1,address_2,city,region,postal_code,shipping_region_id);
		
        
        return ResponseEntity.ok().body(customerJson);
    }
	@PutMapping("/customer/creditCard")
	public ResponseEntity<JSONObject> updateCustomerCreditCard(@RequestHeader(value="Authorization") String authToken,@RequestBody CustomerDto customerDto) {
		
		String credit_card=customerDto.getCredit_card();
		
		JSONObject customerJson=customerService.updatecustomerCreditCard(authToken,credit_card);
		
        
        return ResponseEntity.ok().body(customerJson);
    }
}
