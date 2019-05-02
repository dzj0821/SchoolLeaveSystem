package pers.dzj0821.SchoolLeaveSystem.service;

import java.security.PrivateKey;

import pers.dzj0821.SchoolLeaveSystem.pojo.json.JSONResult;

public interface UserService {
	//the password was hex256 encode and then RSA encrypt 
	public JSONResult register(String username, String base64RSAPassword, String name, int telephone, PrivateKey privateKey);
	
	public JSONResult login(String username, String base64RSApassword, PrivateKey privateKey);
	
	public JSONResult modify(String username, String password, String name, int telephone);
	
	public JSONResult logout();
}
