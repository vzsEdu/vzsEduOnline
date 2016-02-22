package com.civil.domain.user;

import com.mysema.query.jpa.impl.JPAQuery;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by byao on 5/23/15.
 */
@Component
public class UserJpaQueryBuilder {

	@PersistenceContext(unitName = "Ben")
	private EntityManager entityManager;

	protected JPAQuery getJpaQuery() {
		return new JPAQuery(entityManager);
	}

	public JPAQuery findUserByUserIdANdPwd(String login_id, String pwd) {
		JPAQuery query = getJpaQuery();
		QUser qUser = QUser.user;
		query.from(qUser);
		query.where(qUser.email.eq(login_id).and(qUser.password.eq(pwd)));
		return query;

	}
}
