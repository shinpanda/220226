package com.ds.developtask.order;

import com.ds.developtask.order.dto.OrderSaveRequestDto;
import com.ds.developtask.product.ProductRepository;
import com.ds.developtask.product.domain.Product;
import com.ds.developtask.user.domain.User;
import com.ds.developtask.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ds.developtask.order.domain.Order;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderService {

	private final OrderRepository orderRepository;
	private final UserRepository userRepository;
	private final ProductRepository productRepository;

	public Order save(OrderSaveRequestDto orderSaveRequestDto) {
		User orderedUser = userRepository.findByEmail(orderSaveRequestDto.getOrderedUserEmail()).orElseThrow(IllegalArgumentException::new);
		Product orderedProduct = productRepository.findById(orderSaveRequestDto.getProductId()).orElseThrow(IllegalArgumentException::new);

		return orderRepository.save(Order.builder().user(orderedUser).product(orderedProduct).build());
	}
}
