package com.civil.interfaces.web.user.auth.mail;

import com.civil.interfaces.web.core.mail.CivilMailFactory;
import com.civil.interfaces.web.user.auth.mail.dto.MailAuthDto;
import com.vzs.meta.locale.i18n.VzsMessageSource;
import com.vzs.utils.VzsDateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

/**
 * Created by byao on 6/4/15.
 */
@Slf4j
@Component
public class SendVerifyMailProvider {
	@Autowired
	private CivilMailFactory civilMailFactory;
	@Autowired
	JavaMailSender javaMailSender;
	@Autowired
	VzsMessageSource messageSource;
	@Autowired
	MailSecurityHelper mailSecurityHelper;

	private static final String verifyLink = "/auth/mail/auth-mail/verify/";

	public boolean sendAuthMail(String requestAddress, String email)  {
		MailAuthDto mailAuthDto = createMailAuthDto(email);

		if (mailAuthDto.validate()) {
			try {
				MimeMessageHelper mimeMessageHelper = civilMailFactory.getCivilMimeMailMessage(email);

				mimeMessageHelper.setSubject(messageSource.getMessage("mail.subject"));
				String mailBody = messageSource.getMessage("mail.body");
				mailBody = String.format(mailBody, getVerifyLink(requestAddress, mailAuthDto));
				mimeMessageHelper.setText(mailBody, true);
				javaMailSender.send(mimeMessageHelper.getMimeMessage());

				return true;

			} catch (MessagingException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}

		return false;

	}

	private String getVerifyLink(String requestAddress, MailAuthDto mailAuthDto) throws UnsupportedEncodingException {
		return requestAddress + verifyLink + mailSecurityHelper.encryptAuthLink(mailAuthDto);

	}

	private MailAuthDto createMailAuthDto(String email) {
		return MailAuthDto.builder().loginEmail(email).sentTime(VzsDateUtils.getNowTime()).build();
	}
}
