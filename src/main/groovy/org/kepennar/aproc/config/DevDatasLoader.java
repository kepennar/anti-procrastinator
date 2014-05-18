package org.kepennar.aproc.config;

import static org.apache.commons.io.IOUtils.toByteArray;

import java.util.List;

import javax.inject.Inject;

import org.kepennar.aproc.tasks.Task;
import org.kepennar.aproc.tasks.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Profile(Constants.SPRING_PROFILE_DEVELOPMENT)
@Component
public class DevDatasLoader implements InitializingBean {
	private final static Logger LOG = LoggerFactory.getLogger(DevDatasLoader.class);
	private final TaskRepository taskRepository;
	
	@Inject
	public DevDatasLoader(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		taskRepository.deleteAll();
		
		String sampleDatas = new String(toByteArray(ClassLoader.getSystemResourceAsStream("sample-datas/tasks.json")));
		
		List<Task> tasks = new ObjectMapper().readValue(sampleDatas, new TypeReference<List<Task>>(){});
		taskRepository.save(tasks);
		LOG.info("{} sample tasks saved in DB", tasks.size());
		
	}
}
