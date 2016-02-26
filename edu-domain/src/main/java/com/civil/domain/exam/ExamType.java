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
public class ExamType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "exam_type_id")
	private Long examTypeId;

	@Column(name = "name",  nullable = false)
	private String name;

	@Column(name = "description")
	private String description;
}
