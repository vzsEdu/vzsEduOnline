package com.civil.domain.applicationCenter;

import com.vzs.persistence.jpa.springdatajpa.VzsJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by byao on 11/8/15.
 */
@Repository
public interface ApplicationCenterRepository extends VzsJpaRepository<ApplicationCenter, Long>, ApplicationCenterRepositoryCustom {
	List<ApplicationCenter> findByUserId(Long userId);
	List<ApplicationCenter> findByUserIdNot(Long userId);

	ApplicationCenter findByUserIdAndApplicationCenterId(Long userId, Long applicationCenterId);
}
