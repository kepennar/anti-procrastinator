package org.kepennar.aproc

import org.kepennar.aproc.config.Constants
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.core.env.SimpleCommandLinePropertySource
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling

@EnableAutoConfiguration
@EnableAsync
@EnableScheduling
@ComponentScan("org.kepennar.aproc")
class Application {
	static void main(String[] args){
		SpringApplication app = new SpringApplication(Application);
		SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);

		// Check if the selected profile has been set as argument.
		// if not the development profile will be added
		addDefaultProfile(app, source);
		app.run(args)
	}

	/**
	 * Set a default profile if it has not been set
	 */
	private static void addDefaultProfile(SpringApplication app, SimpleCommandLinePropertySource source) {
		if (!source.containsProperty("spring.profiles.active")) {
			app.setAdditionalProfiles(Constants.SPRING_PROFILE_DEVELOPMENT);
		}
	}
}