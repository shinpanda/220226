package com.ds.developtask.order;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrderRepositoryTest {

    @Test
    void 상품_주문_성공() {
        // 준비
        Long productId = 1L;
        String orderedUserEmail = "dsound72@gmail.com";
        
        // 실행
        
        // 검증
        
    }
}