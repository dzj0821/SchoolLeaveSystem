package pers.dzj0821.SchoolLeaveSystem.controller.api;

import java.security.PrivateKey;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.dzj0821.SchoolLeaveSystem.service.UserService;

@RequestMapping("/api/user")
@Controller
public class ApiUserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/register")
	@ResponseBody
	public Map<String, Object> register(String username, String base64RSAPassword, String name, int telephone, HttpSession session) {
		return userService.register(username, base64RSAPassword, name, telephone, (PrivateKey)session.getAttribute("privateKey"));
	}
}
