package com.shopping.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.exceptionhandling.ResourceNotFoundException;
import com.shopping.model.Product;
import com.shopping.model.ShoppingCart;
import com.shopping.repository.CartRepository;
import com.shopping.repository.ProductRepository;

@Service
@Transactional
public class CartServiceImpl implements CartService{
	@Autowired
	CartRepository cartRepository;
	@Autowired
	ProductRepository productRepository;

	@Override
	public JSONObject createCartId() {
		String generatedString = RandomStringUtils.randomAlphanumeric(6);
		JSONObject cartJSON=new JSONObject();
		cartJSON.put("cart_id", generatedString);
		return cartJSON;
	}

	@Override
	//add logic to check whether the requested attribute is present for product
	public JSONObject addProductToCart(String cartId, int productId, int quantity, String attributes){
		ShoppingCart cart=new ShoppingCart();
		cart.setCartId(cartId);
		cart.setAttributes(attributes);
		cart.setBuy_now(1);
		Product product=productRepository.getProductById(productId);
		if(product==null) {
			//throw Exception
		}
		
		cart.setProduct(product);
		cart.setQuantity(quantity);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		cart.setAdded_on(formatter.format(new Date()));
		ShoppingCart savedCart=cartRepository.save(cart);
		JSONObject cartJson=new JSONObject();
		cartJson.put("item_id",savedCart.getItem_id());
		cartJson.put("cart_id",savedCart.getCartId());
		cartJson.put("attributes",savedCart.getAttributes());
		cartJson.put("product_id",savedCart.getProduct().getProduct_id());
		cartJson.put("quantity",savedCart.getQuantity());
		return cartJson;
	}

	@Override
	public List<JSONObject> getProductsFromCart(String cartId) throws Exception {
		List<ShoppingCart> shoppingCartProducts=cartRepository.findByCartId(cartId);
		if(shoppingCartProducts.isEmpty()) {throw new ResourceNotFoundException("Cart Id not Found "+cartId);}
		//List<Product> products=cartRepository.findProductByCartId(cartId);
		List<JSONObject> prodjsons=new ArrayList<>();
		for(ShoppingCart item:shoppingCartProducts) {
			JSONObject prodJson=new JSONObject();
			prodJson.put("item_id", item.getItem_id());
			prodJson.put("cart_id", item.getCartId());
			prodJson.put("name", item.getProduct().getName());
			prodJson.put("attributes", item.getAttributes());
			prodJson.put("product_id", item.getProduct().getProduct_id());
			prodJson.put("image", item.getProduct().getImage());
			prodJson.put("price", item.getProduct().getPrice());
			prodJson.put("discounted_price", item.getProduct().getDiscounted_price());
			prodJson.put("quatity", item.getQuantity());
			float subtotal=item.getProduct().getPrice()*item.getQuantity();
			prodJson.put("subtotal", subtotal);
			prodjsons.add(prodJson);
			
		}
		return prodjsons;
		
	}

	@Override
	public JSONObject updateQuantity(int itemId, int quantity) throws Exception {
		Optional<ShoppingCart> cartOptional=cartRepository.findById(itemId);
		JSONObject cartJson=new JSONObject();
		if(cartOptional.isPresent()) {
			ShoppingCart cart=cartOptional.get();
			cart.setQuantity(quantity);
			ShoppingCart savedCart=cartRepository.save(cart);
			cartJson.put("item_id",savedCart.getItem_id());
			cartJson.put("cart_id",savedCart.getCartId());
			cartJson.put("attributes",savedCart.getAttributes());
			cartJson.put("product_id",savedCart.getProduct().getProduct_id());
			cartJson.put("quantity",savedCart.getQuantity());
			
			
			
		}
		else {
			throw new ResourceNotFoundException("Cart not found in Database:Item ID"+itemId);
		}
		return cartJson;
		
		
	}

	@Override
	public List<Object> deleteCartByCartId(String cartId) throws Exception {
		Long num=cartRepository.deleteByCartId(cartId);
		if(num>0)return new ArrayList<>();
		else {
			throw new ResourceNotFoundException("Cart Id not Found "+cartId);
		}
		
	}
	@Override
	public JSONObject deleteCartByItemId(int itemId) throws Exception{
		JSONObject messageJson=new JSONObject();
		
		if(cartRepository.existsById(itemId)) {
			cartRepository.deleteById(itemId);
			messageJson.put("message", "Item deleted with Item ID "+itemId);
			return messageJson;
		}
		else {
			throw new ResourceNotFoundException("Cart not found in Database:Item ID"+itemId);
		}
		
		
	}
	

}
