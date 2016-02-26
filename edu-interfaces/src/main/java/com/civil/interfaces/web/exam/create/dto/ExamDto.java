package com.civil.interfaces.web.exam.create.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by byao on 2/23/16.
 */
@Data
public class ExamDto {
	private String examTitle;
	private List<String> optionQuestion;
	private List<String> answerA;
	private List<String> answerB;
	private List<String> answerC;
	private List<String> answerD;
	private List<String> answerE;
	private List<String> answerF;
}
