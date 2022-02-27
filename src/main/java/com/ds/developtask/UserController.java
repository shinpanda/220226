package com.ds.developtask;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	
	@PostMapping("/signup") 
	public Object signup(@RequestBody UserSaveRequestDto user) {
		return null;
	}
	 

}
