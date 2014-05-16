package org.kepennar.aproc.tasks;

import org.joda.time.DateTime;
import org.kepennar.aproc.config.audit.AuditableUser;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

public class Task {
	
	@Id
	private String id;
	@Version
	private Long version;
	@CreatedDate
    private DateTime createdAt;
    @LastModifiedDate
    private DateTime lastModified;
    @CreatedBy
    private AuditableUser createdBy;
    @LastModifiedBy
    private String lastModifiedBy;

    
    
	private String name;
	private String description;
	
	public String getId() {
		return id;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}

	public void setId(String id) {
		this.id = id;
	}
	public DateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}
	public DateTime getLastModified() {
		return lastModified;
	}
	public void setLastModified(DateTime lastModified) {
		this.lastModified = lastModified;
	}
	public AuditableUser getCreatedBy() {
		return createdBy;
	}	
	public void setCreatedBy(AuditableUser createdBy) {
		this.createdBy = createdBy;
	}public String getLastModifiedBy() {
		return lastModifiedBy;
	}
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Task [id=" + id + ", createdAt=" + createdAt + ", lastModified=" + lastModified + ", name=" + name + ", description="
				+ description + "]";
	}
	

	
}
