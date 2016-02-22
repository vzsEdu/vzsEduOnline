package com.civil.domain.applicationCenter;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * Created by byao on 11/8/15.
 */
@Data
@Entity
@Table(name = "application_center")
@EqualsAndHashCode(of = { "applicationCenterId" })
public class ApplicationCenter {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "application_center_id")
	private Long applicationCenterId;

	@Column(name = "user_id", length = 100)
	private Long userId;

	@Column(name = "software_name", length = 100)
	private String softwareName;

	@Column(name = "software_description")
	private String softwareDescription;

	@Column(name = "image_path")
	private String imagePath;

	@Column(name = "contact_way")
	private String contactWay;
}
