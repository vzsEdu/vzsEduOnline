package com.civil.interfaces.web.user.auth.mail;

import com.vzs.utils.web.VzsWebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Created by byao on 6/4/15.
 */
@Controller
@RequestMapping("/auth/mail")
public class MailAuthController {
	@Autowired
	SendVerifyMailProvider sendVerifyMailProvider;

	@Autowired
	VerifyMailProvider verifyMailProvider;

	@RequestMapping("/send-auth-mail")
	public ModelAndView sendVerifyMail(HttpServletRequest request, @RequestParam String email) {
		ModelAndView modelAndView = new ModelAndView("auth/mail/mail_sent_result");
		String requestAddress = VzsWebUtils.getRequestAddress(request);
		boolean isSuccessful = sendVerifyMailProvider.sendAuthMail(requestAddress, email);
		if (isSuccessful) {
			modelAndView.addObject("sentInfor", "Mail sent successfully");
		} else {
			modelAndView.addObject("sentInfor", "Mail sent fail, please retry");
		}
		return modelAndView;
	}

	@RequestMapping("/auth-mail/verify/{authLink}")
	public ModelAndView verifyAuthMail(@PathVariable("authLink") String authLink) throws UnsupportedEncodingException {
		ModelAndView modelAndView = new ModelAndView("auth/mail/mail_sent_result");
		if (verifyMailProvider.verifyAuthMail(authLink)) {
			modelAndView.addObject("sentInfor", "Mail Auth successfully");
		} else {
			modelAndView.addObject("sentInfor", "Mail Auth Failed, please try again");
		}
		return modelAndView;
	}
}
