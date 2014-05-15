package org.kepennar.aproc.tasks;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class Task {
	
	@Id
	private String id;
	private Date createdAt;
	private String name;
	private String description;
	
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Task [id=" + id + ", createdAt=" + createdAt + ", name=" + name + ", description=" + description + "]";
	}
	
}
