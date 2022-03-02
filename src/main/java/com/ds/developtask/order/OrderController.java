package com.ds.developtask.order;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ds.developtask.order.domain.Order;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class OrderController {

	@PostMapping("order/{id}")
	public Order saveByProductId(@PathVariable Long productId) {
		return null;
	}
}
