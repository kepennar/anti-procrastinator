package org.kepennar.aproc.config.audit;

import org.springframework.data.domain.AuditorAware;

public class MyAuditor implements AuditorAware<AuditableUser> {
	private static final AuditableUser USER = new AuditableUser("Application"); 
	
	@Override
	public AuditableUser getCurrentAuditor() {
		return USER;
	}

}
