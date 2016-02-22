package com.civil.interfaces.web.student;

import com.civil.domain.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by byao on 2/22/16.
 */
@Controller
@RequestMapping(value = "/student")
public class StudentController {

	@Autowired
	private StudentModifyProvider studentModifyProvider;

	@ResponseBody
	@RequestMapping(value = "/randmonInsert")
	public Student randmonInsert() {
		return studentModifyProvider.insertRandmon();
	}

}
