package com.ds.developtask.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ds.developtask.user.domain.Role;

public interface RoleRepository extends JpaRepository<Role, String>{

	Role findByName(String name);

}
