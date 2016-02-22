package com.civil.domain.user;

import com.vzs.persistence.jpa.springdatajpa.VzsJpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by zaza on 2015/5/2.
 */
@Repository
public interface UserRepository extends UserRepositoryCustom,VzsJpaRepository<User, Long>{
	User findByEmail(String email);

}
