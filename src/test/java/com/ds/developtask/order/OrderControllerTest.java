package com.ds.developtask.order;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.ds.developtask.security.SecurityConfig;

import java.util.HashMap;
import java.util.Map;

@WebMvcTest(controllers = OrderController.class, excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes=SecurityConfig.class)
})
class OrderControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();
	
	OrderService orderService;

	@Test
    @WithMockUser(roles = "USER")
	void 상품_주문_성공() throws Exception {
		// 준비

		Long productId = 1L;
		String orderedUserEmail = "dsound72@gmail.com";

		Map<String, Object> requestData = new HashMap<>();
		requestData.put("productId", productId);
		requestData.put("orderedUserEmail", orderedUserEmail);


		// 실행
		mockMvc.perform(post("/order/1")
				.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(requestData))
				.accept(MediaType.APPLICATION_JSON))
		// 검증
				.andExpect(status().isOk());
	}
}
