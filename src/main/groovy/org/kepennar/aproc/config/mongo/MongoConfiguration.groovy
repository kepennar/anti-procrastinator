package org.kepennar.aproc.config.mongo;

import static org.kepennar.aproc.config.Constants.NOT_SPRING_PROFILE_TEST
import static org.kepennar.aproc.config.Constants.SPRING_PROFILE_TEST

import org.kepennar.aproc.config.audit.AuditableUser
import org.kepennar.aproc.config.audit.MyAuditor
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.domain.AuditorAware
import org.springframework.data.mongodb.config.AbstractMongoConfiguration
import org.springframework.data.mongodb.config.EnableMongoAuditing

import com.mongodb.Mongo

@Profile(NOT_SPRING_PROFILE_TEST)
@Configuration
@EnableMongoAuditing(auditorAwareRef="auditorProvider")
class MongoConfiguration extends AbstractMongoConfiguration {
		String getDatabaseName() { 
			'aproc' 
		}
	
		Mongo mongo() throws Exception { new Mongo() }
		
		String getMappingBasePackage() { 'org.kepennar.aproc' }
		
		@Bean AuditorAware<AuditableUser> auditorProvider() { new MyAuditor("Application"); }
	    
}

