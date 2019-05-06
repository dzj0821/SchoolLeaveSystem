package pers.dzj0821.SchoolLeaveSystem.service.impl;

import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.util.Base64;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.dzj0821.SchoolLeaveSystem.Messages;
import pers.dzj0821.SchoolLeaveSystem.dao.UserDao;
import pers.dzj0821.SchoolLeaveSystem.pojo.User;
import pers.dzj0821.SchoolLeaveSystem.pojo.json.JSONResult;
import pers.dzj0821.SchoolLeaveSystem.service.UserService;
import pers.dzj0821.SchoolLeaveSystem.type.JSONCodeType;
import pers.dzj0821.SchoolLeaveSystem.util.RSAUtil;
import pers.dzj0821.SchoolLeaveSystem.util.SHA256Util;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	private Logger logger = LogManager.getLogger(UserServiceImpl.class);
	private JSONResult serverError = new JSONResult(JSONCodeType.ServerError, Messages.getString("ServerError"), null); //$NON-NLS-1$

	@Override
	public JSONResult register(String username, String base64RSAPassword, String name, String telephone,
			PrivateKey privateKey) {
		// 验证输入
		if (!Pattern.matches(Messages.getString("UsernameRegex"), username)) { //$NON-NLS-1$
			return new JSONResult(JSONCodeType.RegisterUsernameInvalid, Messages.getString("InvalidUsername"), null); //$NON-NLS-1$
		}
		if (!Pattern.matches(Messages.getString("NameRegex"), name)) { //$NON-NLS-1$
			System.out.println(name);
			return new JSONResult(JSONCodeType.RegisterNameInvalid, Messages.getString("InvalidName"), null); //$NON-NLS-1$
		}
		if (!Pattern.matches(Messages.getString("TelephoneRegex"), telephone)) { //$NON-NLS-1$
			return new JSONResult(JSONCodeType.RegisterTelephoneInvalid, Messages.getString("InvalidTelephone"), null); //$NON-NLS-1$
		}
		JSONResult Invalidpassword = new JSONResult(JSONCodeType.RegisterPasswordInvalid,
				Messages.getString("InvalidPassword"), null); //$NON-NLS-1$
		String password = null;
		// 密码解码
		try {
			password = new String(RSAUtil.decrypt(Base64.getDecoder().decode(base64RSAPassword), privateKey));
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			return Invalidpassword;
		}
		if (password == null || !Pattern.matches(Messages.getString("PasswordRegex"), password) //$NON-NLS-1$
				|| Pattern.matches(Messages.getString("AllDigitalRegex"), password) //$NON-NLS-1$
				|| Pattern.matches(Messages.getString("AllLetterRegex"), password)) { //$NON-NLS-1$
			return Invalidpassword;
		}
		// 验证用户名是否存在
		User user = null;
		try {
			user = userDao.selectUserByUsername(username);
		} catch (Exception e) {
			logger.warn(Messages.getString("SQLError"), e); //$NON-NLS-1$
			return serverError;
		}
		if (user != null) {
			return new JSONResult(JSONCodeType.RegisterUsernameAlreadyExist, Messages.getString("UsernameAlreadyExist"), //$NON-NLS-1$
					null);
		}
		user = new User(username, SHA256Util.encrypt(password), name, telephone);
		try {
			userDao.insertUser(user);
		} catch (Exception e) {
			logger.warn(Messages.getString("SQLError"), e); //$NON-NLS-1$
			return serverError;
		}
		JSONResult success = new JSONResult(JSONCodeType.Success, Messages.getString("RegisterSuccess"), null); //$NON-NLS-1$
		success.put(Messages.getString("UserObjectSessionName"), user); //$NON-NLS-1$
		return success;
	}

	@Override
	public JSONResult login(String username, String base64RSAPassword, PrivateKey privateKey) {
		// 验证输入
		if (!Pattern.matches(Messages.getString("UsernameRegex"), username)) { //$NON-NLS-1$
			return new JSONResult(JSONCodeType.RegisterUsernameInvalid, Messages.getString("InvalidUsername"), null); //$NON-NLS-1$
		}
		JSONResult Invalidpassword = new JSONResult(JSONCodeType.RegisterPasswordInvalid,
				Messages.getString("InvalidPassword"), null); //$NON-NLS-1$
		String password = null;
		// 密码解码
		try {
			password = new String(RSAUtil.decrypt(Base64.getDecoder().decode(base64RSAPassword), privateKey));
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			return Invalidpassword;
		}
		if (password == null || !Pattern.matches(Messages.getString("PasswordRegex"), password) //$NON-NLS-1$
				|| Pattern.matches(Messages.getString("AllDigitalRegex"), password) //$NON-NLS-1$
				|| Pattern.matches(Messages.getString("AllLetterRegex"), password)) { //$NON-NLS-1$
			return Invalidpassword;
		}
		// 验证用户名是否存在
		User user = null;
		try {
			user = userDao.selectUserByUsername(username);
		} catch (Exception e) {
			logger.warn(Messages.getString("SQLError"), e); //$NON-NLS-1$
			return serverError;
		}
		if (user == null) {
			return new JSONResult(JSONCodeType.UserNotFound, Messages.getString("UserNotFound"), //$NON-NLS-1$
					null);
		}
		String sha256Password = SHA256Util.encrypt(password);
		if(!sha256Password.equals(user.getHex256Password())) {
			return new JSONResult(JSONCodeType.UsernameOrPasswordError, Messages.getString("UsernameOrPasswordError"), null); //$NON-NLS-1$
		}
		JSONResult result = new JSONResult(JSONCodeType.Success, Messages.getString("LoginSuccess"), null);
		result.put(Messages.getString("UserObjectSessionName"), user);
		return result;
	}

	@Override
	public JSONResult modify(String username, String password, String name, String telephone) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public JSONResult logout() {
		// TODO 自动生成的方法存根
		return null;
	}

}
