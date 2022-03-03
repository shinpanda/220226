package com.ds.developtask.order.service;

import com.ds.developtask.order.domain.Order;
import com.ds.developtask.order.dto.OrderResponseDto;
import com.ds.developtask.order.dto.OrderSaveRequestDto;
import com.ds.developtask.order.dto.UserOrdersDto;
import com.ds.developtask.order.repository.OrderRepository;
import com.ds.developtask.product.domain.Product;
import com.ds.developtask.product.repository.ProductRepository;
import com.ds.developtask.user.domain.User;
import com.ds.developtask.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {

	private final OrderRepository orderRepository;
	private final UserRepository userRepository;
	private final ProductRepository productRepository;
	private final ModelMapper modelMapper;

	@Transactional
	public OrderResponseDto save(OrderSaveRequestDto orderSaveRequestDto) {
		User orderedUser = userRepository.findByEmail(orderSaveRequestDto.getUserEmail()).orElseThrow(IllegalArgumentException::new);
		Product orderedProduct = productRepository.findById(orderSaveRequestDto.getProductId()).orElseThrow(IllegalArgumentException::new);
		Order order = orderRepository.save(Order.builder().user(orderedUser).product(orderedProduct).build());

		modelMapper.createTypeMap(Order.class, OrderResponseDto.class).addMappings(mapping -> {
			mapping.map(Order::getId, OrderResponseDto::setId);
			mapping.map(src -> src.getUser().getEmail(), OrderResponseDto::setUserEmail);
			mapping.map(src -> src.getProduct().getId(), OrderResponseDto::setProductId);
		});

		return modelMapper.map(order, OrderResponseDto.class);
	}

	public List<UserOrdersDto> getUserOrderedList(Long userId){
		List<Order> orders = userRepository.findById(userId).get().getOrders();

		modelMapper.createTypeMap(Order.class, UserOrdersDto.class).addMappings(mapping -> {
			mapping.map(Order::getId, UserOrdersDto::setOrderId);
			mapping.map(src -> src.getUser().getId(), UserOrdersDto::setUserId);
			mapping.map(src -> src.getProduct(), UserOrdersDto::setProduct);
		});

		return orders.stream().map(order -> modelMapper.map(order, UserOrdersDto.class)).collect(Collectors.toList());
	}
}
