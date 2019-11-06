package com.shopping.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.shopping.model.Customer;
import com.shopping.model.Order;

public interface OrderRepository extends CrudRepository<Order, Integer> {
	Order findByOrderId(int orderId);

	List<Order> findByCustomer(Customer savedCustomer);
	

}
