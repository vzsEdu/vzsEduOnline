package com.civil.interfaces.web.project.application;

import com.civil.domain.project.application.ProjectApplication;
import com.civil.domain.project.application.ProjectApplicationService;
import com.vzs.Infrastructure.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by byao on 8/3/15.
 */
@Component
public class ProjectApplicationProvider {
	@Autowired
	ProjectApplicationService projectApplicationService;

	public ProjectApplicationDto applyProject(User user, Long projectId, String comment) {
		ProjectApplication projectApplication = projectApplicationService.applyProject(user.getUserId(), projectId, comment);
		return ProjectApplicationDto.transFrom(projectApplication);
	}

	public ProjectApplicationDto findProjectApplication(User user, Long projectId) {
		ProjectApplication projectApplication =
				projectApplicationService.findProjectApplictionByUserIdAndProjectId(user.getUserId(), projectId);
		return ProjectApplicationDto.transFrom(projectApplication);
	}

	public ProjectApplicationDto cancelProjectApplication(User user, Long projectId) {
		ProjectApplication projectApplication =
				projectApplicationService.cancelProject(user.getUserId(), projectId);
		return ProjectApplicationDto.transFrom(projectApplication);
	}
}
