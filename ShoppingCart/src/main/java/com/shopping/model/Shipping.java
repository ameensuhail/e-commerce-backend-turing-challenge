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
@Table(name="shipping")
public class Shipping {
	//shipping_id, shipping_type, shipping_cost, shipping_region_id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="shipping_id")
	private int shippingId;
	
	@ManyToOne
	@JoinColumn(name="shipping_region_id")
	private ShippingRegion shippingRegion;
	
	private String shipping_type;
	private float shipping_cost;
	public int getShippingId() {
		return shippingId;
	}
	public void setShippingId(int shippingId) {
		this.shippingId = shippingId;
	}
	
	public ShippingRegion getShippingRegion() {
		return shippingRegion;
	}
	public void setShippingRegion(ShippingRegion shippingRegion) {
		this.shippingRegion = shippingRegion;
	}
	public String getShipping_type() {
		return shipping_type;
	}
	public void setShipping_type(String shipping_type) {
		this.shipping_type = shipping_type;
	}
	public float getShipping_cost() {
		return shipping_cost;
	}
	public void setShipping_cost(float shipping_cost) {
		this.shipping_cost = shipping_cost;
	}
	

}
