package com.brooklyn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.brooklyn.entity.Post;


public interface PostRepository extends JpaRepository<Post, Integer>{

}
