package com.brooklyn.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.brooklyn.entity.Post;
import com.brooklyn.exception.ResourceNotFoundException;
import com.brooklyn.payload.PostDTO;
import com.brooklyn.payload.PostResponse;
import com.brooklyn.repository.PostRepository;

@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;
	
	private PostDTO mapToDTO(Post postSaved) {
		return new PostDTO(postSaved.getId(),postSaved.getTitle(), postSaved.getDescription(), postSaved.getContent());
	}
	private Post mapToPost(PostDTO postDTO) {
		return new Post(postDTO.getTitle(), postDTO.getDescription(), postDTO.getContent());
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
	public PostDTO createPost(PostDTO postDTO) {;
		return mapToDTO(postRepository.save(mapToPost(postDTO)));
	}
	public PostDTO get(Integer id) {
		return mapToDTO(postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", String.valueOf(id))));
	}
	public PostDTO updatePost(PostDTO postDTO, Integer id) {
		Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", String.valueOf(id)));
		post.setTitle(postDTO.getTitle());
		post.setDescription(postDTO.getDescription());
		post.setContent(postDTO.getContent());
		return mapToDTO(postRepository.save(post));
	}
	public void deletePost(Integer id) {
		Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", String.valueOf(id)));
		postRepository.delete(post);
	}

}

