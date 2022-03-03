package com.ds.developtask.user;

import com.ds.developtask.config.JwtTokenProvider;
import com.ds.developtask.user.domain.Role;
import com.ds.developtask.user.domain.User;
import com.ds.developtask.user.dto.UserDto;
import com.ds.developtask.user.dto.UserResponseDto;
import com.ds.developtask.user.repository.RoleRepository;
import com.ds.developtask.user.repository.UserRepository;
import com.ds.developtask.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
	@Spy
	ModelMapper modelMapper;
    @InjectMocks
	UserService userService;
	
	UserDto requestUser;
	
	@BeforeEach
	void setUp() {
		// 준비
		String EMAIL = "test22@test.com";
		String PASSWORD = "test";
		
		requestUser = new UserDto(EMAIL, PASSWORD);
	}
	
	@Test
	void 회원_가입_성공() {
		when(userRepository.findByEmail(any())).thenReturn(Optional.empty());
		when(userRepository.save(any())).thenReturn(requestUser.toEntity());
		when(roleRepository.findByName(any())).thenReturn(Role.builder().id(1L).name("ROLE_USER").build());
		// 실행
		UserResponseDto userResponseDto = userService.save(requestUser);
		// 검증
		assertThat(userResponseDto.getEmail()).isEqualTo(requestUser.getEmail());
	}

	@Test
	void 로그인_성공() {
		// 준비
		when(passwordEncoder.matches(any(), any())).thenReturn(true);
		when(userRepository.findByEmail(any())).thenReturn(Optional.ofNullable(User.builder()
				.email(requestUser.getEmail())
				.password(requestUser.getPassword())
				.roles(Arrays.asList(new Role("USER_ROLE"))).build()));
		when(jwtTokenProvider.createToken(any(), any())).thenReturn("CreateToken");
		// 실행
		String token = userService.login(requestUser);
		// 검증
		assertThat(!token.isEmpty());
	}
}
