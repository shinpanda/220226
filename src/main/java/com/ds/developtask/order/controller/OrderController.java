package com.ds.developtask.order.controller;

import com.ds.developtask.order.service.OrderService;
import com.ds.developtask.order.dto.OrderResponseDto;
import com.ds.developtask.order.dto.OrderSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderController {

	private final OrderService orderService;

	@PostMapping("/order")
	public OrderResponseDto save(@RequestBody OrderSaveRequestDto orderSaveRequestDto) {
		return orderService.save(orderSaveRequestDto);
	}

	@GetMapping("/order/user/{id}")
	public List<OrderResponseDto> getUserOrderedList(@PathVariable Long userId){
		return null;
	}
}