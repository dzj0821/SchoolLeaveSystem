package pers.dzj0821.SchoolLeaveSystem.adapter;

import javax.servlet.http.HttpSession;

import pers.dzj0821.SchoolLeaveSystem.Messages;
import pers.dzj0821.SchoolLeaveSystem.pojo.User;

public class HttpSessionAdapter {
	private HttpSession session;
	
	public HttpSessionAdapter(HttpSession session) {
		this.session = session;
	}
	
	public User getUser() {
		return (User) session.getAttribute(Messages.getString("UserObjectSessionName"));
	}
	
	public void setUser(User user) {
		session.setAttribute(Messages.getString("UserObjectSessionName"), user);
	}
}
