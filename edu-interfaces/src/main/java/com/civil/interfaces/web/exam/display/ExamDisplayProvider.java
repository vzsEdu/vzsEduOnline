package com.civil.interfaces.web.exam.display;

import com.civil.domain.exam.Exam;
import com.civil.domain.exam.ExamQuestionOption;
import com.civil.domain.exam.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by byao on 2/27/16.
 */
@Component
public class ExamDisplayProvider {
	@Autowired
	private ExamService examService;

	public Exam findExamById(Long id) {
		return examService.findById(id);
	}
}
