package com.ds.developtask.product;

import com.ds.developtask.product.domain.Product;
import com.ds.developtask.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductRepositoryTest {

	@Autowired
    private ProductRepository productRepository;
	
	@Test
	void 상품_조회_성공() {
		//준비
		
		//실행
		Product product = productRepository.findById(1L).orElseThrow(RuntimeException::new);
		//검증
		assertThat(product.getId()).isEqualTo(1L);
	}

}
