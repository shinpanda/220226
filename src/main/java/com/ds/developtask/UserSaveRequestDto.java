package com.ds.developtask;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserSaveRequestDto {
	
	private String id;
	private String email;
	private String password;
	
	@Builder
	public UserSaveRequestDto(String id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}
	
	public User toEntity() {
		return User.builder()
				.id(id)
				.email(email)
				.password(password).build();
	}
}
