package com.brooklyn.payload;

public class CategoryDTO {
	private Integer id;
	private String name;
	private String description;
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CategoryDTO(Integer id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	public CategoryDTO() {
	}
	
	
}
