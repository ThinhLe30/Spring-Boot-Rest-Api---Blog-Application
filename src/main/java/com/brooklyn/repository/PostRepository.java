package com.brooklyn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brooklyn.entity.Post;


public interface PostRepository extends JpaRepository<Post, Integer>{

}
