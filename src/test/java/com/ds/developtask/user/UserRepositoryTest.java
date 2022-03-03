package com.ds.developtask.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ds.developtask.user.UserDto;
import com.ds.developtask.user.domain.User;
import com.ds.developtask.user.repository.RoleRepository;
import com.ds.developtask.user.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Test
	void 회원_가입_성공() {
		// 준비
		String EMAIL = "dsound722@gmail.com";
		String PASSWORD = "test2";
		String ROLE = "ROLE_USER";
		
		// 실행
		User user = userRepository.save(User.builder().email(EMAIL).password(PASSWORD).roles(Arrays.asList(roleRepository.findByName(ROLE))).build());
		
		// 검증
		assertThat(user.getEmail()).isEqualTo(EMAIL);
	}
	
	@Test
	void 로그인_성공() {
		// 준비
		String EMAIL = "dsound72@gmail.com";
		String PASSWORD = "test";


		// 실행
		User user = userRepository.findByEmail(EMAIL).orElseThrow(IllegalArgumentException::new);

		// 검증
		assertThat(user.getEmail()).isEqualTo(EMAIL);
		assertThat(user.getPassword()).isEqualTo(new BCryptPasswordEncoder().encode(PASSWORD));
	}

}
