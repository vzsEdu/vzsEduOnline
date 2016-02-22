package com.civil.interfaces.web.company.projectHistory;

import com.civil.domain.company.projectHistory.ProjectHistory;
import com.civil.domain.company.projectHistory.ProjectHistoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by byao on 6/20/15.
 */
@Component
@Transactional
public class ProjectHistoryModifyProvider {
	@Autowired
	private ProjectHistoryService projectHistoryService;

	public Long createProjectHistoryByProjectHistoryDto(Long userId, ProjectHistoryDto projectHistoryDto) {
		if (projectHistoryDto.getProjectHistoryId() != null) {
			return updateProjectHistory(userId, projectHistoryDto);
		}
		ProjectHistory projectHistory = projectHistoryDto.convertToProjectHistory(userId);
		projectHistory = projectHistoryService.save(projectHistory);
		return projectHistory.getProjectHistoryId();
	}

	public Long updateProjectHistory(Long userId, ProjectHistoryDto projectHistoryDto) {
		ProjectHistory projectHistory = projectHistoryService.findOwnProjectHistory(userId, projectHistoryDto.getProjectHistoryId());
		BeanUtils.copyProperties(projectHistoryDto, projectHistory);
		projectHistoryService.save(projectHistory);
		return projectHistory.getProjectHistoryId();
	}


}
