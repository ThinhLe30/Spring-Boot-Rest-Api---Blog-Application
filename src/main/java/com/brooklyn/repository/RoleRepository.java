package com.brooklyn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brooklyn.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	Optional<Role> findByName(String name);
}
