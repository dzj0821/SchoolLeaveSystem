package pers.dzj0821.SchoolLeaveSystem.controller.api;

import java.security.KeyPair;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
import pers.dzj0821.SchoolLeaveSystem.type.UserType;

@RequestMapping("/api/user")
@Controller
public class ApiUserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/register")
	@ResponseBody
	@RSATimestampCheck
	public Map<String, Object> register(String username, String password, String name, String telephone, HttpSession session) {
		KeyPair keyPair = (KeyPair) session.getAttribute(Messages.getString("RSAKeyPairSessionName")); //$NON-NLS-1$
		return userService.register(username, password, name, telephone, keyPair.getPrivate());
	}
	
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
}
