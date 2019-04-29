package pers.dzj0821.SchoolLeaveSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping(value = {"/", "/index"})
	public String index() {
		return "index";
	}
}