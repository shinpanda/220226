package com.ds.developtask.order;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ds.developtask.order.domain.Order;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderService {
	
	private final OrderRepository orderRepository;
	
	public Order saveByProductId(@PathVariable Long productId) {
		return null;
	}
}
