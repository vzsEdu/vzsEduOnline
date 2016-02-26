package com.civil.domain.exam;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * Created by byao on 2/23/16.
 */
@Data
@Entity
@Table(name = "check_points")
@EqualsAndHashCode(of = { "checkPointId" })
public class CheckPoint {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "check_point_id")
	private Long checkPointId;

	@Column(name = "check_point_name_level_one",  nullable = false)
	private String checkPointNameLevelOne;

	@Column(name = "check_point_name_level_two",  nullable = false)
	private String checkPointNameLevelTwo;

	@Column(name = "check_point_name_level_three")
	private String checkPointNameLevelThree;

	@Column(name = "check_point_name_level_four")
	private String checkPointNameLevelFour;

	@Column(name = "check_point_desc")
	private String checkPointDesc;

}
