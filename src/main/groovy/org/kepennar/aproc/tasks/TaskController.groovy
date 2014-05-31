package org.kepennar.aproc.tasks;

import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK
import static org.springframework.web.bind.annotation.RequestMethod.GET

import javax.inject.Inject

import org.kepennar.aproc.thymeleaf.components.PageWrapper
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("site/tasks")
class TaskController {
	final TaskRepository repo
	
	@Inject
	TaskController(TaskRepository repo) {
		this.repo = repo
	}
	
    @RequestMapping(method = GET)
    String list(Model model, Pageable pageable) {
		model.addAttribute("currentMenu", "tasks");
		
		PageWrapper<Task> page = new PageWrapper<>(repo.findAll(pageable), 'tasks');
		model.addAttribute("page", page);
		
		return "/tasks/tasksList";
    }

}