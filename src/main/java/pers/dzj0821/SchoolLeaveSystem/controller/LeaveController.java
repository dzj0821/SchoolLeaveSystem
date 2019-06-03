package pers.dzj0821.SchoolLeaveSystem.controller;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pers.dzj0821.SchoolLeaveSystem.Messages;
import pers.dzj0821.SchoolLeaveSystem.annotation.UserTypeRequired;
import pers.dzj0821.SchoolLeaveSystem.type.LeaveType;
import pers.dzj0821.SchoolLeaveSystem.type.UserType;
//TODO 使用自定义EL函数在JSP中取值
/**
 * 请假模块页面的Controller
 * @author dzj0821
 *
 */
@Controller
@RequestMapping("/leave")
public class LeaveController {
	/**
	 * 创建假条页面
	 * @param model
	 * @return
	 */
	@GetMapping("/create")
	@UserTypeRequired(UserType.NORMAL_USER)
	public String create(Model model) {
		Calendar calendar = Calendar.getInstance();
		int[] years = new int[3];
		//获取当前年份
		int currentYear = calendar.get(Calendar.YEAR);
		//提供当前年份到3年后（如2019~2021）的时间
		for(int i = 0; i < years.length; i++) {
			years[i] = currentYear++;
		}
		//月份从0开始
		int currentMonth = calendar.get(Calendar.MONTH) + 1;
		int currentDay = calendar.get(Calendar.DATE);
		int lastDay = calendar.getActualMaximum(Calendar.DATE);
		model.addAttribute("years", years);
		model.addAttribute("currentMonth", currentMonth);
		model.addAttribute("currentDay", currentDay);
		model.addAttribute("lastDay", lastDay);
		return Messages.getString("CreateLeavePage");
	}
	
	@GetMapping("/list")
	public String list(Integer clazzId, Integer userId, LeaveType type) {
		return "list";
	}
}
