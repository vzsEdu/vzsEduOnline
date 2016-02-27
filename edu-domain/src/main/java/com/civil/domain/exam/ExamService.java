package com.civil.domain.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by byao on 2/23/16.
 */
@Service
public class ExamService {
	@Autowired
	private ExamRepository examRepository;

	public Exam save(Exam exam) {
		return examRepository.save(exam);
	}

	public Exam findById(Long examId) {
		if (examId == null) {
			return null;
		}
		return examRepository.findOne(examId);
	}


}
