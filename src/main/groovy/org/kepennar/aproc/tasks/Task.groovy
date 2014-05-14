package org.kepennar.aproc.tasks

import org.springframework.data.annotation.Id

class Task {
	@Id
	String id
	Date createdAt
	String name
	String description

}
