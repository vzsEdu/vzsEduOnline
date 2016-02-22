package com.civil.domain.project.application;

import com.vzs.persistence.jpa.springdatajpa.VzsJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by byao on 7/22/15.
 */
@Repository
public interface ProjectApplicationRepository extends VzsJpaRepository<ProjectApplication, Long>, ProjectApplicationRepositoryCustom {
	List<ProjectApplication> findByUserId(Long userId);
	ProjectApplication findByUserIdAndProjectId(Long userId, Long projectId);
	@Query("select new com.civil.domain.project.application.ProjectAndApplicationCombineDto(v, p) " +
			"from ProjectApplication v ,Project p where v.projectId = p.projectId and p.userId = :userId and v.projectApplicationStatus in (:projectStatus)" +
			""
	)
	List<ProjectAndApplicationCombineDto> findMyToBeApprovePrjects(@Param("userId")Long userId, @Param("projectStatus")ProjectApplicationStatus... projectStatus);

	@Query("select new com.civil.domain.project.application.ProjectAndApplicationCombineDto(v, p) " +
			"from ProjectApplication v ,Project p where v.projectId = p.projectId and p.userId = :userId and v.projectApplicationStatus in (:projectStatus)" +
			" and p.projectId = :projectId"
	)
	List<ProjectAndApplicationCombineDto> findMyToBeApproveValidPrjects(@Param("userId")Long userId, @Param("projectId")Long projectId, @Param("projectStatus")ProjectApplicationStatus... projectStatus);
}
