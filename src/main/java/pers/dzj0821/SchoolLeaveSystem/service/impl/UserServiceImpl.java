package pers.dzj0821.SchoolLeaveSystem.service.impl;

import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.dzj0821.SchoolLeaveSystem.dao.UserDao;
import pers.dzj0821.SchoolLeaveSystem.pojo.User;
import pers.dzj0821.SchoolLeaveSystem.pojo.json.JSONResult;
import pers.dzj0821.SchoolLeaveSystem.service.UserService;
import pers.dzj0821.SchoolLeaveSystem.type.JSONCodeType;
import pers.dzj0821.SchoolLeaveSystem.util.RSAUtil;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	private Logger logger = LogManager.getLogger(UserServiceImpl.class);
	private JSONResult serverError = new JSONResult(JSONCodeType.ServerError, "服务器错误，请稍后再试", null);

	@Override
	public JSONResult register(String username, String base64RSAPassword, String name, int telephone,
			PrivateKey privateKey) {
		// 验证输入
		if (!Pattern.matches(Messages.getString("UserServiceImpl.UsernameRegex"), username)) { //$NON-NLS-1$
			return new JSONResult(JSONCodeType.RegisterUsernameInvalid,
					Messages.getString("UserServiceImpl.RegisterUsernameInvalid"), null); //$NON-NLS-1$
		}
		if (!Pattern.matches("[u4e00-u9fa5]{2,4}", name)) {
			return new JSONResult(JSONCodeType.RegisterNameInvalid, "姓名不符合要求", null);
		}
		if (!Pattern.matches("^(13|14|15|17|18|19)[0-9]{9}$", Integer.toString(telephone))) {
			return new JSONResult(JSONCodeType.RegisterTelephoneInvalid, "手机号不符合要求", null);
		}
		JSONResult Invalidpassword = new JSONResult(JSONCodeType.RegisterPasswordInvalid, "密码不符合要求", null);
		String password = null;
		//密码解码
		try {
			password = new String(RSAUtil.decrypt(Base64.getDecoder().decode(base64RSAPassword), privateKey));
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			return Invalidpassword;
		}
		if (password == null || !Pattern.matches("^[a-zA-Z0-9]{9,18}$", password)
				|| Pattern.matches("^[0-9]*$", password) || Pattern.matches("^[a-zA-Z]*$", password)) {
			return Invalidpassword;
		}
		//验证用户名是否存在
		User user = null;
		try {
			user = userDao.findUserByUsername(username);
		} catch (Exception e) {
			logger.warn("SQL Error", e);
			return serverError;
		}
		if(user != null) {
			return new JSONResult(JSONCodeType.RegisterUsernameAlreadyExist, "用户名已存在", null);
		}
		user = new User(username, password, name, telephone);
		try {
			userDao.addUser(user);
		} catch (Exception e) {
			logger.warn("SQL Error", e);
			return serverError;
		}
		HashMap<String, Object> data = new HashMap<>();
		data.put("id", user.getId());
		JSONResult success = new JSONResult(JSONCodeType.Success, "注册成功", data);
		success.put("user", user);
		return success;
	}

	@Override
	public JSONResult login(String username, String password, PrivateKey privateKey) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public JSONResult modify(String username, String password, String name, int telephone) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public JSONResult logout() {
		// TODO 自动生成的方法存根
		return null;
	}

}
