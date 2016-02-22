package com.civil.interfaces.web.project.approve;

import com.civil.domain.project.Project;
import com.civil.domain.project.application.ProjectAndApplicationCombineDto;
import com.civil.domain.project.application.ProjectApplication;
import com.civil.domain.project.application.ProjectApplicationService;
import com.civil.domain.project.application.ProjectApplicationStatus;
import com.civil.interfaces.web.project.ProjectApplicationDisplayDto;
import com.civil.interfaces.web.project.ProjectDto;
import com.google.common.collect.Lists;
import com.vzs.utils.VzsCollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by byao on 10/31/15.
 */
@Component
public class ProjectApproveProvider {
	@Autowired
	private  ProjectApplicationService projectApplicationService;

	public List<ProjectDto> findMyApproveListProject(Long userId) {
		List<ProjectAndApplicationCombineDto> myProjectApplications = projectApplicationService.toBeApproveProject(userId);
		List<ProjectDto> projectDtos = Lists.newArrayList();
		for (ProjectAndApplicationCombineDto combineDto : myProjectApplications) {
			ProjectDto projectDto = new ProjectDto();
			Project project = combineDto.getProject();
			BeanUtils.copyProperties(project, projectDto);

			ProjectApplication projectApplication = combineDto.getProjectApplication();
			ProjectApplicationDisplayDto projectApplicationDisplayDto =
					new ProjectApplicationDisplayDto(projectApplication.getUserId(),projectApplication.getProjectApplicationStatus());
			projectDto.setProjectApplicationDisplayDto(projectApplicationDisplayDto);
			projectDtos.add(projectDto);
		}
		return projectDtos;
	}

	public Boolean approveProject(Long userId, long projectId) {
		Boolean isValid = false;
		List<ProjectAndApplicationCombineDto> projectAndApplicationCombineDtos = projectApplicationService.toBeApproveProjectValid(userId, projectId);

		if (VzsCollectionUtils.isNotEmpty(projectAndApplicationCombineDtos) && projectAndApplicationCombineDtos.size() == 1) {
			ProjectAndApplicationCombineDto combineDto = projectAndApplicationCombineDtos.get(0);
			ProjectApplication projectApplication = combineDto.getProjectApplication();
			if (projectApplication.getProjectApplicationStatus().equals(ProjectApplicationStatus.APPLYING)) {
				isValid = true;

				projectApplicationService.acceptApplicationStatus(projectApplication.getProjectApplicationId());
			}
		}

		return isValid;
	}
}
