package com.civil.domain.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by byao on 2/23/16.
 */
@Service
public class ExamTypeService {
	@Autowired
	private ExamTypeRepository examTypeRepository;

	public List<ExamType> findAll() {
		return examTypeRepository.findAll();
	}
}
