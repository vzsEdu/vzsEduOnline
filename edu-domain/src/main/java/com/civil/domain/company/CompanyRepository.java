package com.civil.domain.company;

import com.civil.domain.project.Project;
import com.vzs.persistence.jpa.springdatajpa.VzsJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by byao on 6/20/15.
 */
@Repository
public interface CompanyRepository extends VzsJpaRepository<Company, Long> {
	Company findBycompanyIdAndDeleted(Long companyId, Boolean deleted);
	List<Company> findByuserIdAndDeleted(Long userId, Boolean deleted);
	List<Company> findByuserId(Long userId);
	List<Company> findByUserIdNotAndDeletedAndVerified(Long userId, Boolean deleted, Boolean verified);

}
