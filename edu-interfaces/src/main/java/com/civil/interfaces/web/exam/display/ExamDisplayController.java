package com.civil.interfaces.web.exam.display;

import com.civil.domain.exam.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by byao on 2/27/16.
 */
@Controller
@RequestMapping(value = "/exam/display")
public class ExamDisplayController {

	@Autowired
	private ExamDisplayProvider examDisplayProvider;

	@ResponseBody
	@RequestMapping(value = "/examByName/{examId}", method = RequestMethod.GET)
	public Exam findExamById(@PathVariable("examId") Long examId) {
		return examDisplayProvider.findExamById(examId);
	}
}
