package com.ds.developtask.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ProductController {
	
	@GetMapping("/product/{id}")
	public Object getById(Long id) {
		return null;
	}
}
