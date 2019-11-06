package com.shopping.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.config.JwtTokenUtil;
import com.shopping.model.Customer;
import com.shopping.model.Order;
import com.shopping.model.OrderDetail;
import com.shopping.model.Shipping;
import com.shopping.model.ShoppingCart;
import com.shopping.repository.CartRepository;
import com.shopping.repository.CustomerRepository;
import com.shopping.repository.OrderRepository;
import com.shopping.repository.ShippingRepository;
import com.shopping.repository.TaxRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private ShippingRepository shippingRepository;
	@Autowired
	private TaxRepository taxRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private OrderRepository orderRepository;
	@Override
	public JSONObject placeOrder(String authToken,String cart_id, int shipping_id, int tax_id) {
		
		List<ShoppingCart> shoppingCartProducts=cartRepository.findByCartId(cart_id);
		//List<OrderDetail> orderItems=new ArrayList<OrderDetail>();
		Order newOrder=new Order();
		float totalprice=0;
		for(ShoppingCart item:shoppingCartProducts) {
			if(item.getBuy_now()==1) {
				OrderDetail orderDetail=new OrderDetail();
				orderDetail.setAttributes(item.getAttributes());
				orderDetail.setProduct(item.getProduct());
				orderDetail.setProduct_name(item.getProduct().getName());
				orderDetail.setQuantity(item.getQuantity());
				orderDetail.setUnit_cost(item.getProduct().getPrice());
				//adding item to order
				newOrder.addItem(orderDetail);
				totalprice=totalprice+(item.getQuantity()*item.getProduct().getPrice());
				
			}
		}
		Shipping shipping=shippingRepository.findByShippingId(shipping_id);
		newOrder.setShipping(shipping);
		//adding shipping cost to total amount
		totalprice=totalprice+shipping.getShipping_cost();
		newOrder.setTax(taxRepository.findByTaxId(tax_id));
		newOrder.setTotal_amount(totalprice);
		//newOrder.setAuth_code(authToken);
		
		String email = jwtTokenUtil.getUsernameFromToken(authToken);
		Customer savedCustomer=customerRepository.findByEmail(email);
		newOrder.setCustomer(savedCustomer);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		newOrder.setCreated_on(formatter.format(new Date()));
		
		Order placedOrder=orderRepository.save(newOrder);
		
		JSONObject orderJson=new JSONObject();
		orderJson.put("order_id", placedOrder.getOrderId());
		return orderJson;
		
	}
	@Override
	public JSONObject getOrderById(int orderId) {
		Order savedOrder=orderRepository.findByOrderId(orderId);
		JSONObject orderJson=new JSONObject();
		orderJson.put("order_id", savedOrder.getOrderId());
		List<OrderDetail> orderItems=savedOrder.getOrderItems();
		//System.out.println(orderItems);
		List<JSONObject> orderJSONList=new ArrayList<JSONObject>();
		for(OrderDetail orderItem:orderItems) {
			JSONObject orderItemJson=new JSONObject();
			orderItemJson.put("product_id", orderItem.getProduct().getProduct_id());
			orderItemJson.put("attributes", orderItem.getAttributes());
			orderItemJson.put("product_name", orderItem.getProduct_name());
			orderItemJson.put("quantity", orderItem.getQuantity());
			orderItemJson.put("unit_cost", orderItem.getUnit_cost());
			float subtotal=orderItem.getQuantity()*orderItem.getUnit_cost();
			orderItemJson.put("subtotal", subtotal);
			orderJSONList.add(orderItemJson);
			
		}
		orderJson.put("order_items", orderJSONList);
		return orderJson;
		
		
		
		
	}
	@Override
	public List<JSONObject> getOrderByCustomer(String authToken) {
		String email = jwtTokenUtil.getUsernameFromToken(authToken);
		Customer savedCustomer=customerRepository.findByEmail(email);
		//int customerId=savedCustomer.getCustomer_id();
		List<Order> savedOrders=orderRepository.findByCustomer(savedCustomer);
		
		List<JSONObject> orderJSONList=new ArrayList<JSONObject>();
		
		for(Order order:savedOrders) {
			JSONObject orderJson=new JSONObject();
			orderJson.put("order_id", order.getOrderId());
			orderJson.put("total_amount", order.getTotal_amount());
			orderJson.put("created_on", order.getCreated_on());
			orderJson.put("shipped_on", order.getShipped_on());
			orderJson.put("name", order.getCustomer().getName());
			orderJSONList.add(orderJson);
			
		}
		return orderJSONList;
		
	}
	@Override
	public JSONObject getShortOrderById(int orderId) {
		Order savedOrder=orderRepository.findByOrderId(orderId);
		JSONObject orderJson=new JSONObject();
		orderJson.put("order_id", savedOrder.getOrderId());
		orderJson.put("total_amount", savedOrder.getTotal_amount());
		orderJson.put("created_on", savedOrder.getCreated_on());
		orderJson.put("shipped_on", savedOrder.getShipped_on());
		orderJson.put("status", savedOrder.getStatus());
		orderJson.put("name", savedOrder.getCustomer().getName());
		return orderJson;
	}


}
