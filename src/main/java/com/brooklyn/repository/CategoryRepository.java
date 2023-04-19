package com.brooklyn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brooklyn.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

	List<Category> findByName(String name);

}
