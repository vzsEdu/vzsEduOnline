package com.civil.interfaces.web.exam.display.dto;

import com.civil.domain.exam.ExamQuestionOption;
import com.civil.domain.utils.CharacterUtil;
import com.civil.interfaces.web.exam.common.ExamQuestionHelper;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * Created by byao on 2/28/16.
 */
@Data
public class ExamDisplayOptionDto {
	private Long questionId;
	private String questionSeq;
	private String question;

	private List<String> options;

	public static ExamDisplayOptionDto createFrom(ExamDisplayContext examDisplayContext, ExamQuestionOption examQuestionOption) {
		ExamDisplayOptionDto examDisplayOptionDto = new ExamDisplayOptionDto();

		examDisplayOptionDto.setQuestionSeq(examDisplayContext.getQuestionSeqWithIncrease());

		String decorateQestion = ExamQuestionHelper.decorateQestionForOption(examDisplayContext, examQuestionOption.getQuestion(),
				examQuestionOption.getExamQuestionOptionId());
		examDisplayOptionDto.setQuestion(decorateQestion);
		examDisplayOptionDto.setOptions(decorateQuestion(examQuestionOption.options()));
		examDisplayOptionDto.setQuestionId(examQuestionOption.getExamQuestionOptionId());
		return examDisplayOptionDto;
	}

	private static List<String> decorateQuestion(List<String> options) {
		List<String> decoratedOption = Lists.newArrayList();
		for (int i = 0 ; i < options.size() ; i ++) {
			String optionIndeex = CharacterUtil.convertToAlphabetFromInt(i);
			String optionStr = options.get(i);
			StringBuilder sb = new StringBuilder();
			sb.append(optionIndeex).append(": ").append(optionStr).append(" ");
			decoratedOption.add(sb.toString());
		}
		return decoratedOption;
	}



}
