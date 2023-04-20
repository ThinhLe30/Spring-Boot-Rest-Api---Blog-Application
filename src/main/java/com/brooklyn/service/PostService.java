package com.brooklyn.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.brooklyn.entity.Category;
import com.brooklyn.entity.Post;
import com.brooklyn.exception.ResourceNotFoundException;
import com.brooklyn.payload.PostDTO;
import com.brooklyn.payload.PostResponse;
import com.brooklyn.repository.CategoryRepository;
import com.brooklyn.repository.PostRepository;

@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ModelMapper modelMapper;
	private PostDTO mapToDTO(Post postSaved) {
//		return new PostDTO(postSaved.getId(),postSaved.getTitle(), postSaved.getDescription(), postSaved.getContent());
		return modelMapper.map(postSaved, PostDTO.class);
	}
	private Post mapToPost(PostDTO postDTO) {
//		return new Post(postDTO.getTitle(), postDTO.getDescription(), postDTO.getContent());
		return modelMapper.map(postDTO, Post.class);
	}
	public PostResponse findAll(int pageNo, int pageSize, String sortField, String orderBy){
		Sort sort = orderBy.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<Post> posts = postRepository.findAll(pageable);
		List<Post> listOfPost = posts.getContent();
		
		List<PostDTO> content = listOfPost.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(content);
		postResponse.setPageNo(pageNo);
		postResponse.setPageSize(pageSize);
		postResponse.setTotalElements(posts.getTotalElements());
		postResponse.setTotalPages(posts.getTotalPages());
		postResponse.setLast(posts.isLast());
		
		return postResponse;
	}
	public PostDTO createPost(PostDTO postDTO) {
		Category category = categoryRepository.findById(postDTO.getCategoryId()).orElseThrow(()-> new ResourceNotFoundException("Category", "id", String.valueOf(postDTO.getCategoryId())));
		Post post = mapToPost(postDTO);
		post.setCategory(category);
		return mapToDTO(postRepository.save(post));
	}
	public PostDTO get(Integer id) {
		return mapToDTO(postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", String.valueOf(id))));
	}
	public PostDTO updatePost(PostDTO postDTO, Integer id) {
		Category category = categoryRepository.findById(postDTO.getCategoryId()).orElseThrow(()-> new ResourceNotFoundException("Category", "id", String.valueOf(postDTO.getCategoryId())));	
		Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", String.valueOf(id)));
		post.setTitle(postDTO.getTitle());
		post.setDescription(postDTO.getDescription());
		post.setContent(postDTO.getContent());
		post.setCategory(category);
		return mapToDTO(postRepository.save(post));
	}
	public void deletePost(Integer id) {
		Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", String.valueOf(id)));
		postRepository.delete(post);
	}

}

