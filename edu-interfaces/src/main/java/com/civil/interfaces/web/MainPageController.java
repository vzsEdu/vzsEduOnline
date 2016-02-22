package com.civil.interfaces.web;

import com.civil.web.configuration.inteceptor.MainPageInterceptor;
import com.vzs.Infrastructure.auth.Role;
import com.vzs.Infrastructure.auth.User;
import com.vzs.mvc.configuration.custom.CustomInterceptors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zaza on 5/5/15.
 */
@Slf4j
@Controller
public class MainPageController {
    @RequestMapping("/")
    public ModelAndView mainPage(ModelAndView mav) {
        mav.setViewName("main/main");
        return mav;
    }


    @RequestMapping("/register")
    public ModelAndView register(User user, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("user/register");
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping("/myCenter")
    @CustomInterceptors(value = { MainPageInterceptor.class })
    public ModelAndView myCenter(User user) {

        if (user != null && user.getAccountType() == 1) {
            return new ModelAndView(new RedirectView("/company/view/company"));
        } else {
            return new ModelAndView(new RedirectView("/person/view/person"));
        }

    }
}
