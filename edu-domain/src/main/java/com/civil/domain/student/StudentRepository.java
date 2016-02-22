package com.civil.domain.student;

import com.vzs.persistence.jpa.springdatajpa.VzsJpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by byao on 2/22/16.
 */
@Repository
public interface StudentRepository extends VzsJpaRepository<Student, Long>, StudentRepositoryCustom{
}
