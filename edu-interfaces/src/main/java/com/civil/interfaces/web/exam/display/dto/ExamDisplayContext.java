package com.civil.interfaces.web.exam.display.dto;

import lombok.Data;

/**
 * Created by byao on 3/1/16.
 */

public class ExamDisplayContext {
	int questionSeq = 1;
	int optionSeq = 1;

	public String getQuestionSeq() {
		return questionSeq + "";
	}

	public String getQuestionSeqWithIncrease() {

		return questionSeq++ + "";

	}

	public String getOptionSeqWithIncrease() {
		return optionSeq++ + "";
	}

	public String getCurrentOptionSeq() {
		return optionSeq + "";
	}

}
