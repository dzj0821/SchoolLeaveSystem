package pers.dzj0821.SchoolLeaveSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
	@RequestMapping("/hello")
	public String hello(ModelMap model) {
		model.addAttribute("message", "this message is from controller");
		System.out.println("hello");
		return "hello";
	}
}
