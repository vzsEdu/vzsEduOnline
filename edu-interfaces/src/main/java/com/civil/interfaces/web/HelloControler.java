package com.civil.interfaces.web;

import com.civil.interfaces.web.person.blackboard.PersonBlackboardDto;
import com.civil.interfaces.web.person.blackboard.PersonBlackboardModifyProvider;
import com.civil.web.configuration.inteceptor.UserAccessCustom;
import com.vzs.Infrastructure.auth.Role;
import com.vzs.Infrastructure.auth.User;
import com.vzs.meta.locale.i18n.VzsMessageSource;
import com.vzs.utils.exception.VzsRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
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
//    @UserAccessCustom(fieldName = "accountType", value = "2")
    public ModelAndView hello(User user, @RequestParam(defaultValue = "hello Civil") String name, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("hello");
        mav.addObject("user", user);
        mav.addObject("helloWords", messageSource.getMessage("ben.test"));
        mav.addObject("configTesting", defaultStr);
        return mav;
    }

    private static String tempSave;

    @Autowired
    private PersonBlackboardModifyProvider personBlackboardModifyProvider;

    @RequestMapping("/hello/saveSummerNote")
    public ModelAndView saveSummerNote(User user,@RequestParam String summerNoteHtml) {
        tempSave = summerNoteHtml;
        ModelAndView mav = new ModelAndView("hello");
        mav.addObject("summerNoteHtml", summerNoteHtml);

        PersonBlackboardDto personBlackboardDto = new PersonBlackboardDto();
        personBlackboardDto.setBlackboard(summerNoteHtml);
        personBlackboardModifyProvider.createPersonBlackboardByPersonBlackboardDto(user.getUserId(), personBlackboardDto);

        return mav;

    }

    @RequestMapping("/hello/exception")
    public void helloException() {
        throw new VzsRuntimeException("testing");
    }

}
