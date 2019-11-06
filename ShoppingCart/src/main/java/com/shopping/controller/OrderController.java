package com.shopping.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.model.CustomerDto;
import com.shopping.model.OrderDto;
import com.shopping.service.OrderService;

@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;
	@PostMapping("/orders")
	public ResponseEntity<JSONObject> createOrder(@RequestHeader(value="Authorization") String authToken,@RequestBody OrderDto orderDto) {
		
		String cart_id=orderDto.getCart_id();
		int shipping_id=orderDto.getShipping_id();
		int tax_id=orderDto.getTax_id();
		JSONObject orderJson=orderService.placeOrder(authToken.substring(7),cart_id, shipping_id, tax_id);
		
        
        return ResponseEntity.ok().body(orderJson);
    }
	
	@GetMapping("/orders/{order_id}")
	public ResponseEntity<JSONObject> getOrderById(@PathVariable("order_id") int orderId) {
		
		JSONObject orderJson=orderService.getOrderById(orderId);
        
        return ResponseEntity.ok().body(orderJson);
    }
	@GetMapping("/orders/inCustomer")
	public ResponseEntity<List<JSONObject>> getOrderById(@RequestHeader(value="Authorization") String authToken) {
		
		List<JSONObject> orderJson=orderService.getOrderByCustomer(authToken.substring(7));
        
        return ResponseEntity.ok().body(orderJson);
    }
	
	@GetMapping("/orders/shortDetail/{order_id}")
	public ResponseEntity<JSONObject> getShortOrderById(@PathVariable("order_id") int orderId) {
		
		JSONObject orderJson=orderService.getShortOrderById(orderId);
        
        return ResponseEntity.ok().body(orderJson);
    }
}
