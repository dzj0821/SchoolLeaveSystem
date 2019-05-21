package pers.dzj0821.SchoolLeaveSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pers.dzj0821.SchoolLeaveSystem.Messages;
import pers.dzj0821.SchoolLeaveSystem.annotation.UserTypeRequired;
import pers.dzj0821.SchoolLeaveSystem.type.UserType;
//TODO 使用自定义EL函数在JSP中取值
@Controller
@RequestMapping("/leave")
public class LeaveController {
	
	@GetMapping("/create")
	@UserTypeRequired(UserType.NORMAL_USER)
	public String create() {
		return Messages.getString("CreateLeavePage");
	}
}
