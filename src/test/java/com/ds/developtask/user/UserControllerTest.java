package com.ds.developtask.user;

import com.ds.developtask.config.JwtTokenProvider;
import com.ds.developtask.security.CustomUserDetailsService;
import com.ds.developtask.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private JwtTokenProvider jwtTokenProvider;
	
	@MockBean
	private CustomUserDetailsService customUserDetailsService;
		
	@Test
	void 회원_가입_성공() throws Exception{
		
		// 준비
		String EMAIL = "dsound72@gmail.com";
		String PASSWORD = "test";
		
		Map<String, Object> requestData = new HashMap<>();
		requestData.put("email", EMAIL);
		requestData.put("password", PASSWORD);
		
		// 실행
		mockMvc.perform(post("/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(requestData))
				.accept(MediaType.APPLICATION_JSON))				
		// 검증
			.andExpect(status().isOk())
			.andExpect(jsonPath("email").value(EMAIL));
	}
	

	@Test
	void 로그인_성공() throws Exception {
		// 준비
		Map<String, Object> requestData = new HashMap<>();
		requestData.put("email", "dsound72@gmail.com");
		requestData.put("password", "test");

		// 실행
		ResultActions perform = mockMvc.perform(post("/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(requestData))
				.accept(MediaType.APPLICATION_JSON));				
		// 검증
		perform.andExpect(status().isOk());
	}
}
