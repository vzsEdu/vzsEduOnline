package com.civil.interfaces.web.exam.create.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by byao on 2/23/16.
 */
@Data
public class ExamDto {
	private Long examId;
	private String examTitle;
	private List<Long> optionId;
	private List<String> optionQuestion;
	private List<String> optionA;
	private List<String> optionB;
	private List<String> optionC;
	private List<String> optionD;
	private List<String> optionE;
	private List<String> optionF;
	private List<String> answer;
}
