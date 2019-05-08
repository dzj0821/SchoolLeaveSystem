package pers.dzj0821.SchoolLeaveSystem.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import pers.dzj0821.SchoolLeaveSystem.Messages;
import pers.dzj0821.SchoolLeaveSystem.annotation.UserTypeRequired;
import pers.dzj0821.SchoolLeaveSystem.pojo.User;
import pers.dzj0821.SchoolLeaveSystem.type.UserType;

public class UserTypeRequiredInterceptor extends HandlerInterceptorAdapter {
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		UserTypeRequired userTypeRequired = ((HandlerMethod)handler).getMethodAnnotation(UserTypeRequired.class);
		if(userTypeRequired == null) {
			return true;
		}
		UserType needUserType = userTypeRequired.value();
		User user = (User) request.getSession().getAttribute(Messages.getString("UserObjectSessionName"));
		if(user == null || user.getType().ordinal() > needUserType.ordinal()) {
			response.sendError(403);
			return false;
		}
		return true;
	};
}
