package com.civil.interfaces.web.project.application;

import com.civil.domain.project.application.ProjectApplication;
import com.civil.domain.project.application.ProjectApplicationStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * Created by byao on 8/3/15.
 */
@Data
public class ProjectApplicationDto {
	private Long projectId;
	private String projectApplicationStatusI18n;
	private String comment;
	private Boolean isEditable = Boolean.TRUE;
	private Boolean cancelable = Boolean.FALSE;


	@JsonIgnore
	public static ProjectApplicationDto transFrom(ProjectApplication projectApplication) {
		ProjectApplicationDto projectApplicationDto = new ProjectApplicationDto();
		if (projectApplication == null) {
			return projectApplicationDto;
		}
		ProjectApplicationStatus projectApplicationStatus = projectApplication.getProjectApplicationStatus();

		projectApplicationDto.setProjectId(projectApplication.getProjectId());
		projectApplicationDto.setComment(projectApplication.getComment());
		projectApplicationDto.setProjectApplicationStatusI18n(projectApplicationStatus.getI18n());

		if (projectApplicationStatus == ProjectApplicationStatus.APPLY_ACCEPTED ||
				projectApplicationStatus == ProjectApplicationStatus.APPLY_REFUSED) {
			projectApplicationDto.setIsEditable(Boolean.FALSE);
		}

		if (projectApplicationStatus == ProjectApplicationStatus.APPLYING) {
			projectApplicationDto.setCancelable(Boolean.TRUE);
		}

		return projectApplicationDto;
	}
}
