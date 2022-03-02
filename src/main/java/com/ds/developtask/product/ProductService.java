package com.ds.developtask.product;

import org.springframework.stereotype.Service;

import com.ds.developtask.product.domain.Product;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {
		
	private final ProductRepository productRepository;
	
	public Product findById(long id) {
		return productRepository.findById(id).orElseThrow(RuntimeException::new);
	}
	

}
