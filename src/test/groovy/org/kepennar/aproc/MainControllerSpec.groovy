package org.kepennar.aproc

import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate

import spock.lang.*

@ContextConfiguration(classes = Application, loader = SpringApplicationContextLoader)
class MainControllerSpec extends Specification {

	
	void "should return HTTP status success"() {
		when:
		ResponseEntity entity = new RestTemplate().getForEntity("http://localhost:8088/site", String.class)

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
		'http://localhost:8088/site/everybody' 	|| 'Hello everybody!'
		'http://localhost:8088/site/World' 		|| 'Hello World!'
	}
}
