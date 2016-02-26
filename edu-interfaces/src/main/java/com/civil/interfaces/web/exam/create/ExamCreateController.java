package com.civil.interfaces.web.exam.create;

import com.civil.interfaces.web.exam.create.dto.ExamDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by byao on 2/23/16.
 */
@Controller
@RequestMapping(value = "/exam/create")
public class ExamCreateController {

	@RequestMapping(value = "/new-exam")
	public ModelAndView createNewExam() {
		ModelAndView mav = new ModelAndView("/exam/create/createExamPaper");
		return mav;
	}

	@ResponseBody
	@RequestMapping(value = "/save-exam")
	public ExamDto saveNewExam(@ModelAttribute ExamDto examDto) {
		return examDto;
	}
}
