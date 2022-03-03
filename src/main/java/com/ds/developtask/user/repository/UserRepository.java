package com.ds.developtask.user.repository;

import com.ds.developtask.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByEmail(String email) throws IllegalArgumentException;
	void deleteByEmail(String email);
}
