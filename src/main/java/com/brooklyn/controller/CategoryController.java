package com.brooklyn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brooklyn.payload.CategoryDTO;
import com.brooklyn.service.CategoryService;

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
}
