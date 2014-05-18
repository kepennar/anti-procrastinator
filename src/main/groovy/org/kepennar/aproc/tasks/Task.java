package org.kepennar.aproc.tasks;

import org.apache.commons.lang3.builder.ToStringBuilder;
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
    private AuditableUser lastModifiedBy;

    
    public Task() { }
    
	public Task(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
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
	}
	
	public AuditableUser getLastModifiedBy() {
		return lastModifiedBy;
	}
	public void setLastModifiedBy(AuditableUser lastModifiedBy) {
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
		return ToStringBuilder.reflectionToString(this);
	}
	

	
}
