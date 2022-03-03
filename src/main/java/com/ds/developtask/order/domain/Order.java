package com.ds.developtask.order.domain;

import com.ds.developtask.product.domain.Product;
import com.ds.developtask.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name="Orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="UserId")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ProductId")
	private Product product;

	@Builder
	public Order(Long id, User user, Product product) {
		this.id = id;
		this.user = user;
		this.product = product;
	}
}
