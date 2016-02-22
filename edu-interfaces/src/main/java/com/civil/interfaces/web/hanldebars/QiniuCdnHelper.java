package com.civil.interfaces.web.hanldebars;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import com.vzs.utils.VzsStringUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

/**
 * Created by byao on 7/5/15.
 */
public class QiniuCdnHelper implements Helper<Object> {
	@Value("#{environment['qiniu.image.cdn']}")
	String configPath;

	String qiuniuCdn = "http://7xjjor.com1.z0.glb.clouddn.com/";

	public static final String HELPER_NAME = "qiniu";

	@Override
	public CharSequence apply(Object context, Options options) throws IOException {
		if (context != null) {
			String imagePath = qiuniuCdn + String.valueOf(context);
			if (options.params.length == 2) {
				String widht = options.param(0);
				String height = options.param(1);
				if (VzsStringUtils.isNoneBlank(widht) && VzsStringUtils.isNoneBlank(height)) {
					imagePath = imagePath + resizeFormat(widht, height);
				}
			}
			return imagePath;
		}
		return "";
	}
	private String resizeFormat(String widht, String height) {
		return "?" + String.format("imageView2/1/w/%s/h/%s", widht, height);
	}
}
