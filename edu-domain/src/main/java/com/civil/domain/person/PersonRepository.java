package com.civil.domain.person;

import com.vzs.persistence.jpa.springdatajpa.VzsJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zaza on 2015/9/14.
 */
@Repository
public interface PersonRepository extends VzsJpaRepository<Person, Long> {
	Person findBypersonIdAndDeleted(Long personId, Boolean deleted);
	List<Person> findByuserIdAndDeleted(Long userId, Boolean deleted);
	List<Person> findByuserId(Long userId);
	List<Person> findByUserIdNotAndDeletedAndVerified(Long userId, Boolean deleted, Boolean verified);

}
