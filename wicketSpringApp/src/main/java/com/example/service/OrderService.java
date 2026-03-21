package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.model.Order;

@Service
public class OrderService {
	private final List<Order> orders = new ArrayList<>();

	/**
	 * Logic to handle both Add and Update.
	 */
	public void save(Order modelObject) {
		if (modelObject.getId() == null) {
			modelObject.setId(orders.size() + 1L); // Simple ID generation);
		} else {
			orders.removeIf(o -> o.getId().equals(modelObject.getId())); // Remove existing order with same ID
		}
		orders.add(modelObject); // Add the new/updated order
	}

	public void delete(Long id) {
        orders.removeIf(o -> o.getId().equals(id));
    }
	
	/**
	 * Logic for Filtering and Pagination.
	 */
	public List<Order> findOrders(String searchFilter, long first, long count) {
		return orders.stream().filter(o -> matches(o, searchFilter)) // Apply search filter
				.skip(first) // Skip for pagination
				.limit(count) // Limit for pagination
				.collect(Collectors.toList());
	}

	public long countOrders(String searchFilter) {
		return orders.stream().filter(o -> matches(o, searchFilter)).count();
	}

	private boolean matches(Order order, String filter) {
		if (filter == null || filter.isEmpty())
			return true;
		return order.getCustomer().toLowerCase().contains(filter.toLowerCase());
	}
}
