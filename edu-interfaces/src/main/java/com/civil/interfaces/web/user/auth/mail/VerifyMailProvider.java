package com.civil.interfaces.web.user.auth.mail;

import com.civil.domain.user.User;
import com.civil.domain.user.UserService;
import com.civil.interfaces.web.user.auth.mail.dto.MailAuthDto;
import com.vzs.Infrastructure.auth.VzsSecurityManager;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

import static com.vzs.utils.VzsArrayUtils.getDefaultArrayValue;
/**
 * Created by byao on 6/4/15.
 */
@Component
public class VerifyMailProvider {
	@Autowired
	UserService userService;
	@Autowired
	MailSecurityHelper mailSecurityHelper;

	public boolean verifyAuthMail(String authLink) throws UnsupportedEncodingException {
		String[] fields = mailSecurityHelper.getFields(authLink);
		if (!ArrayUtils.isEmpty(fields)) {
			String versionNo = getDefaultArrayValue(fields, 0);
			if (MailAuthDto.version.equals(versionNo)) {
				MailAuthDto mailAuthDto = buildMailAuthDto(fields);
				if (mailAuthDto.validate()) {
					if (acitiveEmailVerified(mailAuthDto.getLoginEmail())) {
						return true;
					}
				}
			}
		}
		return false;

	}

	public boolean acitiveEmailVerified(String email) {
		User user = userService.findByEmail(email);
		if (null != user) {
			user.setEmailVerified(Boolean.TRUE);
			userService.save(user);
			return true;
		}
		return false;
	}

	private MailAuthDto buildMailAuthDto(String[] fields) {
		return MailAuthDto.builder().sentTime(getDefaultArrayValue(fields, 1)).loginEmail(getDefaultArrayValue(fields, 2)).build();
	}

}
