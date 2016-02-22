package com.civil.interfaces.web.project;

import com.civil.domain.project.application.ProjectApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by byao on 10/31/15.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectApplicationDisplayDto {
	private Long userId;
	private ProjectApplicationStatus projectApplicationStatus;
}
