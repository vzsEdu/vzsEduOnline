package com.civil.domain.user;

import com.mysema.query.jpa.impl.JPAQuery;
import com.vzs.Infrastructure.auth.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by byao on 5/23/15.
 */
public class UserRepositoryImpl implements UserRepositoryCustom {
	@Autowired
	UserJpaQueryBuilder userJpaQueryBuilder;

	@Override
	public User findUserByIdAndPwd(String loginId, String password) {
		JPAQuery userByUserIdANdPwd = userJpaQueryBuilder.findUserByUserIdANdPwd(loginId, password);
		List<User> userList = userByUserIdANdPwd.list(QUser.user);
		if (userList == null || userList.isEmpty()) {
			return null;
		} else if(userList.size() > 1) {
			throw new AuthenticationException("Same userLoginId & password duplicate");
		} else {
			return userList.get(0);
		}
	}
}
