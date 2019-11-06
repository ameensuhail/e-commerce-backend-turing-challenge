package com.shopping.service;

import java.util.List;

import org.json.simple.JSONObject;

public interface CartService {
	JSONObject createCartId();

	JSONObject addProductToCart(String cartId, int productId, int quantity, String attributes);

	List<JSONObject> getProductsFromCart(String cartId) throws Exception;

	JSONObject updateQuantity(int itemId,int quantity) throws Exception;

	List<Object> deleteCartByCartId(String cartId) throws Exception;

	JSONObject deleteCartByItemId(int itemId) throws Exception;

}
