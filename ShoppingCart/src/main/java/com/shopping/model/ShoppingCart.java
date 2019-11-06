package com.shopping.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="shopping_cart")
public class ShoppingCart {
	
	//item_id, cart_id, product_id, attributes, quantity, buy_now, added_on
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int item_id;
	@Column(name="cart_id")
	private String cartId;
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	private String attributes;
	private int quantity;
	private int buy_now;
	//date
	private String added_on;
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public String getCartId() {
		return cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getBuy_now() {
		return buy_now;
	}
	public void setBuy_now(int buy_now) {
		this.buy_now = buy_now;
	}
	public String getAdded_on() {
		return added_on;
	}
	public void setAdded_on(String added_on) {
		this.added_on = added_on;
	}
	@Override
	public String toString() {
		return "ShoppingCart [item_id=" + item_id + ", cart_id=" + cartId + ", product=" + product + ", attributes="
				+ attributes + ", quantity=" + quantity + ", buy_now=" + buy_now + ", added_on=" + added_on + "]";
	}
	
	
	

}
