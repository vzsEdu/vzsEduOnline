package com.civil.domain.project;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by byao on 6/20/15.
 */
@Data
@Entity
@Table(name = "projects")
@EqualsAndHashCode(of = { "projectId" })
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "project_id")
	private Long projectId;

	@Column(name = "user_id")
	private Long userId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "publish_date", nullable = false)
	Date publishDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "release_date", nullable = false)
	Date releaseDate;

	@Column(name = "project_name")
	private String projectName;

	@Column(name = "province")
	private String province;

	@Column(name = "city")
	private String city;

	@Column(name = "industry_involved")
	private String industryInvolved;

	@Column(name = "project_detail_description")
	private String projectDetailDescription;

	@Column(name = "collaborate_way")
	private String collaborateWay;

	@Column(name = "task_detail_description")
	private String taskDetailDescription;

	@Column(name = "dead_line")
	private String deadLine;

	@Column(name = "profit")
	private String profit;

	@Column(name = "comment")
	private String comment;

	@Column(name = "imagePath")
	private String imagePath;

	@Column(name = "published")
	private Boolean published;

	@Column(name = "deleted")
	private Boolean deleted = false;

	@Column(name = "regId", length = 40, nullable = false)
	private String regId;


}
