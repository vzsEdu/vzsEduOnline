package com.civil.interfaces.web.user.auth.mail;

import com.civil.interfaces.web.user.auth.mail.dto.MailAuthDto;
import com.vzs.Infrastructure.auth.VzsSecurityManager;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.encoders.UrlBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import static com.civil.interfaces.web.user.auth.mail.dto.MailAuthConstant.DEFAULT_ENCODE;
import static com.civil.interfaces.web.user.auth.mail.dto.MailAuthConstant.ENCODE;
import static com.civil.interfaces.web.user.auth.mail.dto.MailAuthConstant.SECURITY_KEY;
import static com.vzs.utils.encrypt.VzsSecurityConstant.FIELD_SPLIT;

/**
 * Created by byao on 6/6/15.
 */
@Component
public class MailSecurityHelper {
	@Autowired
	VzsSecurityManager vzsSecurityManager;

	String encryptAuthLink(MailAuthDto mailAuthDto) throws UnsupportedEncodingException {
		String encryptKey = vzsSecurityManager.encryptWithoutSalt(mailAuthDto.toString(), SECURITY_KEY);
		return encodeURL(encryptKey);

	}

	String[] getFields(String authLink) throws UnsupportedEncodingException {
		String decode = decodeURL(authLink);
		String decryptLink = vzsSecurityManager.decryptWithoutSalt(decode, SECURITY_KEY);
		if (!StringUtils.isBlank(decryptLink)) {
			return decryptLink.split(FIELD_SPLIT);
		}
		return null;
	}

	private String encodeURL(String url) throws UnsupportedEncodingException {
		return new String(UrlBase64.encode(url.getBytes(DEFAULT_ENCODE)), DEFAULT_ENCODE);
	}

	private String decodeURL(String url) throws UnsupportedEncodingException {
		return new String(UrlBase64.decode(url), DEFAULT_ENCODE);
	}
}
