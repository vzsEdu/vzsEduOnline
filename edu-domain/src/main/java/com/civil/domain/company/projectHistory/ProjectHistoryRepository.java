package com.civil.domain.company.projectHistory;

import com.vzs.persistence.jpa.springdatajpa.VzsJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Created by byao on 6/20/15.
 */
@Repository
public interface ProjectHistoryRepository extends VzsJpaRepository<ProjectHistory, Long> {
	ProjectHistory findByprojectHistoryIdAndDeleted(Long projectHistoryId, Boolean deleted);
	List<ProjectHistory> findByuserIdAndDeleted(Long userId, Boolean deleted);
	List<ProjectHistory> findByuserId(Long userId);

	@Query("select p " +
			"from ProjectHistory p ,Company c where p.userId = c.userId and c.companyId = :companyId and p.deleted = :deleted and c.deleted = :deleted"
	)
	List<ProjectHistory> findProjectHistoryByCompanyId(@Param("companyId")Long companyId,@Param("deleted")Boolean deleted );
}
