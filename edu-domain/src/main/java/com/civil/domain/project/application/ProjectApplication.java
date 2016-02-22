package com.civil.domain.project.application;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by byao on 7/22/15.
 */
@Data
@Entity
@Table(name = "project_application")
@EqualsAndHashCode(of = { "projectApplicationId" })
public class ProjectApplication {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "project_application_id")
	private Long projectApplicationId;

	@Column(name = "user_id",  nullable = false)
	private Long userId;

	@Column(name = "project_id",  nullable = false)
	private Long projectId;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private ProjectApplicationStatus projectApplicationStatus;

	@Column(name = "comment")
	private String comment;

	@Column(name = "last_updated_time")
	private Date lastUpdateTime;
}
