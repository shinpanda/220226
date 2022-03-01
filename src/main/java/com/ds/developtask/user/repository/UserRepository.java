package com.ds.developtask.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ds.developtask.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);

}
