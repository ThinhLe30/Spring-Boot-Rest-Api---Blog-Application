package com.brooklyn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brooklyn.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

	List<Comment> findByPostId(Integer postId);

}
