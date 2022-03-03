package com.ds.developtask.order;

import com.ds.developtask.order.domain.Order;
import com.ds.developtask.order.dto.OrderResponseDto;
import com.ds.developtask.order.dto.OrderSaveRequestDto;
import com.ds.developtask.order.dto.UserOrdersDto;
import com.ds.developtask.order.repository.OrderRepository;
import com.ds.developtask.order.service.OrderService;
import com.ds.developtask.product.domain.Product;
import com.ds.developtask.product.repository.ProductRepository;
import com.ds.developtask.user.domain.Role;
import com.ds.developtask.user.domain.User;
import com.ds.developtask.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

	@InjectMocks
	OrderService orderService;

	@Mock
	OrderRepository orderRepository;

	@Mock
	UserRepository userRepository;

	@Mock
	ProductRepository productRepository;

	@Spy
	private ModelMapper modelMapper;

	@Test
	void 상품_주문_성공() {
		// 준비
		Product product = Product.builder().id(1L).name("테스트상품").amount(10000).build();
		User user = User.builder().id(1L).email("test@test.com").roles(Arrays.asList(new Role("USER_ROLE"))).build();

		when(userRepository.findByEmail(any())).thenReturn(Optional.ofNullable(user));
		when(productRepository.findById(any())).thenReturn(Optional.ofNullable(product));
		when(orderRepository.save(any())).thenReturn(Order.builder().id(1L).user(user).product(product).build());

		// 실행
		OrderResponseDto orderResponseDto = orderService.save(OrderSaveRequestDto.builder()
				.productId(product.getId())
				.userEmail(user.getEmail())
				.build());

		// 검증
		assertThat(orderResponseDto.getProductId()).isEqualTo(product.getId());
		assertThat(orderResponseDto.getUserEmail()).isEqualTo(user.getEmail());
	}

	@Test
	void 회원_주문_내역_조회_성공(){
		// 준비
		User user = User.builder().id(1L).email("test@test.com").roles(Arrays.asList(new Role("USER_ROLE"))).build();
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.builder().id(1L).user(user).product(new Product(1L,"테스트상품", 10000)).build());
		orderList.add(Order.builder().id(2L).user(user).product(new Product(2L,"테스트상품2", 10000)).build());
		when(userRepository.findById(any())).thenReturn(Optional.of(user));
		// 실행
		List<UserOrdersDto> getOrderList = orderService.getUserOrderedList(1L);

		// 검증
		assertThat(getOrderList.size()).isNotZero();;
	}
}
