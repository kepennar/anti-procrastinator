package org.kepennar.aproc

import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

import org.springframework.boot.SpringApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate

import spock.lang.*

class MainControllerSpec extends Specification {
 

	@Shared
	@AutoCleanup
	ConfigurableApplicationContext context

	void setupSpec() {
		context = (ConfigurableApplicationContext) SpringApplication.run(Application.class, "--spring.profiles.active=test")
	}

	
	void "should return HTTP status success"() {
		when:
			ResponseEntity entity = new RestTemplate().getForEntity("http://localhost:8888/site", String.class)
			
		then:
			entity.statusCode == HttpStatus.OK
	}
	
	
	void "should say hello!"() {
		when:
			ResponseEntity<String> entity = new RestTemplate().getForEntity(url, String.class)

		then:
			entity.statusCode == HttpStatus.OK
			entity.body == response

		where:
			url                                 	|| response
			'http://localhost:8888/site/everybody' 	|| 'Hello everybody!'
			'http://localhost:8888/site/World' 		|| 'Hello World!'
	}
}
