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
	private JSONResult serverError = new JSONResult(JSONCodeType.SERVER_ERROR, Messages.getString("ServerError"), null); //$NON-NLS-1$

	@Override
	public JSONResult register(String username, String base64RSAPassword, String name, String telephone,
			PrivateKey privateKey) {
		// 验证输入
		if (!Pattern.matches(Messages.getString("UsernameRegex"), username)) { //$NON-NLS-1$
			return new JSONResult(JSONCodeType.INVALID_USERNAME, Messages.getString("InvalidUsername"), null); //$NON-NLS-1$
		}
		if (!Pattern.matches(Messages.getString("NameRegex"), name)) { //$NON-NLS-1$
			return new JSONResult(JSONCodeType.INVALID_NAME, Messages.getString("InvalidName"), null); //$NON-NLS-1$
		}
		if (!Pattern.matches(Messages.getString("TelephoneRegex"), telephone)) { //$NON-NLS-1$
			return new JSONResult(JSONCodeType.INVALID_TELEPHONE, Messages.getString("InvalidTelephone"), null); //$NON-NLS-1$
		}
		JSONResult Invalidpassword = new JSONResult(JSONCodeType.INVALID_PASSWORD,
				Messages.getString("InvalidPassword"), null); //$NON-NLS-1$
		String password = null;
		// 密码解码
		try {
			password = new String(RSAUtil.decrypt(Base64.getDecoder().decode(base64RSAPassword), privateKey));
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			return Invalidpassword;
		}
		if (!valifyPassword(password)) { //$NON-NLS-1$
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
			return new JSONResult(JSONCodeType.REGISTER_USERNAME_ALREADY_EXIST, Messages.getString("UsernameAlreadyExist"), //$NON-NLS-1$
					null);
		}
		user = new User(username, SHA256Util.encrypt(password), name, telephone);
		try {
			userDao.insertUser(user);
		} catch (Exception e) {
			logger.warn(Messages.getString("SQLError"), e); //$NON-NLS-1$
			return serverError;
		}
		JSONResult success = new JSONResult(JSONCodeType.SUCCESS, Messages.getString("RegisterSuccess"), null); //$NON-NLS-1$
		success.put(Messages.getString("UserObjectSessionName"), user); //$NON-NLS-1$
		return success;
	}

	@Override
	public JSONResult login(String username, String base64RSAPassword, PrivateKey privateKey) {
		// 验证输入
		if (!Pattern.matches(Messages.getString("UsernameRegex"), username)) { //$NON-NLS-1$
			return new JSONResult(JSONCodeType.INVALID_USERNAME, Messages.getString("InvalidUsername"), null); //$NON-NLS-1$
		}
		JSONResult Invalidpassword = new JSONResult(JSONCodeType.INVALID_PASSWORD,
				Messages.getString("InvalidPassword"), null); //$NON-NLS-1$
		String password = null;
		// 密码解码
		try {
			password = new String(RSAUtil.decrypt(Base64.getDecoder().decode(base64RSAPassword), privateKey));
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			return Invalidpassword;
		}
		if (!valifyPassword(password)) { //$NON-NLS-1$
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
			return new JSONResult(JSONCodeType.USER_NOT_FOUND, Messages.getString("UserNotFound"), //$NON-NLS-1$
					null);
		}
		String sha256Password = SHA256Util.encrypt(password);
		if(!sha256Password.equals(user.getPassword())) {
			return new JSONResult(JSONCodeType.USERNAME_OR_PASSWORD_ERROR, Messages.getString("UsernameOrPasswordError"), null); //$NON-NLS-1$
		}
		JSONResult result = new JSONResult(JSONCodeType.SUCCESS, Messages.getString("LoginSuccess"), null);
		result.put(Messages.getString("UserObjectSessionName"), user);
		return result;
	}

	@Override
	public JSONResult modify(User user, String base64RSAOldPassword, String base64RSANewPassword, String name, String telephone, PrivateKey privateKey) {
		//开始验证
		if("".equals(name)) {
			name = null;
		}
		if(name != null && !Pattern.matches(Messages.getString("NameRegex"), name)) { //$NON-NLS-1$
			return new JSONResult(JSONCodeType.INVALID_NAME, Messages.getString("InvalidName"), null); //$NON-NLS-1$
		}
		if("".equals(telephone)) {
			telephone = null;
		}
		if (telephone != null && !Pattern.matches(Messages.getString("TelephoneRegex"), telephone)) { //$NON-NLS-1$
			return new JSONResult(JSONCodeType.INVALID_TELEPHONE, Messages.getString("InvalidTelephone"), null); //$NON-NLS-1$
		}
		JSONResult invalidOldPassword = new JSONResult(JSONCodeType.INVALID_PASSWORD,
				Messages.getString("InvalidOldPassword"), null); //$NON-NLS-1$
		String oldPassword = null;
		try {
			oldPassword = new String(RSAUtil.decrypt(Base64.getDecoder().decode(base64RSAOldPassword), privateKey));
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			//TODO 删除调试代码
			logger.info("throw", e);
			return invalidOldPassword;
		}
		if(!valifyPassword(oldPassword)) {
			return invalidOldPassword;
		}
		JSONResult invalidNewPassword = new JSONResult(JSONCodeType.INVALID_PASSWORD,
				Messages.getString("InvalidNewPassword"), null); //$NON-NLS-1$
		String newPassword = null;
		try {
			newPassword = new String(RSAUtil.decrypt(Base64.getDecoder().decode(base64RSANewPassword), privateKey));
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			return invalidNewPassword;
		}
		if("".equals(newPassword)) {
			newPassword = null;
		}
		if(newPassword != null && !valifyPassword(newPassword)) {
			return invalidNewPassword;
		}
		//验证完毕
		String sha256Password = SHA256Util.encrypt(oldPassword);
		if(!user.getPassword().equals(sha256Password)) {
			return new JSONResult(JSONCodeType.OLD_PASSWORD_ERROR, Messages.getString("OldPasswordError"), null);
		}
		User updateUser = new User();
		updateUser.setId(user.getId());
		if(newPassword != null) {
			updateUser.setPassword(SHA256Util.encrypt(newPassword));
		}
		updateUser.setName(name);
		updateUser.setTelephone(telephone);
		try {
			userDao.updateUserById(updateUser);
			//TODO 可以使用主键查询的地方使用主键进行查询
			user = userDao.selectUserByUsername(user.getUsername());
		} catch (Exception e) {
			logger.warn(Messages.getString("SQLError"), e); //$NON-NLS-1$
			return serverError;
		}
		JSONResult result = new JSONResult(JSONCodeType.SUCCESS, Messages.getString("ModifyUserInfoSuccess"), null);
		result.put(Messages.getString("UserObjectSessionName"), user);
		return result;
	}

	@Override
	public JSONResult logout() {
		// TODO 自动生成的方法存根
		return null;
	}
	
	private boolean valifyPassword(String password) {
		if(password == null) {
			return false;
		}
		return Pattern.matches(Messages.getString("PasswordRegex"), password) //$NON-NLS-1$
				&& !Pattern.matches(Messages.getString("AllDigitalRegex"), password) //$NON-NLS-1$
				&& !Pattern.matches(Messages.getString("AllLetterRegex"), password); //$NON-NLS-1$
	}

	@Override
	public JSONResult getUserInfo(int willGetUserId, User fromUser) {
		//如果查看的不是自己的账号的信息
		if(willGetUserId != fromUser.getId()) {
			
		}
		return null;
	}

}
