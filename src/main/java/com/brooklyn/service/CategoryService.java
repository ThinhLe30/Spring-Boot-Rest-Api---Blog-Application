package com.brooklyn.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brooklyn.entity.Category;
import com.brooklyn.payload.CategoryDTO;
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
}
