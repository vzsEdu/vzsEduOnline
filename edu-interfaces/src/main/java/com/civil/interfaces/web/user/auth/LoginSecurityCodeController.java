package com.civil.interfaces.web.user.auth;

import com.vzs.mvc.meda.verifycode.VerifyImageDto;
import com.vzs.mvc.meda.verifycode.VerifyImageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by byao on 5/30/15.
 */

@Controller
@RequestMapping("/verifyCode")
public class LoginSecurityCodeController {

	@RequestMapping("/random-image")
	public void crimg(HttpServletRequest request, HttpServletResponse response) throws IOException {
		VerifyImageDto verifyImageDto =
				VerifyImageHelper.generateRandomImage(request, response);
		request.getSession().setAttribute("currentToken", verifyImageDto);
	}

}
