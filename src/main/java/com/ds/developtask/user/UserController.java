package com.ds.developtask.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ds.developtask.user.domain.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserController {
		
	private final UserService userService; 
	
	@PostMapping("/signup") 
	public User signup(@RequestBody UserDto user) {
		return userService.save(user);
	}
	 

	@PostMapping("/login") 
	public String login(@RequestBody UserDto user) {
		return userService.login(user);
	}
}
