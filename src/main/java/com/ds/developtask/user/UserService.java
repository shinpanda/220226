package com.ds.developtask.user;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ds.developtask.config.JwtTokenProvider;
import com.ds.developtask.user.domain.Role;
import com.ds.developtask.user.domain.User;
import com.ds.developtask.user.repository.RoleRepository;
import com.ds.developtask.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	
	@Autowired
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    
    @Autowired
    private final RedisTemplate<String, String> redisTemplate;
    
    private final String DEFAULT_ROLE = "USER_ROLE";
	
	@Transactional
	public User save(UserDto requestUser) {
        User user = userRepository.findByEmail(requestUser.getEmail()).orElseThrow(() -> new EntityExistsException("해당 이메일로 가입할 수 없습니다."));

		return userRepository.save(User.builder()
				.email(requestUser.getEmail())
				.password(passwordEncoder.encode(requestUser.getPassword()))
				.roles(Arrays.asList(roleRepository.findByName(DEFAULT_ROLE))).build());
	}

	public String login(UserDto requestUser) {
		String errorMessage = "이름과 패스워드를 다시 확인해주세요";
		User user = userRepository.findByEmail(requestUser.getEmail()).orElseThrow(() -> new IllegalArgumentException(errorMessage));

		if (!passwordEncoder.matches(requestUser.getPassword(), user.getPassword())) {
			throw new IllegalArgumentException(errorMessage);
		}

		return jwtTokenProvider.createToken(String.valueOf(user.getEmail()), user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
	}
}
