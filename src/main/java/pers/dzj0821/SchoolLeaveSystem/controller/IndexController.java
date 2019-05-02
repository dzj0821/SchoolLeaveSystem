package pers.dzj0821.SchoolLeaveSystem.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.dzj0821.SchoolLeaveSystem.service.UserService;

@Controller
public class IndexController {
	
	@RequestMapping(value = {"/", "/index"})
	public String index() {
		return "index";
	}
	
	@Autowired
	private UserService userService;

	@RequestMapping("/test")
	@ResponseBody
	public Map<String, Object> test() {
		return userService.register("12345", null, null, 0, null);
	}
}