package pers.dzj0821.SchoolLeaveSystem.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import pers.dzj0821.SchoolLeaveSystem.Messages;
import pers.dzj0821.SchoolLeaveSystem.annotation.RSATimestampCheck;
import pers.dzj0821.SchoolLeaveSystem.pojo.json.JSONResult;
import pers.dzj0821.SchoolLeaveSystem.type.JSONCodeType;

public class RSATimestampCheckInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		if (handlerMethod.getMethodAnnotation(RSATimestampCheck.class) != null) {
			String clientRSACreateTimestamp = request.getParameter(Messages.getString("RSACreateTimestampModelName")); //$NON-NLS-1$
			if (clientRSACreateTimestamp == null) {
				response.sendError(400);
				return false;
			}
			long clientRSACreateTimestampLong = 0;
			try {
				clientRSACreateTimestampLong = Long.parseLong(clientRSACreateTimestamp);
			} catch (NumberFormatException e) {
				response.sendError(400);
				return false;
			}
			if (clientRSACreateTimestampLong != (long) request.getSession()
					.getAttribute(Messages.getString("RSACreateTimestampSessionName"))) { //$NON-NLS-1$
				JSONResult result = new JSONResult(JSONCodeType.SessionTimeout, Messages.getString("SessionTimeout"), //$NON-NLS-1$
						null);
				request.setAttribute(Messages.getString("NeedForwardResultRequestName"), result); //$NON-NLS-1$
				request.getRequestDispatcher(Messages.getString("ForwardResultPath")).forward(request, response); //$NON-NLS-1$
				return false;
			}
		}
		return true;
	}
}
