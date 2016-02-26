package com.civil.domain.exam;

import com.vzs.persistence.jpa.springdatajpa.VzsJpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by byao on 2/23/16.
 */
@Repository
public interface ExamRepository extends VzsJpaRepository<Exam, Long>, ExamTypeRepositoryCustom {
}
