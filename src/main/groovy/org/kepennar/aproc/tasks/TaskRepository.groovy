package org.kepennar.aproc.tasks

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository extends MongoRepository<Task, BigInteger> {
	List<Task> findByName(String name)
}
