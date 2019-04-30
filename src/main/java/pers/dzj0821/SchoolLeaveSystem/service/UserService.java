package pers.dzj0821.SchoolLeaveSystem.service;

import javax.servlet.http.HttpSession;

public interface UserService {
	//the password was hex256 encode and then RSA encrypt 
	public String register(String username, String password, String name, int telephone, String RSAPrivateKey);
	
	public String login(String username, String password, String RSAPrivateKey, HttpSession session);
	
	public String modify(String username, String password, String name, int telephone);
	
	public String logout();
}
