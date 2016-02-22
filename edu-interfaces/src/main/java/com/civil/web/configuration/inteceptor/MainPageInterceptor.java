package com.civil.web.configuration.inteceptor;

import com.vzs.Infrastructure.auth.User;
import com.vzs.Infrastructure.auth.UserRequestHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by byao on 8/9/15.
 */

@Component
public class MainPageInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	UserRequestHolder userRequestHolder;


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (modelAndView == null) {
			return;
		}

		User user = userRequestHolder.getUser();
		if (user.isLogin()) {
			modelAndView.addObject( "loginClass","hidden");
			modelAndView.addObject( "userName",user.getEmail());
		} else {
			modelAndView.addObject( "logoutClass","hidden");
		}

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}
}
