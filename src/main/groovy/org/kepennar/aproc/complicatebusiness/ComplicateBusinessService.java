package org.kepennar.aproc.complicatebusiness;

import static java.util.stream.Collectors.toList;

import java.util.List;

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
				return new Task(sort(t.getName()), sort(t.getDescription()));
			})
			.collect(toList());
		
	}
	
	private final static String sort(String word) {
		return String.join("", 
				StringUtils.sortStringArray(word.split(""))
				);
	}
	
}
