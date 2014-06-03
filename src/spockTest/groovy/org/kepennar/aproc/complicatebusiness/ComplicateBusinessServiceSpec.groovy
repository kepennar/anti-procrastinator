package org.kepennar.aproc.complicatebusiness;

import static org.assertj.core.api.Assertions.*
import static org.assertj.core.groups.FieldsOrPropertiesExtractor.extract

import org.kepennar.aproc.tasks.Task
import org.kepennar.aproc.tasks.TaskRepository

import spock.lang.Specification

import spock.lang.*

public class ComplicateBusinessServiceSpec extends Specification {

	@Shared
	List<Task> tasks1 = 
	[
		new Task(name:"zyx", description:"cba"),
		new Task(name:"mol", description:"fed")
	]
	
	@Shared
	List<Task> transformedTasks1 =
	[
		new Task(name:"xyz", description:"abc"),
		new Task(name:"lmo", description:"def")
	]
	
	
	@Shared
	List<Task> tasks2 =
	[
		new Task(name:"aeaeae", description:"babae"),
		new Task(name:"efefef", description:"uiuiu")
	]
		
	@Shared
	List<Task> transformedTasks2 =
	[
		new Task(name:"aaaeee", description:"dedebe"),
		new Task(name:"eeefff", description:"iiuuu")
	]	
	
	void "should return Tasks with ordered fields"() {
				
		TaskRepository repo = Mock()
		ComplicateBusinessService service = new ComplicateBusinessService(repo)
		
		setup:
			repo.findAll() >> tasks
		
		when:
			List<Task> transformedTask = service.getAllTransformedTasks()

		then:
			assertThat(transformedTask).usingFieldByFieldElementComparator().containsExactlyElementsOf(expectedTask)
			
		where:
			tasks 						|| expectedTask
			tasks1						|| transformedTasks1
			tasks2						|| transformedTasks2
	}
}
