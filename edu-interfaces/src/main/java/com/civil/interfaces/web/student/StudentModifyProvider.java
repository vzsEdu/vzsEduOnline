package com.civil.interfaces.web.student;

import com.civil.domain.Gender;
import com.civil.domain.student.Student;
import com.civil.domain.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by byao on 2/22/16.
 */
@Component
@Transactional
public class StudentModifyProvider {
	@Autowired
	private StudentService studentService;

	public Student insertRandmon() {
		Student student = new Student();

		student.setName("Ben");
		student.setRegisterDate(new Date());
		student.setRegisterGrade("grade two");
		student.setSchool("lin chuan zhong xue");
		student.setBirthday(new Date());
		student.setGender(Gender.MALE);
		student.setPhone("136765432123");
		student.setAddress("changtaiguangchang");
		student.setEMail("byao@coupang.com");
		student.setParentName("lily");
		student.setPhone("1234567");
		student.setParentWeChatId("wechatid");
		return studentService.save(student);
	}
}
