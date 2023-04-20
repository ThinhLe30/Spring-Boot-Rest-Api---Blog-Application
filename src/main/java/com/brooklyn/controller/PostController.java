package com.brooklyn.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brooklyn.payload.PostDTO;
import com.brooklyn.payload.PostResponse;
import com.brooklyn.service.PostService;
import com.brooklyn.utils.AppConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/posts")
@Tag(name = "CRUD REST APIs for POST Resource")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	
	@Operation(
			description = "Get all Posts REST API is used to get all posts in database",
			summary = "Get All Posts Rest API"
			)
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 OK"
			)
	@GetMapping
	public PostResponse findAll(
			@RequestParam(value = "pageNo", defaultValue = AppConstant.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstant.DEFAULT_PAGE_SIZE,required = false) int pageSize,
			@RequestParam(value = "sortField", defaultValue = AppConstant.DEFAULT_SORT_FIELD,required = false) String sortField,
			@RequestParam(value = "orderBy", defaultValue = AppConstant.DEFAULT_ORDER_BY,required = false) String orderBy
			){
		return postService.findAll(pageNo, pageSize, sortField, orderBy);
	}
	
	@Operation(
			description = "Get Post By id REST API is used to get Post by id in database",
			summary = "Get Post By id Rest API"
			)
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 OK"
			)
	@GetMapping("/{id}")
	public ResponseEntity<PostDTO> getPost(@PathVariable(name = "id") Integer id) {
		return new ResponseEntity<PostDTO>(postService.get(id), HttpStatus.OK);
	}
	
	
	@Operation(
			description = "Create Post REST API is used to save post into database",
			summary = "Create Post Rest API"
			)
	@ApiResponse(
			responseCode = "201",
			description = "Http Status 201 CREATED"
			)
	@SecurityRequirement(
			name = "Bearer Authentication"
			)
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO post) {
		return new ResponseEntity<PostDTO>(postService.createPost(post), HttpStatus.CREATED);
	}
	
	
	@Operation(
			description = "Update Post REST API is used to update post and save into database",
			summary = "Update Post Rest API"
			)
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 OK"
			)
	@SecurityRequirement(
			name = "Bearer Authentication"
			)
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO post,@PathVariable(name = "id") Integer id){
		return new ResponseEntity<PostDTO>(postService.updatePost(post,id), HttpStatus.OK);
	}
	
	
	@Operation(
			description = "Delete Post REST API is used to delete post out of database",
			summary = "Delete Post Rest API"
			)
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 OK"
			)
	@SecurityRequirement(
			name = "Bearer Authentication"
			)
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePost(@PathVariable(name = "id") Integer id) {
		postService.deletePost(id);
		return new ResponseEntity<String>("Post was deleted successfully!", HttpStatus.OK);
	}
}
