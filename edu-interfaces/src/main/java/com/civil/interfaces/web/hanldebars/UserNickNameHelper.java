package com.civil.interfaces.web.hanldebars;

import com.civil.interfaces.web.company.CompanyDto;
import com.civil.interfaces.web.company.CompanyViewProvider;
import com.civil.interfaces.web.person.PersonDto;
import com.civil.interfaces.web.person.PersonViewProvider;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import com.vzs.Infrastructure.auth.User;
import com.vzs.Infrastructure.auth.UserRequestHolder;
import com.vzs.meta.locale.i18n.VzsMessageSource;
import com.vzs.utils.VzsStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Created by byao on 11/1/15.
 */
public class UserNickNameHelper implements Helper<Object> {
	public static final String HELPER_NAME = "loginUserNickName";

	@Autowired
	private UserRequestHolder userRequestHolder;

	@Autowired
	VzsMessageSource messageSource;

	@Autowired
	private CompanyViewProvider companyViewProvider;

	@Autowired
	private PersonViewProvider personViewProvider;

	@Override
	public CharSequence apply(Object context, Options options) throws IOException {
		if (userRequestHolder != null && userRequestHolder.getUser() != null && userRequestHolder.getUser().isLogin()) {
			String name;
			User user = userRequestHolder.getUser();
			if (user.getAccountType() == 1) {
				CompanyDto companyDto = companyViewProvider.getCompanyDto(user.getUserId());
				name = companyDto == null ? null : companyDto.getCompanyName();
			} else {
				PersonDto personDto = personViewProvider.getPersonDto(user.getUserId());
				name = personDto == null ? null : personDto.getPersonName();
			}
			return VzsStringUtils.isNotBlank(name) ? name : user.getEmail();
		}
		return messageSource.getMessage("welcome_login");
	}
}
