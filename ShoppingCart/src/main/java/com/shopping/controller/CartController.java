package com.shopping.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.exceptionhandling.ResourceNotFoundException;
import com.shopping.model.OrderDto;
import com.shopping.model.ShoppingCartDto;
import com.shopping.service.CartService;

@RestController
public class CartController {
	@Autowired
	CartService cartService;
	
	@GetMapping("/shoppingcart/generateUniqueId")
	public ResponseEntity<JSONObject> generateCartId() throws Exception{
		
		JSONObject cartJSON=cartService.createCartId();
		
        
        return ResponseEntity.ok().body(cartJSON);
    }
	
	@PostMapping("/shoppingcart/add")
	public ResponseEntity<JSONObject> addProductToCart(@RequestBody ShoppingCartDto cartDto) throws Exception{
		
		String cartId=cartDto.getCart_id();
		int productId=cartDto.getProduct_id();
		int quantity=cartDto.getQuantity();
		String attributes=cartDto.getAttributes();
		JSONObject cartJson=cartService.addProductToCart(cartId,productId,quantity,attributes);
        
        return ResponseEntity.ok().body(cartJson);
    }
	
	@GetMapping("/shoppingcart/{cart_id}")
	public ResponseEntity<List<JSONObject>> getProductsFromCart(@PathVariable("cart_id")String cartId) throws Exception {
		
			List<JSONObject> prodJsonList=cartService.getProductsFromCart(cartId);
			
	        
	        return ResponseEntity.ok().body(prodJsonList);
    }
	@PutMapping("/shoppingcart/update/{item_id}")
	public ResponseEntity<JSONObject> updateQuantityInCart(@PathVariable("item_id")int itemId,@RequestBody ShoppingCartDto cartDto) throws Exception {
		int quantity=cartDto.getQuantity();
		JSONObject cartJson=null;
		
		cartJson = cartService.updateQuantity(itemId,quantity);
		
		
        
        return ResponseEntity.ok().body(cartJson);
    }
	@DeleteMapping("/shoppingcart/empty/{cart_id}")
	public ResponseEntity<List<Object>> deleteCartByCartId(@PathVariable("cart_id")String cartId) throws Exception {
		
		List<Object> returnList=new ArrayList<>();
		
			returnList=cartService.deleteCartByCartId(cartId);
		
		
		
        
        return ResponseEntity.ok().body(returnList);
    }
	@DeleteMapping("/shoppingcart/removeProduct/{item_id}")
	public ResponseEntity<JSONObject> removeItemByItemId(@PathVariable("item_id")int itemId) throws Exception {
	
			
		JSONObject messageJSON=new JSONObject();
		messageJSON=cartService.deleteCartByItemId(itemId);
		return ResponseEntity.ok().body(messageJSON);
	
		
		
        
        
    }

}
