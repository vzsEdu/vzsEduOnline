package com.civil.interfaces.web.project;

import com.civil.domain.project.Project;
import com.civil.domain.project.ProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by byao on 6/20/15.
 */
@Component
@Transactional
public class ProjectModifyProvider {
	@Autowired
	private ProjectService projectService;

	public Long createProjectByProjectDto(Long userId, ProjectDto projectDto) {
		if (projectDto.getProjectId() != null) {
			return updateProject(userId, projectDto);
		}
		Project project = projectDto.convertToProject(userId);
		project = projectService.save(project);
		return project.getProjectId();
	}

	public Long updateProject(Long userId, ProjectDto projectDto) {
		Project project = projectService.findOwnProject(userId, projectDto.getProjectId());
		BeanUtils.copyProperties(projectDto, project);
		projectService.save(project);
		return project.getProjectId();
	}

}
