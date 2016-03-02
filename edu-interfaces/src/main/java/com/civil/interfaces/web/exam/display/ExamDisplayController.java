package com.civil.interfaces.web.exam.display;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by byao on 2/27/16.
 */
@Controller
@RequestMapping(value = "/exam/display")
public class ExamDisplayController {

	@Autowired
	private ExamDisplayProvider examDisplayProvider;

	@RequestMapping(value = "/examById/{examId}", method = RequestMethod.GET)
	public ModelAndView findExamById(@PathVariable("examId") Long examId) {
		ModelAndView mav = new ModelAndView("/exam/display/displayExamPaper");
		mav.addObject("examDisplayDto", examDisplayProvider.combineExamDtoByExamId(examId));
		return mav;
	}
}
