package com.ds.developtask.product.controller;

import com.ds.developtask.product.service.ProductService;
import com.ds.developtask.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProductController {

	private final ProductService productService;
	
	@GetMapping("/product/{id}")
	public Product findById(@PathVariable Long id) {
		return productService.findById(id);
	}
}
