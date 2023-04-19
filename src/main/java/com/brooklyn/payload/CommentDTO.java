package com.brooklyn.payload;

import com.brooklyn.entity.Post;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class CommentDTO {
	private Integer id;
	private String name;
	private String email;
	private String body;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public CommentDTO(Integer id, String name, String email, String body) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.body = body;
	}
	public CommentDTO() {
	}
	
}
