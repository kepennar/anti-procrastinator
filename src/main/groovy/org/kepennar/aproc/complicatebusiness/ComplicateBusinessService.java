package org.kepennar.aproc.complicatebusiness;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.kepennar.aproc.tasks.Task;
import org.kepennar.aproc.tasks.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ComplicateBusinessService {

	private final TaskRepository taskRepository;
	
	@Inject
	public ComplicateBusinessService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}
	
	public List<Task> getAllTransformedTasks() {
		return taskRepository.findAll().parallelStream()
			.map(t -> {
				Task transformTask = new Task();
				transformTask.setName(sort(t.getName()));
				transformTask.setDescription(sort(t.getDescription()));
				
				return transformTask;
			})
			.collect(Collectors.toList());
		
	}
	
	private final static String sort(String word) {
		return String.join("", 
				StringUtils.sortStringArray(word.split(""))
				);
	}
	
}
