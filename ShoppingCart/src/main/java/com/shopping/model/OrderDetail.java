package com.shopping.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="order_detail")
public class OrderDetail {
	//item_id, order_id, product_id, attributes, product_name, quantity, unit_cost;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int item_id;
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	@ManyToOne
	@JoinColumn(name="order_id")
	private Order order;
	private String attributes;
	private int quantity;
	private String product_name;
	private float unit_cost;
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
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
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public float getUnit_cost() {
		return unit_cost;
	}
	public void setUnit_cost(float unit_cost) {
		this.unit_cost = unit_cost;
	}
	@Override
	public String toString() {
		return "Order_Detail [item_id=" + item_id + ", product=" + product + ", order=" + order + ", attributes="
				+ attributes + ", quantity=" + quantity + ", product_name=" + product_name + ", unit_cost=" + unit_cost
				+ "]";
	}
	

}
