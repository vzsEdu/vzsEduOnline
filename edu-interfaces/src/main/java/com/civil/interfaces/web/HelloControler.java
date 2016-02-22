package com.civil.interfaces.web;

import com.vzs.Infrastructure.auth.User;
import com.vzs.meta.locale.i18n.VzsMessageSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by byao on 4/21/15.
 */
@Slf4j
@Controller
public class HelloControler {

    @Value("#{environment['locale.default']}")
    String defaultStr;
    @Value("#{environment['mail.user']}")
    private String mailUser;
    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    VzsMessageSource messageSource;


    @RequestMapping("/hello")
    public ModelAndView hello(User user, @RequestParam(defaultValue = "hello Civil") String name, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("hello");
        return mav;
    }


}
