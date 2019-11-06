package com.shopping.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customer_id;
	//customer_id, name, email, password, credit_card, address_1, address_2, city, region, postal_code, country, shipping_region_id, day_phone, eve_phone, mob_phone
	private String name;
	@Column(unique = true)
	private String email;
	private String password;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String credit_card;
	private String address_1;
	private String address_2;
	private String city;
	private String region;
	private String postal_code;
	private int shipping_region_id;
	private String day_phone;
	private String eve_phone;
	private String mob_phone;
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCredit_card() {
		return credit_card;
	}
	public void setCredit_card(String credit_card) {
		this.credit_card = credit_card;
	}
	public String getAddress_1() {
		return address_1;
	}
	public void setAddress_1(String address_1) {
		this.address_1 = address_1;
	}
	public String getAddress_2() {
		return address_2;
	}
	public void setAddress_2(String address_2) {
		this.address_2 = address_2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getPostal_code() {
		return postal_code;
	}
	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}
	public int getShipping_region_id() {
		return shipping_region_id;
	}
	public void setShipping_region_id(int shipping_region_id) {
		this.shipping_region_id = shipping_region_id;
	}
	public String getDay_phone() {
		return day_phone;
	}
	public void setDay_phone(String day_phone) {
		this.day_phone = day_phone;
	}
	public String getEve_phone() {
		return eve_phone;
	}
	public void setEve_phone(String eve_phone) {
		this.eve_phone = eve_phone;
	}
	public String getMob_phone() {
		return mob_phone;
	}
	public void setmob_phone(String mpb_phone) {
		this.mob_phone = mpb_phone;
	}
	@Override
	public String toString() {
		return "Customer [customer_id=" + customer_id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", credit_card=" + credit_card + ", address_1=" + address_1 + ", address_2=" + address_2 + ", city="
				+ city + ", region=" + region + ", postal_code=" + postal_code + ", shipping_region_id="
				+ shipping_region_id + ", day_phone=" + day_phone + ", eve_phone=" + eve_phone + ", mpb_phone="
				+ mob_phone + "]";
	}
	
	
}
