package com.ds.developtask;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	
	private UserService userService;
	
	
	@Test
	void 회원_가입_성공() throws Exception{
		// 준비
		String ID = "ds";
		String EMAIL = "dsound72@gmail.com";
		String PASSWORD = "test";
		
		Map<String, Object> requestData = new HashMap<>();
		requestData.put("id", ID);
		requestData.put("email", EMAIL);
		requestData.put("password", PASSWORD);
		
		// 실행
		ResultActions perform = mockMvc.perform(post("/signup",requestData));
		
		// 검증
		perform.andExpect(status().isOk())
			.andExpect(jsonPath("id").value(ID))
			.andExpect(jsonPath("email").value(EMAIL))
			.andExpect(jsonPath("password").value(PASSWORD));
	}
}
