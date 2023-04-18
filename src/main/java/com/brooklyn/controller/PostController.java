package com.brooklyn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/posts")
public class PostController {
	
	@Autowired
	private PostService postService;
	@GetMapping
	public PostResponse findAll(
			@RequestParam(value = "pageNo", defaultValue = "0",required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10",required = false) int pageSize,
			@RequestParam(value = "sortField", defaultValue = "id",required = false) String sortField,
			@RequestParam(value = "orderBy", defaultValue = "asc",required = false) String orderBy
			){
		return postService.findAll(pageNo, pageSize, sortField, orderBy);
	}
	@GetMapping("/{id}")
	public ResponseEntity<PostDTO> getPost(@PathVariable(name = "id") Integer id) {
		return new ResponseEntity<PostDTO>(postService.get(id), HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO post) {
		return new ResponseEntity<PostDTO>(postService.createPost(post), HttpStatus.CREATED);
	}
	@PutMapping("/{id}")
	public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO post,@PathVariable(name = "id") Integer id){
		return new ResponseEntity<PostDTO>(postService.updatePost(post,id), HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePost(@PathVariable(name = "id") Integer id) {
		postService.deletePost(id);
		return new ResponseEntity<String>("Post was deleted successfully!", HttpStatus.OK);
	}
}
