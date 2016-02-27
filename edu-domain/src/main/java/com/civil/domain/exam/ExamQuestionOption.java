package com.civil.domain.exam;

import com.vzs.utils.VzsStringUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * Created by byao on 2/23/16.
 */
@Data
@Entity
@Table(name = "exam_question_options")
@EqualsAndHashCode(of = { "exam_question_option_id" })
public class ExamQuestionOption {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "exam_question_option_id")
	private Long examQuestionOptionId;

	@Column(name = "exam_id")
	private Long examId;

	@Column(name = "squenceId")
	private Long sequenceId;

	@Column(name = "preReading")
	private String preReading;

	@Column(name = "question")
	private String question;

	@Column(name = "option_A")
	private String optionA;

	@Column(name = "option_B")
	private String optionB;

	@Column(name = "option_C")
	private String optionC;

	@Column(name = "option_D")
	private String optionD;

	@Column(name = "option_E")
	private String optionE;

	@Column(name = "option_F")
	private String optionF;

	@Column(name = "answer")
	private String answer;


	public boolean isValid() {
		return VzsStringUtils.isNoneEmpty(question) && VzsStringUtils.isNoneEmpty(optionA);
	}
}
