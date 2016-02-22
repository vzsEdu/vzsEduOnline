package com.civil.web.configuration;

import com.civil.interfaces.web.EduOnlineWebs;
import com.civil.web.configuration.inteceptor.MainPageInterceptor;
import com.civil.web.configuration.inteceptor.UserAccessCustomInterceptor;
import com.vzs.meta.locale.VzsLocale;
import com.vzs.meta.locale.i18n.VzsI18nInterceptor;
import com.vzs.meta.locale.i18n.VzsMessageSource;
import com.vzs.mvc.configuration.web.AbstractVzsWebMvcConfig;
import com.vzs.mvc.configuration.web.handlebars.EnableVzsHandlebars;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

/**
 * Created by byao on 4/21/15.
 */
@Configuration
@EnableAspectJAutoProxy
@EnableVzsHandlebars
@ComponentScan(basePackageClasses = { EduOnlineWebs.class, VzsLocale.class })
public class EduOnlineWebServletApplicationContextConfig extends AbstractVzsWebMvcConfig {
    public static final String UPLOAD_DIR_NON_WINDOWS = "/var/www/virtual/leowong/webapps/ROOT/tmp";
    public static final String UPLOAD_DIR_WINDOWS = "C:\\\\civil\\services\\temp";
    public static final int MAX_UPLOAD_SIZE = 30000000;

    private static String OS = System.getProperty("os.name").toLowerCase();

    @Value("#{environment['mail.host']}")
    private String mailHost;
    @Value("#{environment['mail.user']}")
    private String mailUser;
    @Value("#{environment['mail.password']}")
    private String mailPassword;
    @Value("#{environment['mail.smtp.auth']}")
    private Boolean mailAuth;
    @Value("#{environment['mail.smtp.port']}")
    private Integer port;
    @Value("#{environment['mail.smtp.timeout']}")
    private Long mailTimeout;


    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        loadAuthenticationChain(registry);
        registry.addInterceptor(localeChangeInterceptor());
        registry.addInterceptor(vzsI18nInterceptor());
        registry.addInterceptor(mainPageInterceptor());
        registry.addInterceptor(UserAccessCustomInterceptor());

    }
    @Bean
    public VzsI18nInterceptor vzsI18nInterceptor() {
        return new VzsI18nInterceptor();
    }

    @Bean
    public VzsMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();
        messageResource.setBasenames("classpath:messages/civil");
        messageResource.setDefaultEncoding("UTF-8");
        return new VzsMessageSource(messageResource);
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    @Bean
    public MainPageInterceptor mainPageInterceptor() {
        MainPageInterceptor mainPageInterceptor = new MainPageInterceptor();
        return mainPageInterceptor;
    }

    @Bean
    UserAccessCustomInterceptor UserAccessCustomInterceptor() {
        return new UserAccessCustomInterceptor();
    }

    @Bean
    public SessionLocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.CHINA);
        return sessionLocaleResolver;
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() throws IOException {

        FileSystemResource fileSystemResource = new FileSystemResource(getUploadDir());

        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setUploadTempDir(fileSystemResource);
        commonsMultipartResolver.setMaxUploadSize(MAX_UPLOAD_SIZE);
        return commonsMultipartResolver;
    }

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(mailHost);
        javaMailSender.setPort(port);
        javaMailSender.setUsername(mailUser);
        javaMailSender.setPassword(mailPassword);

        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.auth", mailAuth);
        mailProperties.put("mail.smtp.timeout", mailTimeout);
        mailProperties.put("mail.smtp.debug", false);
        mailProperties.put("mail.smtp.starttls.enable", false);
        mailProperties.put("defaultEncoding", "UTF-8");
        mailProperties.put("mail.mime.charset", "UTF-8");

        javaMailSender.setJavaMailProperties(mailProperties);



        return javaMailSender;
    }
    private String getUploadDir() {
        if (isWindows()) {
            return UPLOAD_DIR_WINDOWS;
        } else {
            return UPLOAD_DIR_NON_WINDOWS;
        }
    }

    private boolean isWindows() {
        return OS.indexOf("windows")>=0;
    }
}
