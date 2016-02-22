package com.civil.interfaces.web.user.signup;

import com.vzs.Infrastructure.auth.Role;
import com.vzs.Infrastructure.auth.User;
import com.vzs.application.encrypt.VzsEncryptProvider;
import com.vzs.meta.locale.i18n.VzsMessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created by zaza on 5/20/15.
 */
@Controller
@RequestMapping("/password")
public class PasswordController {
	@Autowired
	SignupProvider signupProvider;
	@Autowired
	VzsMessageSource messageSource;
	@Autowired
	VzsEncryptProvider vzsEncryptProvider;

	@RequestMapping("/changePassword")
	public ModelAndView signinRequest() {
		ModelAndView modelAndView = new ModelAndView("auth/user/change_password");
		return modelAndView;
	}

	@RequestMapping(value="/changePassword/save", method = RequestMethod.POST )
	@Role("verified,unverified,admin") //to-do
	public ModelAndView signinPersonalFastAction(User user,
												  @RequestParam(value = "password") String password,
												  @RequestParam(value = "new_password") String newPassword) {

		ModelAndView modelAndView = new ModelAndView("auth/user/change_password");

		boolean updatePasswordFlag = signupProvider.updatePasswordByUserId(user.getUserId(), user.getLoginId(), vzsEncryptProvider.makeMd5(password),vzsEncryptProvider.makeMd5(newPassword));
		if (!updatePasswordFlag) {
			modelAndView.addObject("error", messageSource.getMessage("error.old.password"));
			modelAndView.addObject("pswErrorAlertFlag","Y");
		} else {
			modelAndView.addObject("info", messageSource.getMessage("update.password.succeed"));
			modelAndView.addObject("passwordInfoAlert","Y");
		}

		return modelAndView;
	}

}
