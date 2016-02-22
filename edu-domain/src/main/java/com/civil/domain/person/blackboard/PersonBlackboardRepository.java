package com.civil.domain.person.blackboard;

import com.vzs.persistence.jpa.springdatajpa.VzsJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by zaza on 10/1/15.
 */
@Repository
public interface PersonBlackboardRepository extends VzsJpaRepository<PersonBlackboard, Long> {
	PersonBlackboard findBypersonBlackboardIdAndDeleted(Long personBlackboardId, Boolean deleted);
	List<PersonBlackboard> findByuserIdAndDeleted(Long userId, Boolean deleted);
	List<PersonBlackboard> findByuserId(Long userId);

	@Query("select p " +
			"from PersonBlackboard p ,Person c where p.userId = c.userId and c.personId = :personId and p.deleted = :deleted and c.deleted = :deleted"
	)
	List<PersonBlackboard> findPersonBlackboardsByPersonId(@Param("personId")Long personId,@Param("deleted")Boolean deleted );

}
