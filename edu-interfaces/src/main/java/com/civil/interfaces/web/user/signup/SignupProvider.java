package com.civil.interfaces.web.user.signup;

import com.civil.domain.user.User;
import com.civil.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by byao on 6/20/15.
 */
@Component
@Transactional

public class SignupProvider {
	@Autowired
	private UserService userService;

	public void saveUsers(String email, String password, int accountType) {
		User user = new User();
		user.setLoginId(email);
		user.setEmail(email);
		user.setPassword(password);
		user.setAccountType(accountType);
		user.setVerifiedLevel(-1);
		user.setActiveInd(true);
		user.setEmailVerified(false);
		user.setRoleId("unverified");

		userService.save(user);
	}

	public boolean checkUniqueEmail (String email) {
		User user = userService.findByEmail(email);
		if (user != null) {
			return false;
		}
		return true;
	}

	public boolean updateVerifyLevelByUserId (Long userId, Integer verifyLevel) {
		return userService.updateVerifyLevel(userId, verifyLevel);
	}

	public boolean updatePasswordByUserId (Long userId, String loginId, String oldPassword, String newPassword) {
		if (userService.findUserByLoiginIdAndPwd(loginId, oldPassword) != null) {
			userService.updatePassword(userId, newPassword);
			return true;
		}
		return false;
	}
}
