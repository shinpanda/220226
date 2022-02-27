package com.ds.developtask;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	void 회원_가입_성공() {
		// �غ�
		String ID = "ds";
		String EMAIL = "dsound72@gmail.com";
		String PASSWORD = "test";
		
		UserSaveRequestDto requestUser = new UserSaveRequestDto(ID, EMAIL, PASSWORD);
		
		// ����
		User user = userRepository.save(requestUser.toEntity());
		
		// ����
		assertThat(user.getId()).isEqualTo(ID);
		assertThat(user.getEmail()).isEqualTo(EMAIL);
	}

}
