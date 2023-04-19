package com.brooklyn.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.brooklyn.entity.Comment;
import com.brooklyn.entity.Post;
import com.brooklyn.exception.BlogApiException;
import com.brooklyn.exception.ResourceNotFoundException;
import com.brooklyn.payload.CommentDTO;
import com.brooklyn.repository.CommentRepository;
import com.brooklyn.repository.PostRepository;

@Service
public class CommentService {
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private PostRepository postRepository;
	private CommentDTO mapToCommentDTO(Comment comment) {
		return new CommentDTO(comment.getId(), comment.getName(), comment.getEmail(), comment.getBody());
	}
	private Comment mapToComment(CommentDTO commentDTO) {
		return new Comment(commentDTO.getId(),commentDTO.getName(), commentDTO.getEmail(), commentDTO.getBody());
	}
	public CommentDTO createComment(CommentDTO commentDTO, Integer postId) {
		Comment comment = mapToComment(commentDTO);
		comment.setPost(postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", String.valueOf(postId))));
		return mapToCommentDTO(commentRepository.save(comment));
	}
	public List<CommentDTO> findAllByPostId(Integer postId) {
		List<Comment> comments = commentRepository.findByPostId(postId);
		// TODO Auto-generated method stub
		List<CommentDTO> commentsDTO = comments.stream().map(comment -> mapToCommentDTO(comment)).collect(Collectors.toList());
		return commentsDTO;
	}
	public CommentDTO getComment(Integer postId, Integer commentId) {
		Post postInDB = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", String.valueOf(postId)));
		Comment commentInDb = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "id", String.valueOf(commentId)));
		if(!commentInDb.getPost().getId().equals(postInDB.getId())){
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post.");
		}
		return mapToCommentDTO(commentInDb);
	}
}
