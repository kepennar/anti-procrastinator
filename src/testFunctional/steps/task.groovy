
import de.flapdoodle.embed.mongo.Command
import de.flapdoodle.embed.mongo.tests.MongodForTestsFactory 
import de.flapdoodle.embed.mongo.MongodStarter
import de.flapdoodle.embed.mongo.config.RuntimeConfigBuilder
import de.flapdoodle.embed.mongo.config.Net
import de.flapdoodle.embed.mongo.config.Timeout
import de.flapdoodle.embed.mongo.config.IMongodConfig
import de.flapdoodle.embed.mongo.MongodExecutable
import de.flapdoodle.embed.mongo.MongodProcess

import de.flapdoodle.embed.process.runtime.Network

import de.flapdoodle.embed.mongo.config.MongodConfigBuilder
import de.flapdoodle.embed.mongo.distribution.Version

import org.kepennar.aproc.Application

import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

import org.springframework.boot.SpringApplication
import org.springframework.context.ConfigurableApplicationContext

import com.mongodb.BasicDBObject
import com.mongodb.MongoClient
import com.mongodb.WriteConcern
import wslite.rest.RESTClient

import static cucumber.api.groovy.EN.And
import static cucumber.api.groovy.Hooks.*

Before() {
    // Start embedded Mongo
    /*println Command.MongoD

    MongodStarter runtime = MongodStarter.getDefaultInstance();
    IMongodConfig mongodConfig = new MongodConfigBuilder()
        .version(Version.Main.PRODUCTION)
        .build();
    mongodExecutable = runtime.prepare(mongodConfig);
    mongod = mongodExecutable.start();
    */
    
    // Mongo client
    def mongo = new MongoClient()
    mongo.writeConcern = WriteConcern.NORMAL
    db = mongo.getDB("test")
    tasks = db.createCollection("task", new BasicDBObject())

    /*Future future = Executors
    .newSingleThreadExecutor().submit(
        new Callable() {
            @Override
            public ConfigurableApplicationContext call() throws Exception {
                return (ConfigurableApplicationContext) SpringApplication
                .run(Application.class)
            }
        })
    context = future.get(60, TimeUnit.SECONDS)
*/
    client = new RESTClient("http://localhost:8088/api/task")
    
}

After() {
    if (db) {
        db.getCollection("task").drop()    
    }    
    if (mongodExecutable) {
        mongodExecutable.stop()    
    }
}

And(~'^a Task named "([^"]*)"$') { String task ->
    tasks.insert(new BasicDBObject("name", task))
}

And(~'^the Task "([^"]*)" description is "([^"]*)"$') { String task, String description ->
    def query = new BasicDBObject("name", task)
    def update = new BasicDBObject("name", task).append("description", description)
    tasks.update(query, update)
}

And(~'^a Task is requested with name "([^"]*)"$') { String task ->
    response = client.get(path:"/name/${task}")
}

And(~'^the description is "([^"]*)"$') { String description ->
    assert response.statusCode == 200
    assert response.json.description == description
}