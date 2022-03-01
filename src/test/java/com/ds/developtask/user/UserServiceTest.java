package com.ds.developtask.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ds.developtask.config.JwtTokenProvider;
import com.ds.developtask.user.UserDto;
import com.ds.developtask.user.UserService;
import com.ds.developtask.user.domain.Role;
import com.ds.developtask.user.domain.User;
import com.ds.developtask.user.repository.RoleRepository;
import com.ds.developtask.user.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	
	@Mock
	UserRepository userRepository;

	@Mock
	RoleRepository roleRepository;
    
    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    JwtTokenProvider jwtTokenProvider;
    
    @InjectMocks
	UserService userService;
	
	UserDto requestUser;
	User user;
	
	@BeforeEach
	void setUp() {
		// 준비
		String EMAIL = "dsound72@gmail.com";
		String PASSWORD = "test";
		
		requestUser = new UserDto(EMAIL, PASSWORD);
	}
	
	@Test
	void 회원_가입_성공() {
		when(userRepository.save(any())).thenReturn(requestUser.toEntity());
		// 실행
		user = userService.save(requestUser);
		// 검증
		assertThat(user.getEmail()).isEqualTo(requestUser.getEmail());
	}

	@Test
	void 로그인_성공() {
		// 준비
		when(passwordEncoder.matches(any(), any())).thenReturn(true);
		when(userRepository.findByEmail(any())).thenReturn(User.builder()
				.email(requestUser.getEmail())
				.password(requestUser.getPassword())
				.roles(Arrays.asList(new Role("USER_ROLE"))).build());
		when(jwtTokenProvider.createToken(any(), any())).thenReturn("CreateToken");
		// 실행
		String token = userService.login(requestUser);
		// 검증
		assertThat(!token.isEmpty());
	}
}
