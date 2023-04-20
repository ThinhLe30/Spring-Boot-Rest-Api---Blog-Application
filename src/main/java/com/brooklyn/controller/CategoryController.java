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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brooklyn.payload.CategoryDTO;
import com.brooklyn.payload.CommentDTO;
import com.brooklyn.payload.PostDTO;
import com.brooklyn.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
		return new ResponseEntity<CategoryDTO>(categoryService.addCategory(categoryDTO), HttpStatus.CREATED);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDTO> getCategory(@PathVariable(name = "id") Integer id) {
		return new ResponseEntity<CategoryDTO>(categoryService.getCategory(id), HttpStatus.OK);
	}
	@GetMapping("/{id}/posts")
	public List<PostDTO> getPostsByCategory(@PathVariable(name = "id") Integer cateId) {
		return categoryService.findPostsByCategory(cateId);
	}
	@GetMapping
	public List<CategoryDTO> findAll(){
		return categoryService.findAll();
	}
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CategoryDTO> updateCategory(@PathVariable(name = "id") Integer id, @RequestBody CategoryDTO categoryDTO){
		return new ResponseEntity<CategoryDTO>(categoryService.updateCategory(id, categoryDTO), HttpStatus.OK); 
	}
	

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteComment(@PathVariable(name = "id") Integer id){
		categoryService.deleteComment(id);
		return new ResponseEntity<String>("Category was deleted successfully!", HttpStatus.OK);
	}
	
}
