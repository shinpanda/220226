package com.ds.developtask.product;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.ds.developtask.config.JwtTokenProvider;
import com.ds.developtask.security.CustomUserDetailsService;
import com.ds.developtask.user.UserService;

@WebMvcTest
class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private JwtTokenProvider jwtTokenProvider;
	
	@MockBean
	private CustomUserDetailsService customUserDetailsService;
	
	private ProductService productService;
		
	@Test
	void 상품_조회_성공() throws Exception {
		// 준비
		
		// 실행
		mockMvc.perform(get("/product/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))				
		// 검증
			.andExpect(status().isOk());
	}

}
