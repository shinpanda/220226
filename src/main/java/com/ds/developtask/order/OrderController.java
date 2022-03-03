package com.ds.developtask.order;

import com.ds.developtask.config.JwtTokenProvider;
import com.ds.developtask.order.dto.OrderSaveRequestDto;
import org.springframework.web.bind.annotation.*;

import com.ds.developtask.order.domain.Order;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class OrderController {

	private final OrderService orderService;

	@PostMapping("order/{id}")
	public void save(@RequestBody OrderSaveRequestDto orderSaveRequestDto, @RequestHeader("X-AUTH-TOKEN") String token) {
		orderService.save(orderSaveRequestDto);
	}
}
