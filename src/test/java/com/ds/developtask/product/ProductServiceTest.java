package com.ds.developtask.product;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.ds.developtask.product.domain.Product;

class ProductServiceTest {
	
	ProductRepository productRepository;
	ProductService productService = new ProductService();
	
	@Test
	void 상품_조회_성공() throws Exception {
		// 준비		
		// 실행
		Product product = productService.getById(1L);
		
		// 검증
		assertThat(product.getId()).isEqualTo(1);
		
	}

}
