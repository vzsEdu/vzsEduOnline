package com.civil.interfaces.web.hanldebars;

import com.github.jknack.handlebars.Handlebars;
import com.vzs.Infrastructure.web.handlebars.Vzsi18nHelper;
import com.vzs.mvc.configuration.web.handlebars.HandlebarsConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by byao on 7/5/15.
 */
@Configuration
public class EduOnlineHanldebarsConfig implements HandlebarsConfigurer {

	@Override
	public void configureHandlebars(Handlebars handlebars) {
		handlebars.registerHelper(QiniuCdnHelper.HELPER_NAME, new QiniuCdnHelper());
	}
}
