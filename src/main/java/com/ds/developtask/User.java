package com.ds.developtask;

import java.util.function.IntPredicate;

import javax.persistence.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@NoArgsConstructor
@Entity
public class User {
	private String id;
	private String email;
	private String password;
	
	@Builder
	public User(String id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}	
}
