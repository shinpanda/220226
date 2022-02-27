package com.ds.developtask;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserServiceTest {
	
	UserService userService = new UserService();
	UserRepository userRepository;

	@Test
	void 회원_가입_성공() {
		// 준비
		String ID = "ds";
		String EMAIL = "dsound72@gmail.com";
		String PASSWORD = "test";
		
		UserSaveRequestDto requestUser = new UserSaveRequestDto(ID, EMAIL, PASSWORD);
		
		// 실행
		User user = userService.save(requestUser);
		
		// 검증
		assertThat(user.getId()).isEqualTo(ID);
	}

}
