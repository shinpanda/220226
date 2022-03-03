package com.ds.developtask.order;

import com.ds.developtask.order.domain.Order;
import com.ds.developtask.product.ProductRepository;
import com.ds.developtask.product.domain.Product;
import com.ds.developtask.user.domain.User;
import com.ds.developtask.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void 상품_주문_성공() {
        // 준비
        Long productId = 1L;
        String orderedUserEmail = "dsound72@gmail.com";

        User orderedUser = userRepository.findByEmail(orderedUserEmail).orElseThrow(RuntimeException::new);
        Product orderedProduct = productRepository.findById(productId).orElseThrow(RuntimeException::new);

        // 실행
        Order order = orderRepository.save(Order.builder().user(orderedUser).product(orderedProduct).build());

        // 검증
        assertThat(order.getProduct().getId()).isEqualTo(productId);
        assertThat(order.getUser().getEmail()).isEqualTo(orderedUserEmail);
    }
}