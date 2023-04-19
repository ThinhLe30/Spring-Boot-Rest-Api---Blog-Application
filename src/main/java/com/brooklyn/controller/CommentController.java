package com.brooklyn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.brooklyn.payload.CommentDTO;
import com.brooklyn.service.CommentService;

import jakarta.validation.Valid;

@RestController
public class CommentController {
	@Autowired
	private CommentService commentService;
	

	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDTO> createComment(@PathVariable(name = "postId") Integer postId,
													@Valid @RequestBody CommentDTO commentDTO){
		return new ResponseEntity<CommentDTO>(commentService.createComment(commentDTO,postId), HttpStatus.CREATED);
	}
	@GetMapping("/posts/{postId}/comments")
	public List<CommentDTO> findAllByPostId(@PathVariable(name = "postId") Integer postId){
		return commentService.findAllByPostId(postId);
	}
	@GetMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDTO> findAllByPostId(@PathVariable(name = "postId") Integer postId,
											@PathVariable(name = "commentId") Integer commentId){
		return new ResponseEntity<CommentDTO>(commentService.getComment(postId, commentId), HttpStatus.OK);
	}
	

	@PutMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDTO> updateComment(@PathVariable(name = "postId") Integer postId,
			@PathVariable(name = "commentId") Integer commentId,
			@Valid @RequestBody CommentDTO commentDTO){
		return new ResponseEntity<CommentDTO>(commentService.updateComment(postId, commentId, commentDTO), HttpStatus.OK); 
	}
	

	@DeleteMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<String> deleteComment(@PathVariable(name = "postId") Integer postId,
											@PathVariable(name = "commentId") Integer commentId){
		commentService.deleteComment(postId, commentId);
		return new ResponseEntity<String>("Comment was deleted successfully!", HttpStatus.OK);
	}
}
