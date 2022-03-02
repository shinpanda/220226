package com.ds.developtask.product;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.ds.developtask.config.JwtTokenProvider;
import com.ds.developtask.product.domain.Product;
import com.ds.developtask.security.CustomUserDetailsService;
import com.ds.developtask.user.UserService;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private JwtTokenProvider jwtTokenProvider;
	
	@MockBean
	private CustomUserDetailsService customUserDetailsService;
	
	@MockBean
	private ProductService productService;
		
	@Test
	void 상품_조회_성공() throws Exception {
		Long id = 1L;
		String name = "상품1";
		int amount = 10000;
		// 준비
		when(productService.findById(anyLong())).thenReturn(Product.builder().id(id).name(name).amount(amount).build());
		
		// 실행
		mockMvc.perform(get("/product/"+id)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))				
		// 검증
			.andExpect(status().isOk())
			.andExpect(jsonPath("id").value(id));
	}

}
