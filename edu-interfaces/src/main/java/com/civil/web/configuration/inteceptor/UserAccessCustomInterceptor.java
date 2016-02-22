package com.civil.web.configuration.inteceptor;

import com.google.common.base.Optional;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.vzs.Infrastructure.auth.User;
import com.vzs.Infrastructure.auth.UserRequestHolder;
import com.vzs.utils.VzsAnnotationUtils;
import com.vzs.utils.exception.VzsRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * Created by byao on 11/22/15.
 */
@Component
public class UserAccessCustomInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	UserRequestHolder userRequestHolder;

	private final Cache<Method, Optional<UserAccessCustom>> roleCache = CacheBuilder.newBuilder().maximumSize(10000L).build();

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		Optional<UserAccessCustom> userAccessCutsom = getUserAccessCutsom(handlerMethod.getMethod());
		if (!userAccessCutsom.isPresent()) {
			return;
		}
		User user = userRequestHolder.getUser();
		if (!user.isLogin()) {
			throw new VzsRuntimeException("Please login first");
		}
		if (!checkRole(userAccessCutsom.orNull(), user)) {
			throw new VzsRuntimeException("User access is wrong, please contact admin");
		}
	}

	protected boolean checkRole(UserAccessCustom userAccessCustom, User user) {
		if(userAccessCustom != null && userAccessCustom.value() != null) {
			String fieldName = userAccessCustom.fieldName();
			String value = userAccessCustom.value();

			Field field = ReflectionUtils.findField(User.class, fieldName);
			field.setAccessible(true);
			Object valueObject = ReflectionUtils.getField(field, user);
			Class<?> returnType = field.getType();
			try {
				if (valueObject == null) {
					return false;
				}
				if (returnType.equals(String.class)) {
					return valueObject.equals(value);
				} else if (returnType.equals(Integer.class)) {
					return valueObject.equals(Integer.parseInt(value));
				} else if (returnType.equals(Boolean.class)) {
					return valueObject.equals(Boolean.parseBoolean(value));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public Optional<UserAccessCustom> getUserAccessCutsom(final Method method) {
		try {
			return (Optional)this.roleCache.get(method, new Callable() {
				public Optional<UserAccessCustom> call() throws Exception {
					UserAccessCustom role = VzsAnnotationUtils.findAnnotationsFromMethodAndClass(method, UserAccessCustom.class);
					return Optional.fromNullable(role);
				}
			});
		} catch (ExecutionException var3) {
			throw new IllegalStateException(var3.getMessage(), var3);
		}
	}

}
