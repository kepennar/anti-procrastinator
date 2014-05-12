package org.kepennar.aproc.tasks;

import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK
import static org.springframework.web.bind.annotation.RequestMethod.GET

import java.util.Optional

import javax.inject.Inject

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/task")
class TaskController {
	final TaskRepository repo
	
	@Inject
	TaskController(TaskRepository repo) {
		this.repo = repo
	}
	
    @RequestMapping(method=GET, value="name/{name}")
    ResponseEntity byName(@PathVariable String name) {
		return new ResponseEntity<>(this.repo.findByName(name), OK)
    }

	
	@RequestMapping(method=GET, value="{id}")
	ResponseEntity task(@PathVariable BigInteger id) {

		return Optional.ofNullable(this.repo.findOne(id))
			.map({t -> new ResponseEntity<>(t, OK)})
			.orElse(new ResponseEntity<>(NOT_FOUND));
	}
}