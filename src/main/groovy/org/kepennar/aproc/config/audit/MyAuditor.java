package org.kepennar.aproc.config.audit;

import org.springframework.data.domain.AuditorAware;

public class MyAuditor implements AuditorAware<AuditableUser> {
	private AuditableUser user; 
	
	public MyAuditor(String name) {
		this.user = new AuditableUser(name);
	}
	@Override
	public AuditableUser getCurrentAuditor() {
		return user;
	}

}
