package com.civil.interfaces.web.core.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by byao on 6/4/15.
 */
@Component
public class EduOnlineMailFactory {
	@Value("#{environment['mail.address']}")
	private String mailAddress;
	@Autowired
	JavaMailSender javaMailSender;

	public MimeMessageHelper getCivilMimeMailMessage(String to) throws MessagingException {
		MimeMessage simpleMailMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(simpleMailMessage, true, "utf-8");
		mimeMessageHelper.setFrom(mailAddress);
		mimeMessageHelper.setTo(to);
		mimeMessageHelper.setReplyTo("noreply@civil.com");
		return mimeMessageHelper;
	}

}
