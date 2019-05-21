package pers.dzj0821.SchoolLeaveSystem.controller.api;

import java.io.IOException;
import java.security.KeyPair;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.dzj0821.SchoolLeaveSystem.Messages;
import pers.dzj0821.SchoolLeaveSystem.annotation.RSATimestampCheck;
import pers.dzj0821.SchoolLeaveSystem.annotation.UserTypeRequired;
import pers.dzj0821.SchoolLeaveSystem.pojo.User;
import pers.dzj0821.SchoolLeaveSystem.pojo.json.JSONResult;
import pers.dzj0821.SchoolLeaveSystem.service.UserService;
import pers.dzj0821.SchoolLeaveSystem.type.JSONCodeType;
import pers.dzj0821.SchoolLeaveSystem.type.UserType;

@RequestMapping("/api/user")
@Controller
public class ApiUserController {
	private Logger logger = LogManager.getLogger(ApiUserController.class);
	
	@Autowired
	private UserService userService;
	
	//TODO 修改为PostMapping
	@RequestMapping("/register")
	@ResponseBody
	@RSATimestampCheck
	public Map<String, Object> register(String username, String password, String name, String telephone, HttpSession session) {
		KeyPair keyPair = (KeyPair) session.getAttribute(Messages.getString("RSAKeyPairSessionName")); //$NON-NLS-1$
		return userService.register(username, password, name, telephone, keyPair.getPrivate());
	}
	//TODO 修改为PostMapping
	@RequestMapping("/login")
	@ResponseBody
	@RSATimestampCheck
	public Map<String, Object> login(String username, String password, HttpSession session){
		KeyPair keyPair = (KeyPair) session.getAttribute(Messages.getString("RSAKeyPairSessionName")); //$NON-NLS-1$
		JSONResult result = userService.login(username, password, keyPair.getPrivate());
		session.setAttribute(Messages.getString("UserObjectSessionName"), result.get(Messages.getString("UserObjectSessionName")));
		return result;
	}
	
	@PostMapping("/modify")
	@ResponseBody
	@UserTypeRequired(UserType.NORMAL_USER)
	@RSATimestampCheck
	public Map<String, Object> modify(String oldPassword, String newPassword, String name, String telephone, HttpSession session){
		KeyPair keyPair = (KeyPair) session.getAttribute(Messages.getString("RSAKeyPairSessionName")); //$NON-NLS-1$
		User user = (User) session.getAttribute(Messages.getString("UserObjectSessionName")); //$NON-NLS-1$
		JSONResult result = userService.modify(user, oldPassword, newPassword, name, telephone, keyPair.getPrivate());
		session.setAttribute(Messages.getString("UserObjectSessionName"), result.get(Messages.getString("UserObjectSessionName")));
		return result;
	}
	
	@PostMapping("/info")
	@ResponseBody
	@UserTypeRequired(UserType.NORMAL_USER)
	public Map<String, Object> info(Integer id, HttpSession session, HttpServletResponse response){
		User user = (User) session.getAttribute(Messages.getString("UserObjectSessionName")); //$NON-NLS-1$
		//TODO 目前权限验证写在service层，目标是controller层拦截权限不足的返回值并转为response.sendError(403)
		JSONResult result = userService.getUserInfo(id, user);
		if(result.getCode() == JSONCodeType.ACCESS_DENIED) {
			try {
				response.sendError(403);
			} catch (IOException e) {
				logger.warn(e);
			}
			return null;
		}
		return result;
	}
}
