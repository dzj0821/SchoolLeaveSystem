package pers.dzj0821.SchoolLeaveSystem.adapter;

import javax.servlet.http.HttpServletRequest;

import pers.dzj0821.SchoolLeaveSystem.pojo.view.UserInfoView;

public class HttpServletRequestAdapter {
	private static final String USER_INFO_VIEW_NAME = "UserInfoView";
	private HttpServletRequest request;
	
	public HttpServletRequestAdapter(HttpServletRequest request) {
		this.request = request;
	}
	
	public void setUserInfoView(UserInfoView view) {
		request.setAttribute(USER_INFO_VIEW_NAME, view);
	}
	
	public UserInfoView getUserInfoView() {
		return (UserInfoView) request.getAttribute(USER_INFO_VIEW_NAME);
	}
}
