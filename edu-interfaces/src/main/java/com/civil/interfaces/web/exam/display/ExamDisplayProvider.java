package com.civil.interfaces.web.exam.display;

import com.civil.domain.exam.Exam;
import com.civil.domain.exam.ExamQuestionOption;
import com.civil.domain.exam.ExamService;
import com.civil.interfaces.web.exam.display.dto.ExamDisplayContext;
import com.civil.interfaces.web.exam.display.dto.ExamDisplayDto;
import com.civil.interfaces.web.exam.display.dto.ExamDisplayOptionDto;
import com.google.common.collect.Lists;
import com.vzs.utils.VzsCollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by byao on 2/27/16.
 */
@Component
public class ExamDisplayProvider {
	@Autowired
	private ExamService examService;

	private Exam findExamById(Long id) {
		return examService.findById(id);
	}

	public ExamDisplayDto combineExamDtoByExamId(Long id) {
		ExamDisplayDto examDisplayDtoForOption = new ExamDisplayDto();
		ExamDisplayContext examDisplayContext = new ExamDisplayContext();


		Exam examById = findExamById(id);
		examDisplayDtoForOption.setExamTitle(examById.getTitle());
		// options
		examDisplayDtoForOption.setExamDisplayOptionDtos(createDisplayOptions(examDisplayContext, examById.getExamQuestionOptions()));

		return examDisplayDtoForOption;
	}

	private List<ExamDisplayOptionDto> createDisplayOptions(ExamDisplayContext examDisplayContext, List<ExamQuestionOption> examQuestionOptions) {
		List<ExamDisplayOptionDto> examDisplayOptionDtos = Lists.newArrayList();

		if (VzsCollectionUtils.isNotEmpty(examQuestionOptions)) {
			for (ExamQuestionOption examQuestionOption : examQuestionOptions) {
				examDisplayOptionDtos.add(ExamDisplayOptionDto.createFrom(examDisplayContext, examQuestionOption));
			}
		}

		return examDisplayOptionDtos;
	}
}
