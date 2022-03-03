package com.ds.developtask.order.domain;

import java.lang.reflect.Member;

import javax.persistence.*;

import com.ds.developtask.product.domain.Product;

import com.ds.developtask.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name="Orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userId")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="productId")
	private Product product;

	@Builder
	public Order(Long id, User user, Product product) {
		this.id = id;
		this.user = user;
		this.product = product;
	}
}
