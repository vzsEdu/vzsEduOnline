package com.civil.domain.exam;

import com.vzs.persistence.jpa.springdatajpa.VzsJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by byao on 2/23/16.
 */
@Repository
public interface ExamTypeRepository extends VzsJpaRepository<ExamType, Long>, ExamTypeRepositoryCustom{

	List<ExamType> findAll();
}
