package com.ds.developtask.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.ds.developtask.product.repository.ProductRepository;
import com.ds.developtask.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ds.developtask.product.domain.Product;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
	
	@Mock
    ProductRepository productRepository;
	
	@InjectMocks
	ProductService productService;
	
	@Test
	void 상품_조회_성공() throws Exception {
		Long id = 1L;
		String name = "상품1";
		int amount = 10000;
		// 준비
		when(productRepository.findById(any())).thenReturn(Optional.of(Product.builder()
				.id(id)
				.name(name)
				.amount(amount)
				.build()));
		
		// 실행
		Product product = productService.findById(1L);
		
		// 검증
		assertThat(product.getId()).isEqualTo(id);
		assertThat(product.getName()).isEqualTo(name);
		assertThat(product.getAmount()).isEqualTo(amount);
	}

}
