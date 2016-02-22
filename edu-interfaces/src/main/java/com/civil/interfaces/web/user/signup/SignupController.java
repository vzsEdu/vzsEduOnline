package com.civil.interfaces.web.user.signup;

import com.vzs.application.encrypt.VzsEncryptProvider;
import com.vzs.meta.locale.i18n.VzsMessageSource;
import com.vzs.mvc.meda.verifycode.VerifyImageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by byao on 5/20/15.
 */
@Controller
@RequestMapping("/signup")
public class SignupController {
	@Autowired
	SignupProvider signupProvider;
	@Autowired
	VzsMessageSource messageSource;
	@Autowired
	VzsEncryptProvider vzsEncryptProvider;

	@RequestMapping("/personal")
	public ModelAndView signinRequest() {
		ModelAndView modelAndView = new ModelAndView("auth/sign/signup");
		return modelAndView;
	}

	@RequestMapping("/personal/fastSignUp")
	public ModelAndView signinPersonalFastAction(HttpServletRequest request,
												  @RequestParam(value = "email") String email,
												  @RequestParam(value = "password") String password,
												  @RequestParam(value = "verifyCode") String verifyCode) {
		return fastSignUp(request, email, password, verifyCode, 2);
	}

	@RequestMapping("/company/fastSignUp")
	public ModelAndView signinCompanyFastAction(HttpServletRequest request,
												 @RequestParam(value = "email") String email,
												 @RequestParam(value = "password") String password,
												 @RequestParam(value = "verifyCode") String verifyCode) {
		return fastSignUp(request, email, password, verifyCode, 1);
	}

	private ModelAndView fastSignUp(HttpServletRequest request,String email,String password,
									 String verifyCode,Integer accountType) {
		ModelAndView mav = new ModelAndView("auth/sign/signup");
		ModelAndView mavSuccess = new ModelAndView("auth/sign/signup_success");
		VerifyImageDto verifyImageDto = (VerifyImageDto)request.getSession().getAttribute("currentToken");

		if (!verifyImageDto.getToken().equalsIgnoreCase(verifyCode)) {
			mav.addObject("error", messageSource.getMessage("error.verify_code"));
			mav.addObject("alertFlag","Y");
			return mav;
		} else if (!signupProvider.checkUniqueEmail(email)){
			mav.addObject("error", messageSource.getMessage("error.email.unique"));
			mav.addObject("alertFlag","Y");
			return mav;
		} else {
			signupProvider.saveUsers(email, vzsEncryptProvider.makeMd5(password), accountType);
			return mavSuccess;
		}
	}

}
