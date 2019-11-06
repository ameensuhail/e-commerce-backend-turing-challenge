package com.shopping.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class Order {
	//order_id, total_amount, created_on, shipped_on, status, comments, customer_id, auth_code, reference, shipping_id, tax_id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")
	private int orderId;
	private float total_amount;
	//change date
	private String created_on;
	private String shipped_on;
	
	private int status;
	private String comments;
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	private String auth_code;
	private String reference;
	@ManyToOne
	@JoinColumn(name="shipping_id")
	private Shipping shipping;
	@ManyToOne
	@JoinColumn(name="tax_id")
	private Tax tax;
	/*@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name="order_detail",
	joinColumns = {@JoinColumn(name="order_id",referencedColumnName="order_id")},
	inverseJoinColumns = {@JoinColumn(name="item_id",referencedColumnName="item_id")})*/
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "order")
	private List<OrderDetail> orderItems=new ArrayList<>();

	public List<OrderDetail> getOrderItems() {
		return orderItems;
	}
	//important
	public void addItem(OrderDetail orderDetail) {
		orderItems.add(orderDetail);
		orderDetail.setOrder(this);
	}
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public float getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(float total_amount) {
		this.total_amount = total_amount;
	}
	public String getCreated_on() {
		return created_on;
	}
	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}
	public String getShipped_on() {
		return shipped_on;
	}
	public void setShipped_on(String shipped_on) {
		this.shipped_on = shipped_on;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getAuth_code() {
		return auth_code;
	}
	public void setAuth_code(String auth_code) {
		this.auth_code = auth_code;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public Shipping getShipping() {
		return shipping;
	}
	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}
	public Tax getTax() {
		return tax;
	}
	public void setTax(Tax tax) {
		this.tax = tax;
	}
	@Override
	public String toString() {
		return "Order [order_id=" + orderId + ", total_amount=" + total_amount + ", created_on=" + created_on
				+ ", shipped_on=" + shipped_on + ", status=" + status + ", comments=" + comments + ", customer="
				+ customer + ", auth_code=" + auth_code + ", reference=" + reference + ", shipping=" + shipping
				+ ", tax=" + tax + ", orderItems=" + orderItems + "]";
	}
	
	

}
