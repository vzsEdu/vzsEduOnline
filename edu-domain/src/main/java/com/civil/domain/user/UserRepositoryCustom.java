package com.civil.domain.user;

/**
 * Created by byao on 5/23/15.
 */
public interface UserRepositoryCustom {
	User findUserByIdAndPwd(String loginId, String password);
}
