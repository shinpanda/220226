package com.ds.developtask.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ds.developtask.order.domain.Order;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

	OrderRepository orderRepository;
	OrderService orderService;
	
	@Test
	void 상품_주문_성공() {
		// 준비
		
		// 실행
		Order order = orderService.saveByProductId(1L);
		
		// 검증
		assertThat(order.getProduct().getId()).isEqualTo(1L);
	}

}
