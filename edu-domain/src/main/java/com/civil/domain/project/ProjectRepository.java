package com.civil.domain.project;

import com.vzs.persistence.jpa.springdatajpa.VzsJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by byao on 6/20/15.
 */
@Repository
public interface ProjectRepository extends VzsJpaRepository<Project, Long>, ProjectRepositoryCustom {
	Project findByprojectIdAndDeleted(Long projectId, Boolean deleted);
	List<Project> findByuserIdAndDeleted(Long userId, Boolean deleted);
	List<Project> findByUserIdNotAndDeletedAndPublished(Long userId, Boolean deleted, Boolean published);

	@Query("select p from ProjectApplication v ,Project p where v.projectId = p.projectId and v.userId = :userId" +
			"")
	List<Project> findMyAppliedProject(@Param("userId") Long userId);
}
