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
	/**
	 * 某用户请求根据id获取另一用户信息
	 * @param willGetUserId 请求获取的用户id
	 * @param fromUser 发起请求的用户
	 * @return ACCESS_DENIED 发起请求的用户权限不足
	 *  <br>USER_NOT_FOUND 目标用户不存在
	 *  <br>SUCCESS 请求成功，数据在data.user内，类型为UserInfoView
	 */
	public JSONResult getUserInfo(Integer willGetUserId, User fromUser);
}
