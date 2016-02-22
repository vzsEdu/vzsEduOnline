package com.civil.interfaces.web.project;

import com.civil.domain.project.Project;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * Created by byao on 6/21/15.
 */
@Data
public class ProjectDto {

	private Long projectId;

	private String projectName;

	private String province;

	private String city;

	private String industryInvolved;

	private String projectDetailDescription;

	private String collaborateWay;

	private String taskDetailDescription;

	private String deadLine;

	private String profit;

	private String comment;

	private String imagePath;

	private Boolean published = false;

	private Boolean readonly = true;

	private ProjectApplicationDisplayDto projectApplicationDisplayDto;

	public Project convertToProject(Long userId) {
		Project project = new Project();
		BeanUtils.copyProperties(this, project);
		project.setUserId(userId);
		project.setRegId(userId + "");
		//we don't have these two fields in design, just keep it for future
		project.setPublishDate(new Date());
		project.setReleaseDate(new Date());
		return project;
	}

}
