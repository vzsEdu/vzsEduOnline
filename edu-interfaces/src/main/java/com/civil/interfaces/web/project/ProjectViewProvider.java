package com.civil.interfaces.web.project;

import com.civil.domain.project.Project;
import com.civil.domain.project.ProjectService;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by byao on 6/25/15.
 */
@Component
public class ProjectViewProvider {
	@Autowired
	private ProjectService projectService;

	public ProjectDto getProjectDto(Long userId, Long projectId) {
		ProjectDto projectDto = new ProjectDto();
		Project project = projectService.findProjectById(projectId);
		BeanUtils.copyProperties(project, projectDto);
		if (project.getUserId().equals(userId) && !project.getPublished()) {
			projectDto.setReadonly(false);
		}
		return projectDto;
	}

	public List<ProjectDto> findProjectsByUserId(Long userId) {
		List<ProjectDto> projectDtos = Lists.newArrayList();
		List<Project> projectsByUserId = projectService.findProjectsByUserId(userId);
		for (Project project : projectsByUserId) {
			ProjectDto projectDto = new ProjectDto();
			BeanUtils.copyProperties(project, projectDto);
			projectDtos.add(projectDto);
		}
		return projectDtos;
	}

	public List<ProjectDto> findNonOwnPublishedProject(Long userId) {
		List<ProjectDto> projectDtos = Lists.newArrayList();
		List<Project> projectsByUserId = projectService.findNonOwnPublishedProject(userId);
		for (Project project : projectsByUserId) {
			ProjectDto projectDto = new ProjectDto();
			BeanUtils.copyProperties(project, projectDto);
			projectDtos.add(projectDto);
		}
		return projectDtos;
	}

	public List<ProjectDto> findMyAppliedProjects(Long userId) {
		List<ProjectDto> projectDtos = Lists.newArrayList();
		List<Project> projectsByUserId = projectService.findMyAppliedProject(userId);
		for (Project project : projectsByUserId) {
			ProjectDto projectDto = new ProjectDto();
			BeanUtils.copyProperties(project, projectDto);
			projectDtos.add(projectDto);
		}
		return projectDtos;
	}
}
