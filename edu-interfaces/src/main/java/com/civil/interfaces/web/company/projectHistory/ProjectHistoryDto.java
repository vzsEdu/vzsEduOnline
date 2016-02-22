package com.civil.interfaces.web.company.projectHistory;

import com.civil.domain.company.Company;
import com.civil.domain.company.projectHistory.ProjectHistory;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * Created by zaza on 8/16/15.
 */
@Data
public class ProjectHistoryDto {

	private Long projectHistoryId;

	private String projectName;

	private String imagePath;

	private String description;

	public ProjectHistory convertToProjectHistory(Long userId) {
		ProjectHistory projectHistory = new ProjectHistory();
		BeanUtils.copyProperties(this, projectHistory);
		projectHistory.setUserId(userId);
//		company.setRegId(userId + "");
		//we don't have these two fields in design, just keep it for future
		return projectHistory;
	}
}
