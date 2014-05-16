
import static cucumber.api.groovy.EN.*
import static cucumber.api.groovy.Hooks.*

import org.kepennar.aproc.Application
import org.springframework.boot.SpringApplication

import wslite.rest.RESTClient

import com.mongodb.BasicDBObject
import com.mongodb.MongoClient
import com.mongodb.WriteConcern


def context = SpringApplication.run(Application.class, "-Pspring.profiles.active=test")

Before() {
	
	// Mongo client
	def mongo = new MongoClient()
	mongo.writeConcern = WriteConcern.NORMAL
	db = mongo.getDB("aproc")
	tasks = db.createCollection("task", new BasicDBObject())
	client = new RESTClient("http://localhost:8088/api/tasks")

}

After() {
	if (db) {
		db.getCollection("task").drop()
	}
}

Given(~'^a Task named "([^"]*)"$') { String task ->
	tasks.insert(new BasicDBObject("name", task))
}

And(~'^the Task "([^"]*)" Id is "([^"]*)"$') { String task, String id ->
	def query = new BasicDBObject("name", task)
	def update = new BasicDBObject("name", task).append("id", id)
	tasks.update(query, update)
}

And(~'^the Task "([^"]*)" description is "([^"]*)"$') { String task, String description ->
	def query = new BasicDBObject("name", task)
	def update = new BasicDBObject("name", task).append("description", description)
	tasks.update(query, update)
}


When(~'^a Task is requested with name "([^"]*)"$') { String task ->
	response = client.get(path:"/name/${task}")
}

When(~'^a Task with name "([^"]*)" is requested by is id$') { task ->
	def dbTask = tasks.find(new BasicDBObject("name", task)).next()
	response = client.get(path:"/${dbTask._id}")
}

Then(~'^the description is "([^"]*)"$') { String description ->
	assert response.statusCode == 200
	assert response.json.description == description
}