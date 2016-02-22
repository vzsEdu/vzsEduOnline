package com.civil.domain.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by byao on 2/22/16.
 */
@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public Student save(Student student) {
		return studentRepository.save(student);
	}
}
