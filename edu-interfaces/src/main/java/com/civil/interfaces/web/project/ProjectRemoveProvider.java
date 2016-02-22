package com.civil.interfaces.web.project;

import com.civil.domain.project.Project;
import com.civil.domain.project.ProjectService;
import com.vzs.utils.exception.VzsRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by byao on 6/20/15.
 */
@Component
public class ProjectRemoveProvider {
	@Autowired
	private ProjectService projectService;

	public void removeById(Long userId, Long projectId) {
		projectService.deleteById(userId, projectId);
	}
}
