package com.civil.interfaces.web.applicationCenter;

import com.civil.domain.applicationCenter.ApplicationCenter;
import com.civil.domain.project.Project;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * Created by byao on 11/8/15.
 */
@Data
public class ApplicationCenterDto {
	private Long applicationCenterId;

	private String softwareName;

	private String softwareDescription;

	private String imagePath;

	private String contactWay;

	private Boolean readonly = false;

	@JsonIgnore
	public static ApplicationCenterDto transferFrom(ApplicationCenter applicationCenter) {
		if (applicationCenter == null) {
			return new ApplicationCenterDto();
		}
		ApplicationCenterDto applicationCenterDto = new ApplicationCenterDto();
		BeanUtils.copyProperties(applicationCenter, applicationCenterDto);
		return applicationCenterDto;
	}

	@JsonIgnore
	public static ApplicationCenterDto transferFrom(ApplicationCenter applicationCenter, Long userId) {
		ApplicationCenterDto applicationCenterDto = transferFrom(applicationCenter);
		if (!applicationCenter.getUserId().equals(userId)) {
			applicationCenterDto.setReadonly(true);
		}
		return applicationCenterDto;
	}

	@JsonIgnore
	public ApplicationCenter convertToProject(Long userId) {
		ApplicationCenter project = new ApplicationCenter();
		BeanUtils.copyProperties(this, project);
		project.setUserId(userId);
		return project;
	}


}
