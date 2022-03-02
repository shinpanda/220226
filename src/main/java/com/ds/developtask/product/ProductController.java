package com.ds.developtask.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ds.developtask.product.domain.Product;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ProductController {

	@Autowired
	ProductService productService;
	
	@GetMapping("/product/{id}")
	public Product findById(@PathVariable Long id) {
		return productService.findById(id);
	}
}
