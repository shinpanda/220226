package com.ds.developtask.user.controller;

import com.ds.developtask.user.dto.UserDto;
import com.ds.developtask.user.dto.UserResponseDto;
import com.ds.developtask.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {
		
	private final UserService userService;
	
	@PostMapping("/signup") 
	public UserResponseDto signup(@RequestBody UserDto user) {
		return userService.save(user);
	}
	 

	@PostMapping("/login") 
	public String login(@RequestBody UserDto user) {
		return userService.login(user);
	}
}
