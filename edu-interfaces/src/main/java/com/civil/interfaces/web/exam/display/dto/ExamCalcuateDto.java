package com.civil.interfaces.web.exam.display.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by byao on 3/2/16.
 */
@Data
public class ExamCalcuateDto {
	// options
	List<String> options;
	List<Long> optionQuestionIds;


}
