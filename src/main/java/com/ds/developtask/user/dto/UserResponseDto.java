package com.ds.developtask.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserResponseDto {

	private Long id;
	private String email;

	@Builder
	public UserResponseDto(Long id, String email) {
		this.id = id;
		this.email = email;
	}
}
