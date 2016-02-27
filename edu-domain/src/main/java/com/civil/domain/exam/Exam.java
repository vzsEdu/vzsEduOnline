package com.civil.domain.exam;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by byao on 2/23/16.
 */
@Data
@Entity
@Table(name = "exams")
@EqualsAndHashCode(of = { "examId" })
public class Exam {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "exam_id")
	private Long examId;

	@Column(name = "title",  nullable = false)
	private String title;

	@Column(name = "raise_date",  nullable = false)
	private Date raiseDate;

	@Column(name = "source",  nullable = false)
	private String source; // exam comes from

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "exam_id")
	private List<ExamQuestionOption> examQuestionOptions = Lists.newArrayList();
}
