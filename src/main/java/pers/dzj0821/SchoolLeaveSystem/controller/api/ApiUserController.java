package pers.dzj0821.SchoolLeaveSystem.controller.api;

import java.security.KeyPair;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.dzj0821.SchoolLeaveSystem.Messages;
import pers.dzj0821.SchoolLeaveSystem.annotation.RSATimestampCheck;
import pers.dzj0821.SchoolLeaveSystem.service.UserService;

@RequestMapping("/api/user")
@Controller
public class ApiUserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/register")
	@ResponseBody
	@RSATimestampCheck
	public Map<String, Object> register(String username, String password, String name, String telephone, String publicKey, HttpSession session) {
		KeyPair keyPair = (KeyPair) session.getAttribute(Messages.getString("RSAKeyPairSessionName")); //$NON-NLS-1$
		return userService.register(username, password, name, telephone, keyPair.getPrivate());
	}
}
