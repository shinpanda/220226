package com.ds.developtask.user.domain;

import com.ds.developtask.order.domain.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Getter
@NoArgsConstructor
@Entity
@Table(name="Member")
public class User{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "user_roles", 
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
		)
	private Collection<Role> roles;

	@OneToMany(mappedBy = "user")
	@OrderBy("id asc")
	private List<Order> orders = new ArrayList<Order>();
	
	@Builder
	public User(Long id, String email, String password, Collection<Role> roles, List<Order> orders) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.orders = orders;
	}
}
