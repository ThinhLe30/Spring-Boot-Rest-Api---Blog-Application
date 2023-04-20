package com.brooklyn.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brooklyn.entity.Category;
import com.brooklyn.entity.Comment;
import com.brooklyn.entity.Post;
import com.brooklyn.exception.ResourceNotFoundException;
import com.brooklyn.payload.CategoryDTO;
import com.brooklyn.payload.CommentDTO;
import com.brooklyn.payload.PostDTO;
import com.brooklyn.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ModelMapper modelMapper;
	private CategoryDTO mapToDTO(Category category) {
		return modelMapper.map(category, CategoryDTO.class);
	}
	private Category mapToEntity(CategoryDTO categoryDTO) {
		return modelMapper.map(categoryDTO, Category.class);
	}
	public CategoryDTO addCategory(CategoryDTO categoryDTO) {
		
		Category category = mapToEntity(categoryDTO);
		Category savedCategory = categoryRepository.save(category);
		return mapToDTO(savedCategory);
	}
	public CategoryDTO getCategory(Integer id) {
		Category category = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "id", String.valueOf(id)));
		return mapToDTO(category);
	}
	public List<CategoryDTO> findAll() {
		List<Category> categories = categoryRepository.findAll();
		// TODO Auto-generated method stub
		List<CategoryDTO> categoryDTOs = categories.stream().map(cate -> mapToDTO(cate)).collect(Collectors.toList());
		return categoryDTOs;
	}
	public CategoryDTO updateCategory(Integer id, CategoryDTO categoryDTO) {
		Category category = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "id", String.valueOf(id)));
		category.setName(categoryDTO.getName());
		category.setDescription(categoryDTO.getDescription());
		Category savedCategory = categoryRepository.save(category);	
		return mapToDTO(savedCategory);
	}
	public void deleteComment(Integer id) {
		// TODO Auto-generated method stub
		Category category = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "id", String.valueOf(id)));
		categoryRepository.delete(category);
		
	}
	public List<PostDTO> findPostsByCategory(Integer cateId) {
		Category category = categoryRepository.findById(cateId).orElseThrow(()-> new ResourceNotFoundException("Category", "id", String.valueOf(cateId)));
		List<Post> posts = category.getPosts();
		return posts.stream().map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
	}
}
