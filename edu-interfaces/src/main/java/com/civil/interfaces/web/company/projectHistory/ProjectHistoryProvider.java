package com.civil.interfaces.web.company.projectHistory;

import com.civil.domain.company.projectHistory.ProjectHistory;
import com.civil.domain.company.projectHistory.ProjectHistoryService;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by byao on 6/25/15.
 */
@Component
public class ProjectHistoryProvider {
	@Autowired
	private ProjectHistoryService projectHistoryService;

	public ProjectHistoryDto getProjectHistoryDto(Long userId) {
		ProjectHistoryDto projectHistoryDto = new ProjectHistoryDto();
		ProjectHistory projectHistory = projectHistoryService.getProjectHistoryByUserId(userId);
		if (projectHistory == null) {
			return null;
		}
		BeanUtils.copyProperties(projectHistory, projectHistoryDto);
		return projectHistoryDto;
	}

	public List<ProjectHistoryDto> findProjectHistorysByUserId(Long userId) {
		List<ProjectHistoryDto> projectHistoryDtos = Lists.newArrayList();
		List<ProjectHistory> projectHistorysByUserId = projectHistoryService.findProjectHistorysByUserId(userId);
		for (ProjectHistory projectHistory : projectHistorysByUserId) {
			ProjectHistoryDto projectHistoryDto = new ProjectHistoryDto();
			BeanUtils.copyProperties(projectHistory, projectHistoryDto);
			projectHistoryDtos.add(projectHistoryDto);
		}
		return projectHistoryDtos;
	}

	public List<ProjectHistoryDto> findProjectHistorysByCompanyId(Long companyId) {
		List<ProjectHistoryDto> projectHistoryDtos = Lists.newArrayList();
		List<ProjectHistory> projectHistorysByCompanyId = projectHistoryService.findProjectHistorysByCompanyId(companyId);
		for (ProjectHistory projectHistory : projectHistorysByCompanyId) {
			ProjectHistoryDto projectHistoryDto = new ProjectHistoryDto();
			BeanUtils.copyProperties(projectHistory, projectHistoryDto);
			projectHistoryDtos.add(projectHistoryDto);
		}
		return projectHistoryDtos;
	}

	public ProjectHistoryDto getProjectHistoryDtoById(Long projectHistoryId) {
		ProjectHistoryDto projectHistoryDto = new ProjectHistoryDto();
		ProjectHistory projectHistory = projectHistoryService.findProjectHistoryById(projectHistoryId);

		BeanUtils.copyProperties(projectHistory, projectHistoryDto);
		return projectHistoryDto;
	}
}
