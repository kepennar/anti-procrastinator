package org.kepennar.aproc.tasks;

import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK
import static org.springframework.web.bind.annotation.RequestMethod.GET

import javax.inject.Inject

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/tasks")
class RestTaskController {
	final TaskRepository repo
	
	@Inject
	RestTaskController(TaskRepository repo) {
		this.repo = repo
	}
	
    @RequestMapping(method=GET, value="name/{name}")
    ResponseEntity byName(@PathVariable String name) {
		return Optional.ofNullable(repo.findByName(name))
			.map({t -> new ResponseEntity<>(t, OK)})
			.orElse(new ResponseEntity<>(NOT_FOUND));
    }

	
	@RequestMapping(method=GET, value="{id}")
	ResponseEntity task(@PathVariable String id) {
		return Optional.ofNullable(repo.findOne(id))
			.map({t -> new ResponseEntity<>(t, OK)})
			.orElse(new ResponseEntity<>(NOT_FOUND));
	}
	
	
	@RequestMapping(method=GET, value="{id}/majVersion")
	ResponseEntity majVersion(@PathVariable String id) {
		
		Optional.ofNullable(repo.findOne(id))
			.map({t -> 
				repo.save(t);
				new ResponseEntity<>(t, OK)
			})
			.orElse(new ResponseEntity<>(NOT_FOUND));
	}
}