package com.civil.domain.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by byao on 2/23/16.
 */
@Service
public class ExamQuestionOptionService {

	@Autowired
	private ExamQuestionOptionRepository examQuestionOptionRepository;

	public ExamQuestionOption findById(Long examQuestoinOptionid) {
		if (examQuestoinOptionid == null) {
			return null;
		}
		return examQuestionOptionRepository.findOne(examQuestoinOptionid);
	}

	public ExamQuestionOption save(ExamQuestionOption examQuestionOption) {
		return examQuestionOptionRepository.save(examQuestionOption);
	}


}
