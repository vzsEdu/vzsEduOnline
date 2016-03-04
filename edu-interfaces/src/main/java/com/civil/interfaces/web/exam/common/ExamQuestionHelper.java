package com.civil.interfaces.web.exam.common;

import com.civil.interfaces.web.exam.display.dto.ExamDisplayContext;

/**
 * Created by byao on 3/1/16.
 */
public class ExamQuestionHelper {
	public static String decorateQestionForOption(ExamDisplayContext examDisplayContext, String question, Long optionQuestionId) {
		while (question.contains(ExamDisplayConstant.questionHolder)) {
			String inputHtml = ExamDisplayConstant.optionInput;
			inputHtml = inputHtml.replaceFirst(ExamDisplayConstant.placeHolderRegx, examDisplayContext.getOptionSeqWithIncrease());
			inputHtml = inputHtml.replaceFirst(ExamDisplayConstant.examIdRegx, optionQuestionId + "");
			question = question.replaceFirst(ExamDisplayConstant.questionHolderRegx, inputHtml);
			question = question.replaceAll("\\n", "<br />");
		}
		return question;
	}
}
