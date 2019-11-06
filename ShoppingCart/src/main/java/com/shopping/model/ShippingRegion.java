package com.shopping.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="shipping_region")
public class ShippingRegion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="shipping_region_id")
	private int shippingRegionId;
	@Column(name="shipping_region")
	private String shippingRegion;
	public int getShippingRegionId() {
		return shippingRegionId;
	}
	public void setShippingRegionId(int shippingRegionId) {
		this.shippingRegionId = shippingRegionId;
	}
	public String getShippingRegion() {
		return shippingRegion;
	}
	public void setShippingRegion(String shippingRegion) {
		this.shippingRegion = shippingRegion;
	}
	

}
