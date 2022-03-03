package com.ds.developtask.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ds.developtask.product.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
