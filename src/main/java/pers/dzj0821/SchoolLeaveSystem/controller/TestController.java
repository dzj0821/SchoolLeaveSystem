package pers.dzj0821.SchoolLeaveSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.dzj0821.SchoolLeaveSystem.dao.UserDao;
import pers.dzj0821.SchoolLeaveSystem.pojo.User;

@Controller
public class TestController {
	
	@Autowired
	private UserDao userdao;
	
	@RequestMapping("/hello")
	public String hello(ModelMap model) {
		model.addAttribute("message", "this message is from controller");
		System.out.println("hello");
		return "hello";
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public String login(String username, String password) {
		User user = null;
		try {
			user = userdao.selectUserByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		if(user == null) {
			return "用户名不存在";
		}
		if(user.getHex256Password().equals(password)) {
			return "登陆成功";
		}
		return "用户名或密码错误";
	}
}
