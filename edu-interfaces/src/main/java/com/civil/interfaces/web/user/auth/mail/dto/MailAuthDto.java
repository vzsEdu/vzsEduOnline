package com.civil.interfaces.web.user.auth.mail.dto;

import com.google.common.base.Optional;
import com.vzs.utils.VzsDateUtils;
import com.vzs.utils.VzsToStringBuilder;
import com.vzs.utils.reqular.VzsReqularUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

import java.util.regex.Pattern;

import static com.civil.interfaces.web.user.auth.mail.dto.MailAuthConstant.MAIL_MAX_AGE;
import static com.vzs.utils.encrypt.VzsSecurityConstant.FIELD_SPLIT;

/**
 * Created by byao on 6/4/15.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MailAuthDto {
	public static final Pattern pattern = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
	public static final String version = "1";
	private String sentTime;
	private String loginEmail;

	public boolean validate() {
		boolean eMailValide = VzsReqularUtils.match(pattern, loginEmail);
		return eMailValide && VzsDateUtils.getIntervalSeconds(sentTime) <= MAIL_MAX_AGE ;
	}
	@Override
	public String toString() {
		return  new VzsToStringBuilder(this, FIELD_SPLIT).append(version).append(sentTime).append(loginEmail).toString();
	}
}
