package com.brooklyn.payload;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PostDTO {
	private Integer id;
	
	@NotEmpty
	@Size(min = 2, message = "Post title should have at least 2 characters")
	private String title;
	
	@NotEmpty
	@Size(min = 10, message = "Post description should have at least 10 characters")
	private String description;
	
	@NotEmpty
	private String content;
	
	private Set<CommentDTO> comments;
	
	
	private Integer categoryId;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public PostDTO(String title, String description, String content) {
		this.title = title;
		this.description = description;
		this.content = content;
	}
	
	public PostDTO(Integer id, String title, String description, String content) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.content = content;
	}
	public PostDTO() {
	}
	public Set<CommentDTO> getComments() {
		return comments;
	}
	public void setComments(Set<CommentDTO> comments) {
		this.comments = comments;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	
	
}
