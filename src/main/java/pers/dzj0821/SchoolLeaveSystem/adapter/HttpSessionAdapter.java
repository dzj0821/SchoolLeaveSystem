package pers.dzj0821.SchoolLeaveSystem.adapter;

import java.security.KeyPair;

import javax.servlet.http.HttpSession;

import pers.dzj0821.SchoolLeaveSystem.pojo.User;

public class HttpSessionAdapter {
	private static final String USER_KEY_NAME = "user";
	private static final String RSA_KEY_PAIR_KEY_NAME = "RSAKeyPair";
	private static final String RSA_CREATE_TIMESTAMP_KEY_NAME = "RSACreateTimestamp";
	private HttpSession session;
	
	public HttpSessionAdapter(HttpSession session) {
		this.session = session;
	}
	
	public User getUser() {
		return (User) session.getAttribute(USER_KEY_NAME);
	}
	
	public void setUser(User user) {
		session.setAttribute(USER_KEY_NAME, user);
	}
	
	public KeyPair getRSAKeyPair() {
		return (KeyPair) session.getAttribute(RSA_KEY_PAIR_KEY_NAME);
	}
	
	public void setRSAKeyPair(KeyPair keyPair) {
		session.setAttribute(RSA_KEY_PAIR_KEY_NAME, keyPair);
	}
	
	public long getRSACreateTimestamp() {
		return (long)session.getAttribute(RSA_CREATE_TIMESTAMP_KEY_NAME);
	}
	
	public void setRSACreateTimestamp(long RSACreateTimestamp) {
		session.setAttribute(RSA_CREATE_TIMESTAMP_KEY_NAME, RSACreateTimestamp);
	}
}
