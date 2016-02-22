package com.civil.interfaces.web.user.auth.mail;

import org.bouncycastle.util.encoders.UrlBase64;
import org.junit.Test;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.web.util.UriUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import static org.junit.Assert.assertTrue;

/**
 * Created by byao on 6/5/15.
 */
public class URLEncodeTest {
	String encoded = "vFGjcc4dVCVS7N/UXPTTefJRsG/USQAdq8O1Y1S0YGiOI6SC61c/n2Z+p7LWQRV1lXx1Vvbik/yqUulyU9RgMVwdGPGtZE9wDaMdjPDxowM=";
	@Test
	public void testURLEncode() throws UnsupportedEncodingException {
		String ascii = new String(UrlBase64.encode(encoded.getBytes()));
		System.out.print(ascii);

		String decode = new String(UrlBase64.decode(ascii));
		assertTrue(decode.equals(encoded));
	}
}
