package com.ds.developtask.product;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ds.developtask.product.domain.Product;

@DataJpaTest
class ProductRepositoryTest {

	@Autowired
	ProductRepository productRepository;
	
	@Test
	void 상품_조회_성공() {
		//준비
		
		//실행
		Product product = productRepository.findById(1L).orElseThrow(RuntimeException::new);
		//검증
		assertThat(product.getId()).isEqualTo(1L);
	}

}
