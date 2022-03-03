package com.ds.developtask.order;

import com.ds.developtask.config.JwtTokenProvider;
import com.ds.developtask.order.dto.OrderSaveRequestDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ds.developtask.order.domain.Order;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class OrderController {

	private final OrderService orderService;

	@PostMapping("order")
	public Order save(@RequestBody OrderSaveRequestDto orderSaveRequestDto) {
		return orderService.save(orderSaveRequestDto);
	}
}
