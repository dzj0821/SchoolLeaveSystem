package pers.dzj0821.SchoolLeaveSystem.service;

import java.security.PrivateKey;

import pers.dzj0821.SchoolLeaveSystem.pojo.User;
import pers.dzj0821.SchoolLeaveSystem.pojo.json.JSONResult;

public interface UserService {
	//密码经过RSA加密
	public JSONResult register(String username, String base64RSAPassword, String name, String telephone, PrivateKey privateKey);
	
	public JSONResult login(String username, String base64RSApassword, PrivateKey privateKey);
	
	public JSONResult modify(User user, String base64RSAOldPassword, String base64RSANewPassword, String name, String telephone, PrivateKey privateKey);
	
	public JSONResult logout();
	
	public JSONResult getUserInfo(int willGetUserId, User fromUser);
}
