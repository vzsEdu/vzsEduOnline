package com.civil.interfaces.web.user.auth;

import com.civil.domain.user.UserService;
import com.vzs.Infrastructure.auth.AuthenticationException;
import com.vzs.Infrastructure.auth.User;
import com.vzs.Infrastructure.auth.VzsUserFactory;
import com.vzs.application.encrypt.VzsEncryptProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by byao on 5/23/15.
 */
@Component
public class AuthProvider {

	@Autowired
	UserService userService;
	@Autowired
	VzsUserFactory vzsUserFactory;
	@Autowired
	VzsEncryptProvider vzsEncryptProvider;

	public boolean authUser(HttpServletRequest request, HttpServletResponse response,
	                        String loginId, String pwd, boolean isRememberLogin) {
		com.civil.domain.user.User userByLoiginIdAndPwd = userService.findUserByLoiginIdAndPwd(loginId, vzsEncryptProvider.makeMd5(pwd));
		if (userByLoiginIdAndPwd == null) {
			return false;
		}
		User authUser = transfer(request, userByLoiginIdAndPwd);
		try {
			if (! isRememberLogin) {
				vzsUserFactory.createTempUser(request, authUser);
			} else {
				vzsUserFactory.createUser(response, authUser);
			}
		} catch (Exception e) {
			throw new AuthenticationException("User Login create fail");
		}

		return true;
	}

	private User transfer(HttpServletRequest request, com.civil.domain.user.User user) {
		User authUser = vzsUserFactory.createEmptyUser(request);
		authUser.setVerifiedLevel(user.getVerifiedLevel());
		authUser.setNickName(user.getNickName());
		authUser.setEmail(user.getEmail());
		authUser.setLoginId(user.getLoginId());
		authUser.setRoleIds(user.getRoleIds());
		authUser.setUserId(user.getUserId());
		authUser.setAccountType(user.getAccountType());
		return authUser;
	}

}
