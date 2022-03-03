package com.ds.developtask.user.dto;

import com.ds.developtask.user.domain.User;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserDto {

	private String email;
	private String password;
	
	@Builder
	public UserDto(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public User toEntity() {
		return User.builder()
				.email(email)
				.password(password)
				.build();
	}
}
