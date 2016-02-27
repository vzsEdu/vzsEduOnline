package com.civil.interfaces.web.exam.create;

import com.civil.domain.exam.Exam;
import com.civil.domain.exam.ExamQuestionOption;
import com.civil.domain.exam.ExamQuestionOptionService;
import com.civil.domain.exam.ExamService;
import com.civil.interfaces.web.exam.create.dto.ExamDto;
import com.google.common.collect.Lists;
import com.vzs.utils.VzsCollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by byao on 2/23/16.
 */
@Component
@Transactional
public class ExamModifyProvider {

	@Autowired
	private ExamService examService;

	@Autowired
	private ExamQuestionOptionService examQuestionOptionService;

	public void saveExam(ExamDto examDto) {
		Exam exam = findExam(examDto);
		List<ExamQuestionOption> examQuestionOptions = updateExamQuestionOption(exam.getExamId(), examDto);
	}

	private List<ExamQuestionOption> updateExamQuestionOption(Long examId, ExamDto examDto) {
		List<Long> optionIds = examDto.getOptionId();
		List<String> optionQuestions = examDto.getOptionQuestion();
		List<String> answerAs = examDto.getAnswerA();
		List<String> answerBs = examDto.getAnswerB();
		List<String> answerCs = examDto.getAnswerC();
		List<String> answerDs = examDto.getAnswerD();
		List<String> answerEs = examDto.getAnswerE();
		List<String> answerFs = examDto.getAnswerF();

		if (VzsCollectionUtils.isEmpty(optionIds)) {
			return Lists.newArrayList();
		}

		List<ExamQuestionOption> examQuestionOptions = Lists.newArrayListWithCapacity(optionIds.size());

		for (int i = 0 ; i < optionIds.size() ; i++) {
			Long optionId = optionIds.get(i);
			String optionQuestion = optionQuestions.get(i);
			String optionA = answerAs.get(i);
			String optionB = answerBs.get(i);
			String optionC = answerCs.get(i);
			String optionD = answerDs.get(i);
			String optionE = answerEs.get(i);
			String optionF = answerFs.get(i);

			ExamQuestionOption examQuestionOption = new ExamQuestionOption();
			if (optionId != null) {
				examQuestionOption = examQuestionOptionService.findById(optionId);
			} else {
				examQuestionOption.setExamId(examId);
			}

			examQuestionOption.setQuestion(optionQuestion);
			examQuestionOption.setOptionA(optionA);
			examQuestionOption.setOptionB(optionB);
			examQuestionOption.setOptionC(optionC);
			examQuestionOption.setOptionD(optionD);
			examQuestionOption.setOptionE(optionE);
			examQuestionOption.setOptionF(optionF);

			examQuestionOptions.add(examQuestionOption);
			if (examQuestionOption.isValid()) {
				examQuestionOptionService.save(examQuestionOption);
			}
		}

		return examQuestionOptions;
	}

	private Exam findExam(ExamDto examDto) {
		Long examId = examDto.getExamId();
		Exam exam = examService.findById(examId);
		if (exam == null) {
			exam = new Exam();
			exam.setRaiseDate(new Date());
		}
		exam.setSource("Default");
		exam.setTitle(examDto.getExamTitle());
		return examService.save(exam);
	}
}
